/*
 * TurtleBot.cpp - Library for easy control of an Arduino Uno powered
 * Parallax BoE (Board of Education) robot.
 *
 * License:  Released into the public domain.
 * Authors:
 *   AD:  Andrew C. Dassing
 * Change Log:
 *   2014-03-06 AD:  Conceptualized and initiated.
 */
#include "TurtleBot.h"
#include <Arduino.h>

#define PI 3.141592

///////////////
///////////////

TurtleBot::TurtleBot() {
	init();
}

TurtleBot::~TurtleBot() {
	if (left.attached()) {
		left.writeMicroseconds(LeftStopPWM);
		left.detach();
	}
	if (right.attached()) {
		right.writeMicroseconds(RightStopPWM);
		right.detach();
	}
}

TurtleBot::TurtleBot(const int leftMotorPin, const int rightMotorPin,
		const float wheelDiameter, const float axleTrack) {
	init();
	this->leftMotorPin = leftMotorPin;
	this->rightMotorPin = rightMotorPin;
	this->wheelDiameter = wheelDiameter;
	this->axleTrack = axleTrack;
	this->speed = wheelDiameter * PI;
}

void TurtleBot::init() {
	revPerMs = 0.00075;
	//= 0.00081; // = 0.81 revs/sec

	steering = SteerFORWARD;

	leftMotorPin = 13;
	leftPwm = LeftStopPWM;

	rightMotorPin = 12;
	rightPwm = RightStopPWM;

	wheelDiameter = 6.7; //cm
	axleTrack = 11.0;  //cm
	speed = wheelDiameter * PI;  // rotations/sec
	steer(0);
}

void TurtleBot::setRevolutionsPerSecond(const float revPerSec) {
	revPerMs = revPerSec / 1000.0;
}

TurtleBot& TurtleBot::forward(const float revolutions) {
	steer(0);
	engage(revolutions);
	return *this;
}

TurtleBot& TurtleBot::backward(const float revolutions) {
	steer(0);
	engage(-revolutions);
	return *this;
}

TurtleBot& TurtleBot::turnLeft() {	// 90 degrees
	turnLeft(90);
	return *this;
}

TurtleBot& TurtleBot::turnLeft(const float degrees) {
	steer(degrees < 0.0? SteerTurnRIGHT : SteerTurnLEFT);
	engage(getRevsForDegrees(degrees));
	return *this;
}

TurtleBot& TurtleBot::spinLeft() {  // 90 degrees
	spinLeft(90);
	return *this;
}

TurtleBot& TurtleBot::spinLeft(const float degrees) {
	steer(degrees < 0.0? SteerSpinRIGHT : SteerSpinLEFT);
	engage(getRevsForDegrees(degrees));
	return *this;
}

TurtleBot& TurtleBot::turnRight() {   // 90 degrees
	turnRight(90);
	return *this;
}

/*
 * turnRight() - spins in place clockwise approximately the number of degrees specified.
 */
TurtleBot& TurtleBot::turnRight(const float degrees) {
	steer(degrees < 0.0? SteerTurnLEFT : SteerTurnRIGHT);
	engage(getRevsForDegrees(degrees));
	return *this;
}

/*
 * spinRight() - spins in place clockwise approximately 90 degrees.
 */
TurtleBot& TurtleBot::spinRight() { // 90 degrees
	spinRight(90);
	return *this;
}

TurtleBot& TurtleBot::spinRight(const float degrees) {
	steer(degrees < 0.0? SteerSpinLEFT : SteerSpinRIGHT);
	engage(getRevsForDegrees(degrees));
	return *this;
}

/*
 * -200 .. -100 .. 0 .. +100 .. +200
 * spin    turn   fwd   turn    spin
 * left    left         right   right
 */
void TurtleBot::steer(const float steering) {

	leftPwm = LeftFullFwdPWM;
	rightPwm = RightFullFwdPWM;

	if (steering == 0.0) {
		//go straight
	} else if (steering < 0.0) { // left turn
		leftPwm = LeftFullFwdPWM + int(2.0 * steering);
	} else { //right turn
		rightPwm = RightFullFwdPWM + int(2.0 * steering);
	}
}

long TurtleBot::getMsForRevolutions(const float revolutions) {
	return long(abs(revolutions) / revPerMs);
}

float TurtleBot::getRevsForDegrees(const float degrees) {
	return (axleTrack * degrees) / (wheelDiameter * 180.0);
}

void TurtleBot::engage(const float revolutions) {
	boolean reversed = revolutions < 0.0;
	int finalLeftPwm = reversed? rightPwm : leftPwm;
	int finalRightPwm = reversed? leftPwm : rightPwm;

	if (!left.attached())
		left.attach(leftMotorPin);
	if (!right.attached())
		right.attach(rightMotorPin);
	left.writeMicroseconds(finalLeftPwm);
	right.writeMicroseconds(finalRightPwm);

	delay(getMsForRevolutions(revolutions));
	stop();
}

TurtleBot& TurtleBot::stop() {
	left.writeMicroseconds(LeftStopPWM);
	right.writeMicroseconds(RightStopPWM);
	return *this;
}


package com.ardublock.translator.block.turtlebot;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class TurtleBotTurnLeft extends TurtleBotTurnLeftBy {

	public TurtleBotTurnLeft(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
		translator.addHeaderFile("TurtleBot.h");
		translator.addHeaderFile("Servo.h");
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		return rotate("90");
	}
}

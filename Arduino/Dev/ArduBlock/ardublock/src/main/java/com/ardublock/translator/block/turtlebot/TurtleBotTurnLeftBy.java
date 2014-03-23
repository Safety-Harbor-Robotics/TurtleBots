package com.ardublock.translator.block.turtlebot;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class TurtleBotTurnLeftBy extends TranslatorBlock {

	public TurtleBotTurnLeftBy(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
		translator.addHeaderFile("TurtleBot.h");
		translator.addHeaderFile("Servo.h");
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		return rotate(this.getRequiredTranslatorBlockAtSocket(0).toCode());
	}
	
	protected String rotate (String rotations) 	{
		String ret;

		translator.addDefinitionCommand("TurtleBot turtleBot;\n");  //...singleton
		
		ret = "turtleBot.turnLeft(";
		ret += rotations;
		ret += ");\n";
		
		return ret;
	}
}

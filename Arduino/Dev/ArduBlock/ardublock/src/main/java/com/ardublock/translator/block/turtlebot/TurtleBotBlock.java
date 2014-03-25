package com.ardublock.translator.block.turtlebot;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class TurtleBotBlock extends TranslatorBlock {

	private final String motionMethodName;
	
	public TurtleBotBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label, String motion)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
		translator.addHeaderFile("TurtleBot.h");
		translator.addHeaderFile("Servo.h");
		motionMethodName = motion;
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		return motion(this.getRequiredTranslatorBlockAtSocket(0).toCode());
	}

	protected String motion (final String argValue) 	{
		String ret;

		translator.addDefinitionCommand("TurtleBot turtleBot;\n");  //...singleton
		
		ret = "turtleBot." + motionMethodName + "(";
		ret += argValue;
		ret += ");\n";
		
		return ret;
	}
}

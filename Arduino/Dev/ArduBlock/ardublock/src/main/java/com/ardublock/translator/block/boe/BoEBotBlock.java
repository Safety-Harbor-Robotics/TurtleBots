package com.ardublock.translator.block.boe;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;

;

public class BoEBotBlock extends TranslatorBlock {

	private final String motionMethodName;
	
	public BoEBotBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label, String motion)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
		translator.addHeaderFile("TurtleBot.h");
		translator.addHeaderFile("Servo.h");
		motionMethodName = motion;
	}

	@Override
	public String toCode() throws Exception
	{
		return motion(this.getRequiredTranslatorBlockAtSocket(0).toCode());
	}

	protected String motion (final String argValue) 	{
		String ret;

		translator.addDefinitionCommand("TurtleBot turtleBot;\n");  //...singleton
		
		ret = "turtleBot." + motionMethodName + "(";
		if (argValue != null) ret += argValue;
		ret += ");\n";
		
		return ret;
	}
}

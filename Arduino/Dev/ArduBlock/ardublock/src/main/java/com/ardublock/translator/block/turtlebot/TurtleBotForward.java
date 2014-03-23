package com.ardublock.translator.block.turtlebot;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class TurtleBotForward extends TranslatorBlock {

	public TurtleBotForward(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
		translator.addHeaderFile("TurtleBot.h");
		translator.addHeaderFile("Servo.h");
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		String ret;
		String rotations;

		translator.addDefinitionCommand("TurtleBot turtleBot;\n");  //...singleton
		
		TranslatorBlock rotationsBlock = this.getRequiredTranslatorBlockAtSocket(0);
		rotations = rotationsBlock.toCode();
		
		ret = "turtleBot.forward(";
		ret += rotations;
		ret += ");\n";
		
		return ret;
	}
	
	
///////////////// ORIGINAL REFACTORED CODE
//	@Override
//	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
//	{
//		String ret;
//		String port;
//		
//		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
//		port = translatorBlock.toCode();
//		
//		if (translatorBlock instanceof NumberBlock)
//		{
//			translator.addSetupCommand("pinMode( " + port + " , OUTPUT);");
//			ret = "digitalWrite( ";
//		}
//		else
//		{
//			translator.addDefinitionCommand(DigitalOutputBlock.ARDUBLOCK_DIGITAL_WRITE_DEFINE);
//			ret = "__ardublockDigitalWrite(";
//		}
//		
//		ret += port;
//		ret += ", ";
//		ret += this.getRequiredTranslatorBlockAtSocket(1).toCode();
//		ret += " );\n";
//		return ret;
//	}

}

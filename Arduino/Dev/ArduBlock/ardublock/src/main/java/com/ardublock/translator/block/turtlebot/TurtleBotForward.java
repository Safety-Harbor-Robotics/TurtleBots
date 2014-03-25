package com.ardublock.translator.block.turtlebot;

import com.ardublock.translator.Translator;

public class TurtleBotForward extends TurtleBotBlock {

	protected final static String MOTION = "forward";

	public TurtleBotForward(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label, MOTION);
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

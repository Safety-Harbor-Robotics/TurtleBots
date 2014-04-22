package com.ardublock.translator.block;

import com.ardublock.translator.Translator;

public class InversedAnalogOutputBlock extends AnalogOutputBlock
{

	public InversedAnalogOutputBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception
	{
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		String portNum = translatorBlock.toCode();
		translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
		String value = translatorBlock.toCode();
		
		String setupCode = "pinMode( " + portNum + " , OUTPUT);";
		translator.addSetupCommand(setupCode);
		
		String ret = "analogWrite(" + portNum + ", 255 - (" + value + " ));\n";
		
		return ret;
	}
	
}

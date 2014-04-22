package com.ardublock.translator.block;

import com.ardublock.translator.Translator;

public class AnalogOutputBlock extends TranslatorBlock
{
	public AnalogOutputBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception
	{
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		String portNum = translatorBlock.toCode();
		
		
		if (translatorBlock instanceof NumberBlock)
		{
			translator.addOutputPin(portNum.trim());
		}
		else
		{
			String setupCode = "pinMode( " + portNum + " , OUTPUT);";
			translator.addSetupCommand(setupCode);
		}
		translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
		String value = translatorBlock.toCode();
		
		String ret = "analogWrite(" + portNum + " , " + value + ");\n";
		return ret;
	}

}



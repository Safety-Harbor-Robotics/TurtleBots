package com.ardublock.translator.block;

import com.ardublock.translator.Translator;

public class SerialAvailableBlock extends TranslatorBlock
{
	public SerialAvailableBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception
	{
		translator.addSetupCommand("Serial.begin(9600);");
		
		String ret = "Serial.available()";
		
		return ret;
	}
}

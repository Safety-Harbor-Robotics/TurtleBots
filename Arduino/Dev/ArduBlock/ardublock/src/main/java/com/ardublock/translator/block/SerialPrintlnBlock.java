package com.ardublock.translator.block;


import com.ardublock.translator.Translator;

public class SerialPrintlnBlock extends TranslatorBlock
{
	public SerialPrintlnBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception
	{
		translator.addSetupCommand("Serial.begin(9600);");
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0, "Serial.print(", ");\n");
		
		String ret = translatorBlock.toCode();
		ret = ret + "Serial.println();\n";
		
		return ret;
	}
}

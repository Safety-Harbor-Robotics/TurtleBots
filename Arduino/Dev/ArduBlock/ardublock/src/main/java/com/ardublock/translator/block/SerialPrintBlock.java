package com.ardublock.translator.block;

import com.ardublock.translator.Translator;

public class SerialPrintBlock extends TranslatorBlock
{
	public SerialPrintBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception
	{
		/**
		 * DO NOT add tab in code any more, we'll use arduino to format code, or the code will duplicated. 
		 */
		translator.addSetupCommand("Serial.begin(9600);");
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0, "Serial.print(", ");\nSerial.print(\" \");\n");
		
		String ret = translatorBlock.toCode();
		translatorBlock = this.getRequiredTranslatorBlockAtSocket(1);
		String test=translatorBlock.toCode();
//		ret+=test;
		if(test.equals("true")){
		    ret+="Serial.println();\n";
		}
		return ret;
	}
}

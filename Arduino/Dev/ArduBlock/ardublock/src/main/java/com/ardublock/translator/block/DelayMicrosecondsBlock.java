package com.ardublock.translator.block;

import com.ardublock.translator.Translator;


public class DelayMicrosecondsBlock extends TranslatorBlock
{
	public DelayMicrosecondsBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception
	{
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		String ret = "\tdelayMicroseconds( " + tb.toCode() + " );\n";
		return ret;
	}

}

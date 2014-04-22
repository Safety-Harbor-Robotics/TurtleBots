package com.ardublock.translator.block;

import com.ardublock.translator.Translator;

public class NotEqualBlock extends TranslatorBlock
{
	public NotEqualBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception
	{
		String ret = "( ( ";
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		ret = ret + tb.toCode();
		ret = ret + " ) != ( ";
		tb = this.getRequiredTranslatorBlockAtSocket(1);
		ret = ret + tb.toCode();
		ret = ret + " ) )";
		return codePrefix + ret + codeSuffix;
	}
}

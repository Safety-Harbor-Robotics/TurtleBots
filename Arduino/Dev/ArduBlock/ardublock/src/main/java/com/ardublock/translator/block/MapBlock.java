package com.ardublock.translator.block;

import com.ardublock.translator.Translator;

public class MapBlock extends TranslatorBlock
{
	public MapBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception
	{
		String ret = "map ( ";
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		ret = ret + tb.toCode();
		ret = ret + " , ";
		tb = this.getRequiredTranslatorBlockAtSocket(1);
		ret = ret + tb.toCode();
		ret = ret + " , ";
		tb = this.getRequiredTranslatorBlockAtSocket(2);
		ret = ret + tb.toCode();
		ret = ret + " , ";
		tb = this.getRequiredTranslatorBlockAtSocket(3);
		ret = ret + tb.toCode();
		ret = ret + " , ";
		tb = this.getRequiredTranslatorBlockAtSocket(4);
		ret = ret + tb.toCode();
		ret = ret + " ) ";
		return codePrefix + ret + codeSuffix;
	}
	
}

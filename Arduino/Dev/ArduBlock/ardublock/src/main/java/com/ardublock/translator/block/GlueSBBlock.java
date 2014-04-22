package com.ardublock.translator.block;

import com.ardublock.translator.Translator;


public class GlueSBBlock extends GlueBlock
{
	public GlueSBBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception
	{
		String ret = "";
		TranslatorBlock translatorBlock = this.getTranslatorBlockAtSocket(0, codePrefix, codeSuffix);
		if (translatorBlock != null)
		{
			ret = translatorBlock.toCode();
		}
		return ret;
	}
}

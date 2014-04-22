package com.ardublock.translator.block;

import com.ardublock.translator.Translator;

public class NoToneBlock extends TranslatorBlock
{
	public NoToneBlock(Long blockId, Translator translator, String codePrefix,	String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception
	{
		TranslatorBlock pinBlock = this.getRequiredTranslatorBlockAtSocket(0);
		String ret = "\tnoTone(" + pinBlock.toCode() + ");\n";
		return ret;
	}
}

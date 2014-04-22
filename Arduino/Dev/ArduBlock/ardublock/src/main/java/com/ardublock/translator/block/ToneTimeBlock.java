package com.ardublock.translator.block;

import com.ardublock.translator.Translator;

public class ToneTimeBlock extends TranslatorBlock
{
	public ToneTimeBlock(Long blockId, Translator translator, String codePrefix,	String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception
	{
		TranslatorBlock pinBlock = this.getRequiredTranslatorBlockAtSocket(0);
		TranslatorBlock freqBlock = this.getRequiredTranslatorBlockAtSocket(1);
		TranslatorBlock timeBlock = this.getRequiredTranslatorBlockAtSocket(2);
		String ret = "tone(" + pinBlock.toCode() + ", " + freqBlock.toCode() + ", " + timeBlock.toCode() + ");\n";
		return ret;
	}
}

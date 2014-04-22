package com.ardublock.translator.block;

import com.ardublock.translator.Translator;

public class ToneBlock extends TranslatorBlock
{

	public ToneBlock(Long blockId, Translator translator, String codePrefix,	String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception
	{
		TranslatorBlock pinBlock = this.getRequiredTranslatorBlockAtSocket(0);
		TranslatorBlock freqBlock = this.getRequiredTranslatorBlockAtSocket(1);
		
		String ret = "tone(" + pinBlock.toCode() + ", " + freqBlock.toCode() + ");\n";
		return ret;
	}

}

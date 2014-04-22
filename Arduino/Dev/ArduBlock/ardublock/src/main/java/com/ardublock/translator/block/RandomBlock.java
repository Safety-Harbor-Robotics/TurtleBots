package com.ardublock.translator.block;

import com.ardublock.translator.Translator;

public class RandomBlock extends TranslatorBlock
{

	public RandomBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception
	{
		String ret = "\trandom( ";
		TranslatorBlock translatorBlock = getRequiredTranslatorBlockAtSocket(0);
		ret = ret + translatorBlock.toCode();
		ret = ret + " )";
		translator.addSetupCommand("\trandomSeed(analogRead(A0) + analogRead(A1) + analogRead(A2));");
		return codePrefix + ret + codeSuffix;
	}

}

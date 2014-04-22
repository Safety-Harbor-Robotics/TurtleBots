package com.ardublock.translator.block.insectbot;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;


public class GetDistanceBlock extends TranslatorBlock
{


	public GetDistanceBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception
	{
		InsectBotUtil.setupEnv(translator);
		
		String ret = "insect.getDistance()";
		
		return codePrefix + ret + codeSuffix;
	}

}

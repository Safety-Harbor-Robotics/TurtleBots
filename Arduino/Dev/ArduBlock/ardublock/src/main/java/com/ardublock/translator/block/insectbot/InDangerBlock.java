package com.ardublock.translator.block.insectbot;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;

public class InDangerBlock extends TranslatorBlock
{

	public InDangerBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception
	{
		InsectBotUtil.setupEnv(translator);
		
		String ret = "insect.isInDanger()";
		
		return codePrefix + ret + codeSuffix;
	}

	
}

package com.ardublock.translator.block;

import com.ardublock.translator.Translator;

public class SetupBlock extends TranslatorBlock
{
	public SetupBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator);
	}

	@Override
	public String toCode() throws Exception
	{
		String ret="";
		TranslatorBlock translatorBlock = getTranslatorBlockAtSocket(0);
		while (translatorBlock != null)
		{
			ret += translatorBlock.toCode();
			translatorBlock = translatorBlock.nextTranslatorBlock();
		}
        translator.addSetupCommand(ret);
		return "";
	}
}

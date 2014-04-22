package com.ardublock.translator.block.subroutine;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;

public class SubroutineBlock extends TranslatorBlock
{

	public SubroutineBlock(Long blockId, Translator translator,
			String codePrefix, String codeSuffix, String label) {
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception
	{
		String subroutineName = label.trim();
		String ret;
		ret = "void " + subroutineName + "()\n{\n";
		TranslatorBlock translatorBlock = getTranslatorBlockAtSocket(0);
		
		while (translatorBlock != null)
		{
			ret = ret + translatorBlock.toCode();
			translatorBlock = translatorBlock.nextTranslatorBlock();
		}
		ret = ret + "}\n\n";
		return ret;
	}
}

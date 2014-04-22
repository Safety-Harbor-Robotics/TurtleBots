package com.ardublock.translator.block.seeedstudio;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.NumberBlock;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;


public class GroveJoyStickButtonBlock extends TranslatorBlock
{
	public GroveJoyStickButtonBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception
	{
		String ret = "analogRead(A";
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		if (translatorBlock instanceof NumberBlock)
		{
			ret = ret + translatorBlock.toCode();
			ret = ret + ") > 1000";
			return codePrefix + ret + codeSuffix;
		}
		else
		{
			throw new BlockException(getBlockId(), "analog pin# must be a number");
		}
	}
}

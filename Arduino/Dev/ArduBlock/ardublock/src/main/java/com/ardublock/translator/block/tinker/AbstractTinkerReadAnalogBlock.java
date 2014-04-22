package com.ardublock.translator.block.tinker;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.NumberBlock;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.BlockException;

public abstract class AbstractTinkerReadAnalogBlock extends TranslatorBlock
{

	AbstractTinkerReadAnalogBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
		translator.addHeaderFile("TinkerKit.h");
	}
	
	@Override
	public String toCode() throws Exception
	{
		String ret = "analogRead(";
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		if (translatorBlock instanceof NumberBlock)
		{
			ret = ret + "A";
			ret = ret + translatorBlock.toCode();
			ret = ret + ")";
			return codePrefix + ret + codeSuffix;
		}
		else
		{
			if (translatorBlock instanceof TinkerInputPortBlock)
			{
				ret = ret + translatorBlock.toCode();
				ret = ret + ")";
				return codePrefix + ret + codeSuffix;
			}
			else
			{
				throw new BlockException(getBlockId(), "analog pin# must be a number");
			}
		}
	}

}

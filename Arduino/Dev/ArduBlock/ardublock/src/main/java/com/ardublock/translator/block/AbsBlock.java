package com.ardublock.translator.block;

import com.ardublock.translator.Translator;

public class AbsBlock extends TranslatorBlock
	{

		public AbsBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
		{
			super(blockId, translator, codePrefix, codeSuffix, label);
		}

		@Override
		public String toCode() throws Exception
		{
			String ret = "abs( ";
			TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
			ret = ret + translatorBlock.toCode();
			ret = ret + " )";
			return codePrefix + ret + codeSuffix;
		}
		
	}

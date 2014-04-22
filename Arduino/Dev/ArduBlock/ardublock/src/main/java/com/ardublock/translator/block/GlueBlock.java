package com.ardublock.translator.block;

import com.ardublock.translator.Translator;

public abstract class GlueBlock extends TranslatorBlock {

	public GlueBlock(Long blockId, Translator translator, String codePrefix,
			String codeSuffix, String label) {
		super(blockId, translator, codePrefix, codeSuffix, label);
	}
	
	@Override
	public abstract String toCode() throws Exception;
}

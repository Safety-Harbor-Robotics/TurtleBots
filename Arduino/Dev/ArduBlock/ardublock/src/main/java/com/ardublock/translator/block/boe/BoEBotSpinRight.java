package com.ardublock.translator.block.boe;

import com.ardublock.translator.Translator;

;

public class BoEBotSpinRight extends BoEBotBlock {

	protected static final String MOTION = "spinRight";

	public BoEBotSpinRight(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label, MOTION);
	}

	@Override
	public String toCode() throws Exception
	{
		return motion("90");
	}
}

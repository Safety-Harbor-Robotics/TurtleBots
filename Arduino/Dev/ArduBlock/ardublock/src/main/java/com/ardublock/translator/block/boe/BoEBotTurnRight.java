package com.ardublock.translator.block.boe;

import com.ardublock.translator.Translator;

;

public class BoEBotTurnRight extends BoEBotBlock {

	protected static final String MOTION = "turnRight";

	public BoEBotTurnRight(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label, MOTION);
	}

	@Override
	public String toCode() throws Exception
	{
		return motion("90");
	}
}

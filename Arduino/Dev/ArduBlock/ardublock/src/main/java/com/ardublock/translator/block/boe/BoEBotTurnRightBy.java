package com.ardublock.translator.block.boe;

import com.ardublock.translator.Translator;

public class BoEBotTurnRightBy extends BoEBotBlock {
	
	protected static final String MOTION = "turnRight";

	public BoEBotTurnRightBy(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label, MOTION);
	}
}

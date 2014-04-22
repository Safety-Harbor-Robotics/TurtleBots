package com.ardublock.translator.block.boe;

import com.ardublock.translator.Translator;

public class BoEBotTurnLeftBy extends BoEBotBlock {

	protected static final String MOTION = "turnLeft";
	
	public BoEBotTurnLeftBy(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label, MOTION);
	}
}

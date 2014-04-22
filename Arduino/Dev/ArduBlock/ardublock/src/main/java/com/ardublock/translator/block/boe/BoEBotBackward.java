package com.ardublock.translator.block.boe;

import com.ardublock.translator.Translator;

public class BoEBotBackward extends BoEBotBlock {

	private static final String MOTION = "backward";
	
	public BoEBotBackward(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label, MOTION);
	}
}

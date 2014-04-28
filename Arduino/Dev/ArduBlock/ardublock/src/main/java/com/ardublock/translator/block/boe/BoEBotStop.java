package com.ardublock.translator.block.boe;

import com.ardublock.translator.Translator;

public class BoEBotStop extends BoEBotBlock {

	protected static final String MOTION = "stop";

	public BoEBotStop(Long blockId, Translator translator, String codePrefix,
			String codeSuffix, String label, String motion) {
		super(blockId, translator, codePrefix, codeSuffix, label, MOTION);
	}

	@Override
	public String toCode() throws Exception
	{
		return motion(null);
	}
}

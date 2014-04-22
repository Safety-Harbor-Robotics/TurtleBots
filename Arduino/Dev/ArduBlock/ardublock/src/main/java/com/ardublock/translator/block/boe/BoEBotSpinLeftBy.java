package com.ardublock.translator.block.boe;

import com.ardublock.translator.Translator;

public class BoEBotSpinLeftBy extends BoEBotBlock {

	protected static final String MOTION = "spinLeft";

	public BoEBotSpinLeftBy(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label, MOTION);
	}
}

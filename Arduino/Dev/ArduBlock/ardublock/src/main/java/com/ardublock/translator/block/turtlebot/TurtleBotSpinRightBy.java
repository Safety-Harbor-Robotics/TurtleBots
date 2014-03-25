package com.ardublock.translator.block.turtlebot;

import com.ardublock.translator.Translator;

public class TurtleBotSpinRightBy extends TurtleBotBlock {

	protected static final String MOTION = "spinRight";

	public TurtleBotSpinRightBy(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label, MOTION);
	}
}

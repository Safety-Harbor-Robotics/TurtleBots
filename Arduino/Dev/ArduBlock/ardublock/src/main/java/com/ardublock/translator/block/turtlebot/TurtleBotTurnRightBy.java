package com.ardublock.translator.block.turtlebot;

import com.ardublock.translator.Translator;

public class TurtleBotTurnRightBy extends TurtleBotBlock {
	
	protected static final String MOTION = "turnRight";

	public TurtleBotTurnRightBy(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label, MOTION);
	}
}

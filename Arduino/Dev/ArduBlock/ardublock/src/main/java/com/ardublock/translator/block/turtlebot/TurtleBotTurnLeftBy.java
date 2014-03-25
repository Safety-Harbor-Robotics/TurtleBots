package com.ardublock.translator.block.turtlebot;

import com.ardublock.translator.Translator;

public class TurtleBotTurnLeftBy extends TurtleBotBlock {

	protected static final String MOTION = "turnLeft";
	
	public TurtleBotTurnLeftBy(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label, MOTION);
	}
}

package com.ardublock.translator.block.turtlebot;

import com.ardublock.translator.Translator;

public class TurtleBotBackward extends TurtleBotBlock {

	private static final String MOTION = "backward";
	
	public TurtleBotBackward(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label, MOTION);
	}
}

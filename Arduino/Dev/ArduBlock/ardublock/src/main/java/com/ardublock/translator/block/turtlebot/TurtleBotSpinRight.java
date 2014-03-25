package com.ardublock.translator.block.turtlebot;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class TurtleBotSpinRight extends TurtleBotBlock {

	protected static final String MOTION = "spinRight";

	public TurtleBotSpinRight(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label, MOTION);
	}

	@Override
	public String toCode() throws SocketNullException, SubroutineNotDeclaredException
	{
		return motion("90");
	}
}

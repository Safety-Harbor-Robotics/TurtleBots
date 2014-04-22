package com.ardublock.translator.block.exception;

import com.ardublock.core.exception.ArdublockException;

public class SubroutineParamNeedsGlue extends ArdublockException {

	private static final long serialVersionUID = -5042912591837008063L;

	private Long blockId;

	public Long getBlockId() {
		return blockId;
	}
	
	public SubroutineParamNeedsGlue(Long blockId)
	{
		this.blockId = blockId;
	}
}

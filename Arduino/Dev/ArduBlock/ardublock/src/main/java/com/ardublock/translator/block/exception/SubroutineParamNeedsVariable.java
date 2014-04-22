package com.ardublock.translator.block.exception;

import com.ardublock.core.exception.ArdublockException;

public class SubroutineParamNeedsVariable extends ArdublockException {

	private static final long serialVersionUID = 2785937241874792340L;
	
	private Long blockId;

	public Long getBlockId() {
		return blockId;
	}
	
	public SubroutineParamNeedsVariable (Long blockId)
	{
		this.blockId = blockId;
	}
	

}

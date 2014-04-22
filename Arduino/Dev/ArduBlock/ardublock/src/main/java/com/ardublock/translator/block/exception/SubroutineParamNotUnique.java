package com.ardublock.translator.block.exception;

import com.ardublock.core.exception.ArdublockException;

public class SubroutineParamNotUnique extends ArdublockException {

	private static final long serialVersionUID = -6410234682625391608L;
	
	private Long blockId;

	public Long getBlockId() {
		return blockId;
	}
	
	public SubroutineParamNotUnique (Long blockId)
	{
		this.blockId = blockId;
	}
}

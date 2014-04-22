package com.ardublock.translator.block.exception;

import com.ardublock.core.exception.ArdublockException;

public class SubroutineNeedsParamBlock extends ArdublockException {

	private static final long serialVersionUID = -3498990241055165108L;
	
	private Long blockId;

	public Long getBlockId() {
		return blockId;
	}
	
	public SubroutineNeedsParamBlock(Long blockId)
	{
		this.blockId = blockId;
	}
}

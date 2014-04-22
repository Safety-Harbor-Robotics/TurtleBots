package com.ardublock.translator.block.subroutine;

import com.ardublock.translator.Translator;

public class Parameters1SlotBlock extends ParametersBlock {

	public Parameters1SlotBlock(Long blockId, Translator translator,
			String codePrefix, String codeSuffix, String label) {
		super(blockId, translator, codePrefix, codeSuffix, label, 1);
	}
}

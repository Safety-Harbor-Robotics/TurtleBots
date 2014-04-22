package com.ardublock.translator.block.subroutine;

import com.ardublock.translator.Translator;

public class SubroutineParamBlock extends SubrFuncBaseBlock {

	public SubroutineParamBlock(Long blockId, Translator translator,
			String codePrefix, String codeSuffix, String label) {
		super(blockId, translator, codePrefix, codeSuffix, label, "void");
	}
}

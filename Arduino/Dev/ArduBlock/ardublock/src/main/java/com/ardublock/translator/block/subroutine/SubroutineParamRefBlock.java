package com.ardublock.translator.block.subroutine;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;
;

public class SubroutineParamRefBlock extends TranslatorBlock {

	public SubroutineParamRefBlock(Long blockId, Translator translator,
			String codePrefix, String codeSuffix, String label) {
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception {
	
		String subroutine = label.trim();
		if (!translator.containFunctionName(subroutine)) {
			throw new SubroutineNotDeclaredException(getBlockId());
		}
		
		subroutine += "(";
		
		TranslatorBlock glue1 = this.getRequiredTranslatorBlockAtSocket(0);
		TranslatorBlock param1 = glue1.getTranslatorBlockAtSocket(0);
		subroutine += param1.toCode();
		
		return subroutine + ");\n";
	}

}

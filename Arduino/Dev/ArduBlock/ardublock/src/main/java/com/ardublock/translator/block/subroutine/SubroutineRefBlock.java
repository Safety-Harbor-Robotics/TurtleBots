package com.ardublock.translator.block.subroutine;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;

public class SubroutineRefBlock extends TranslatorBlock
{

	public SubroutineRefBlock(Long blockId, Translator translator,
			String codePrefix, String codeSuffix, String label) {
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception
	{
		String subroutineName = label.trim();
		if (!translator.containFunctionName(subroutineName))
		{
			throw new SubroutineNotDeclaredException(getBlockId());
		}
		return "\t"+subroutineName + "();\n";
	}

}

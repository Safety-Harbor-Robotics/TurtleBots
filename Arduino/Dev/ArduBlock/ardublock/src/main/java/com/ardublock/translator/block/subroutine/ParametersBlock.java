package com.ardublock.translator.block.subroutine;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.GlueBlock;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.VariableBlock;
import com.ardublock.translator.block.exception.SubroutineParamNeedsGlue;
import com.ardublock.translator.block.exception.SubroutineParamNeedsVariable;

public abstract class ParametersBlock extends TranslatorBlock {

	protected int parameterCount = 0;
	protected String subroutine = "?";
	
	public ParametersBlock(Long blockId, Translator translator,
			String codePrefix, String codeSuffix, String label, int paramCount) {
		super(blockId, translator, codePrefix, codeSuffix, label);
		parameterCount = paramCount;
	}
	
	public void setSubroutine(String subroutine) {
		this.subroutine = subroutine;  
	}

	@Override
	public String toCode() throws Exception {
		StringBuffer code = new StringBuffer(40);
		
		for (int i = 0;  i < parameterCount; i++) {
			if (i > 0) code.append(", ");

			TranslatorBlock glue = getRequiredTranslatorBlockAtSocket(i);
			if (!(glue instanceof GlueBlock)) {
				throw new SubroutineParamNeedsGlue(getBlockId());
			}
			
			TranslatorBlock param = glue.getRequiredTranslatorBlockAtSocket(0);
			if (!(param instanceof VariableBlock)) {
				throw new SubroutineParamNeedsVariable(getBlockId());
			}
			
			code.append(((VariableBlock)param).toParameter(subroutine));
		}
		
		return code.toString();
	}
}

package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.SubroutineParamNotUnique;

public abstract class VariableBlock extends TranslatorBlock {

	String dataType = "?";
	
	public VariableBlock(Long blockId, Translator translator,
			String codePrefix, String codeSuffix, String label, String dataType) {
		super(blockId, translator, codePrefix, codeSuffix, label);
		this.dataType = dataType;
	}

	@Override
	public String toParameter(String subrName) throws Exception {
		
		String paramName = label;
		String internalParamName = translator.getParamVariable(paramName);
		if (internalParamName == null)	//...good!
		{
			translator.addParameterVariable(translator.buildParameterName(paramName), paramName);
		}
		else
			throw new SubroutineParamNotUnique(this.getBlockId()); 
			
		return dataType + " " + label;
	}
}

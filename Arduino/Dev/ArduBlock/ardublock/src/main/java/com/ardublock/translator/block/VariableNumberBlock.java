package com.ardublock.translator.block;

import com.ardublock.translator.Translator;

public class VariableNumberBlock extends VariableBlock {
	public VariableNumberBlock(Long blockId, Translator translator,
			String codePrefix, String codeSuffix, String label) {
		super(blockId, translator, codePrefix, codeSuffix, label, "int");
	}

	@Override
	public String toCode() {
		String ret = "";
		String varName = translator.getParamVariable(label);
		if (translator.isInSubroutine() && varName != null) {
			ret = varName;
		} else {
			if (varName == null) {
				varName = translator.getNumberVariable(label);
			}

			if (varName == null) {
				varName = translator.buildVariableName(label);
				translator.addNumberVariable(label, varName);
				translator.addDefinitionCommand(dataType + " "
						+ varName + " = 0 ;");
			}
			ret = codePrefix + varName + codeSuffix;
		}
		
		return ret;
	}
}

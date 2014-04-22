package com.ardublock.translator.block.subroutine;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.GlueBlock;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.VariableBlock;
import com.ardublock.translator.block.exception.SubroutineNeedsParamBlock;
import com.ardublock.translator.block.exception.SubroutineParamNeedsVariable;

public class SubrFuncBaseBlock extends TranslatorBlock {

	protected String returnType = "void";
	
	public SubrFuncBaseBlock(Long blockId, Translator translator,
			String codePrefix, String codeSuffix, String label, String retType) {
		super(blockId, translator, codePrefix, codeSuffix, label);
		returnType = retType;
	}

	@Override
	public String toCode() throws Exception {
		String subrShortName = label.trim();
		
		StringBuffer code = new StringBuffer(40);
		code.append(returnType).append(" ").append(subrShortName).append("(");
		
		TranslatorBlock connector = getRequiredTranslatorBlockAtSocket(0);
		translator.enterSubroutine(subrShortName);
		
		if (connector instanceof ParametersBlock) {
			ParametersBlock params = (ParametersBlock)connector;
			params.setSubroutine(subrShortName);
			code.append(params.toCode());
		} else if (connector instanceof GlueBlock) {
			TranslatorBlock param = connector.getRequiredTranslatorBlockAtSocket(0);
			if (!(param instanceof VariableBlock)) {
				throw new SubroutineParamNeedsVariable(getBlockId());
			}
			
			code.append(((VariableBlock)param).toParameter(subrShortName));
		} else {
			throw new SubroutineNeedsParamBlock(connector.getBlockId());
		}
		
		code.append(")\n{\n");

		TranslatorBlock translatorBlock = getTranslatorBlockAtSocket(1);
		while (translatorBlock != null) {
			code.append(translatorBlock.toCode());
			translatorBlock = translatorBlock.nextTranslatorBlock();
		}
		translator.exitSubroutine();

		return code.append("}\n\n").toString();
	}

}

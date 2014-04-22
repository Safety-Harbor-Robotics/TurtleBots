package com.ardublock.translator.block;

import com.ardublock.translator.Translator;

public class VariableVectorBlock extends TranslatorBlock
{
	public VariableVectorBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception
	{
        TranslatorBlock position = this.getRequiredTranslatorBlockAtSocket(0);
		String ret = "vec_"+label.replace(" ","")+"["+position.toCode()+" - 1]";
		return codePrefix + ret + codeSuffix;
	}

}

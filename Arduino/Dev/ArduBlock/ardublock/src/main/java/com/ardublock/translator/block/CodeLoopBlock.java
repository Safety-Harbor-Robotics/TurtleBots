package com.ardublock.translator.block;

import com.ardublock.translator.Translator;


public class CodeLoopBlock extends TranslatorBlock
{
	public CodeLoopBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception
	{
		
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		String ret = "\t"+translatorBlock.toCode();
		//ret=ret.substring(1);
		//ret=ret.replace(ret.substring(ret.length()),";");
		ret=ret+"\n";
		return ret;
	}
}

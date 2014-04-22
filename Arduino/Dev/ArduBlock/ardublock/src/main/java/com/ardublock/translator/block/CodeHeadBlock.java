package com.ardublock.translator.block;

import com.ardublock.translator.Translator;


public class CodeHeadBlock extends TranslatorBlock
{
	public CodeHeadBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception
	{
		
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		
		String ret = translatorBlock.toCode();
		//ret=ret.substring(1);
		//ret=ret.replace(ret.substring(ret.length()-1),"");
		ret=ret+"\n";
		translator.addDefinitionCommand(ret);
		return "";
	}
}

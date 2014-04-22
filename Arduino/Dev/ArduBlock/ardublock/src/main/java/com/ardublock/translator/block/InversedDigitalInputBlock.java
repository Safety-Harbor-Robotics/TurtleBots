package com.ardublock.translator.block;

import com.ardublock.translator.Translator;

public class InversedDigitalInputBlock extends DigitalInputBlock
{

	public InversedDigitalInputBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
		super(blockId, translator, codePrefix, codeSuffix, label);
	}
	
	protected String generateCodeUsingNumberBlock(TranslatorBlock translatorBlock) throws Exception
	{
		String number;
		number = translatorBlock.toCode();
		translator.addInputPin(number.trim());
		
		String ret = "!digitalRead(";
		ret = ret + number;
		ret = ret + ")";
		return codePrefix + ret + codeSuffix;
	}
	
	protected String generateCodeUsingNonNumberBlock(TranslatorBlock translatorBlock) throws Exception
	{
		translator.addDefinitionCommand(ARDUBLOCK_DIGITAL_READ_DEFINE);
		String ret = "!__ardublockDigitalRead(";
		
		ret = ret + translatorBlock.toCode();
		ret = ret + ")";
		return codePrefix + ret + codeSuffix;
	}
}

package com.ardublock.translator.block;

import com.ardublock.translator.Translator;


public class DigitalInputBlock extends TranslatorBlock
{
	public static final String ARDUBLOCK_DIGITAL_READ_DEFINE = "boolean __ardublockDigitalRead(int pinNumber)\n{\npinMode(pinNumber, INPUT);\nreturn digitalRead(pinNumber);\n}\n\n";
	
	public DigitalInputBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception
	{
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		if (translatorBlock instanceof NumberBlock)
		{
			return generateCodeUsingNumberBlock(translatorBlock);
		}
		else
		{
			return generateCodeUsingNonNumberBlock(translatorBlock);
		}
		
	}
	
	protected String generateCodeUsingNumberBlock(TranslatorBlock translatorBlock) throws Exception
	{
		String number;
		number = translatorBlock.toCode();
		translator.addInputPin(number.trim());
		
		String ret = "digitalRead(";
		ret = ret + number;
		ret = ret + ")";
		return codePrefix + ret + codeSuffix;
	}
	
	protected String generateCodeUsingNonNumberBlock(TranslatorBlock translatorBlock) throws Exception
	{
		translator.addDefinitionCommand(ARDUBLOCK_DIGITAL_READ_DEFINE);
		String ret = "__ardublockDigitalRead(";
		
		ret = ret + translatorBlock.toCode();
		ret = ret + ")";
		return codePrefix + ret + codeSuffix;
	}

}

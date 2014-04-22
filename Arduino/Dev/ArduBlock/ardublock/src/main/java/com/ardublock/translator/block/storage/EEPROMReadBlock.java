package com.ardublock.translator.block.storage;

//import com.ardublock.core.Context;
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;


public class EEPROMReadBlock extends TranslatorBlock
{
	public EEPROMReadBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception
	{
		setupEEPROMEnvironment(translator);
		
		String ret = "EEPROM.read( ";
		
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);

		ret += tb.toCode() + " ) ";
		
	return codePrefix + ret + codeSuffix;
}
	
	public static void setupEEPROMEnvironment(Translator t)
	{
		t.addHeaderFile("EEPROM.h");
	}


}

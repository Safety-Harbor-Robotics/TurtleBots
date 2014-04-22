package com.ardublock.translator.block;

import com.ardublock.translator.Translator;

public class WatchdogResetBlock extends TranslatorBlock
{
	public WatchdogResetBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception
	{
		WatchdogBlock.setupWatchdog(translator);

		//TranslatorBlock t5 = getRequiredTranslatorBlockAtSocket(0);
		//String bol1 = t5.toString();

			String ret = "wdt_reset();\n";
			return ret;
		
	}


}

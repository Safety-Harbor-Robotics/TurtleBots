package com.ardublock.translator.block;

import com.ardublock.core.Context;
import com.ardublock.translator.Translator;

public class WireWriteBlock extends TranslatorBlock
{
	public WireWriteBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception
	{
		WireReadBlock.setupWireEnvironment(translator);
	
		String ret = "";
		Context context = Context.getContext();
		if (context.getArduinoVersionString().equals(Context.ARDUINO_VERSION_UNKNOWN))
		{
			ret += "//Unable to dectect your Arduino version, using 1.0 in default\n";
		}
		
		ret += "\t__ardublockI2cWriteData( ";
		TranslatorBlock tb = getRequiredTranslatorBlockAtSocket(0);
		ret = ret + tb.toCode();
		ret = ret + " , ";
		tb = getRequiredTranslatorBlockAtSocket(1);
		ret = ret + tb.toCode();
		ret = ret + " , ";
		tb = getRequiredTranslatorBlockAtSocket(2);
		ret = ret + tb.toCode();
		ret = ret + " );\n";
		return ret;
	}

}

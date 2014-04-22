package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;

public class LCD_command_lightOff_Block extends TranslatorBlock {
	
	public LCD_command_lightOff_Block(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	//@Override
	public String toCode() throws Exception
	{
		
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		String I2C_addr = tb.toCode();
		String ret = "lcd_I2C_" + I2C_addr + ".setBacklight(LOW);\n";
		return ret;
	}
	
}
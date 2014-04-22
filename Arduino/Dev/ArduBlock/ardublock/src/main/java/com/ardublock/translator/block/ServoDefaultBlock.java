
package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.BlockException;

public class ServoDefaultBlock extends TranslatorBlock {

	public ServoDefaultBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception
	{
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);

		String servoSpecs = "";

		if (!( tb instanceof NumberBlock ) )
		{
			throw new BlockException(this.getBlockId(), "the Pin# of Servo must be a number");
		}
		
		String pinNumber = tb.toCode();
		String servoName = "servo_pin_" + pinNumber;

		tb = this.getRequiredTranslatorBlockAtSocket(1);

		String ret = servoName + ".write( " + tb.toCode() + " );\n";
		translator.addHeaderFile("Servo.h");
		translator.addDefinitionCommand("Servo " + servoName + ";");
		translator.addSetupCommand(servoName + ".attach(" + pinNumber + servoSpecs + ");");
		return ret;
	}

}

package com.ardublock.translator.block.makeblock;
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;

public class MeUltrasonicSensor extends TranslatorBlock {

	public MeUltrasonicSensor(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception {
		translator.addHeaderFile("Makeblock.h");
		translator.addHeaderFile("SoftwareSerial.h");
		translator.addHeaderFile("Wire.h");
		TranslatorBlock translatorBlock = this.getRequiredTranslatorBlockAtSocket(0);
		String ret = "MeUltrasonicSensor ultraSensor"+translatorBlock.toCode()+"(PORT_"+translatorBlock.toCode()+");";
		translator.addDefinitionCommand(ret);
		return codePrefix + "ultraSensor"+translatorBlock.toCode()+".distanceCm()" + codeSuffix;
	}
}

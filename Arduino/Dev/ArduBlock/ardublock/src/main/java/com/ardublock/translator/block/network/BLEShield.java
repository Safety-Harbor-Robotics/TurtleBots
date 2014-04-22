package com.ardublock.translator.block.network;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;

public class BLEShield extends TranslatorBlock {

	public BLEShield(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception {
		translator.addHeaderFile("SoftwareSerial.h");
		translator.addDefinitionCommand("SoftwareSerial bleShield(2, 3);");
		translator.addSetupCommand("bleShield.begin(19200);");
		return "";
	}

}

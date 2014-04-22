package com.ardublock.translator.block.network;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;

public class BLEShieldAvailable extends TranslatorBlock {

	public BLEShieldAvailable(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception {
		return "bleShield.available()";
	}

}

package com.ardublock.translator.block.network;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;

public class BLEShieldRead extends TranslatorBlock {

	public BLEShieldRead(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
		super(blockId, translator, codePrefix, codeSuffix, label);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toCode() throws Exception {
		return "bleShield.read()";
	}

}

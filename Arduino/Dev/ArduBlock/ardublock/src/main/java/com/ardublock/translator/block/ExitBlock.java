package com.ardublock.translator.block;

import com.ardublock.translator.Translator;

public class ExitBlock extends TranslatorBlock {

	public ExitBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
		super(blockId, translator);
	}

	@Override
	public String toCode() throws Exception {
	    String ret = "exit(0);\n";
	    return ret;
	}

}

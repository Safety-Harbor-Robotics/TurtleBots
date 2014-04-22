package com.ardublock.translator.block.makeblock;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;

public class MeShutterAction extends TranslatorBlock {

	public MeShutterAction(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label) {
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception {
	
		String port_name = this.getTranslator().getBlock(getBlockId()).getGenusName();
		String port_str = port_name.split("_")[2];
		return port_str;
	
	}

}

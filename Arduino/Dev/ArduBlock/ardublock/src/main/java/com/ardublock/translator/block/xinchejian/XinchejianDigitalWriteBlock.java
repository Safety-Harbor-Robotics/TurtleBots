package com.ardublock.translator.block.xinchejian;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;

public class XinchejianDigitalWriteBlock extends TranslatorBlock
{

	public XinchejianDigitalWriteBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception
	{
		translator.addDefinitionCommand(XinchejianDigitalReadBlock.XINCHEJIAN_DIGITAL_IO_DEFINITION);
		
		String ret = "__ardublock_xinchejian_ms_digitalWrite( ";
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		ret = ret + tb.toCode();
		ret = ret + " , ";
		tb = this.getRequiredTranslatorBlockAtSocket(1);
		ret = ret + tb.toCode();
		ret = ret + " );\n";
		return ret;
	}

}

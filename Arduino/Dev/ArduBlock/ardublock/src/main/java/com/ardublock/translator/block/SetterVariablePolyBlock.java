package com.ardublock.translator.block;

import java.util.ResourceBundle;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.BlockException;

public class SetterVariablePolyBlock extends TranslatorBlock
{
	private static ResourceBundle uiMessageBundle = ResourceBundle.getBundle("com/ardublock/block/ardublock");
	
	public SetterVariablePolyBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception
	{
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
		if (!(tb instanceof VariablePolyBlock))	{
			throw new BlockException(getBlockId(), uiMessageBundle.getString("ardublock.error_msg.char_var_slot"));
		}
		
		String ret = tb.toCode();
		tb = this.getRequiredTranslatorBlockAtSocket(1);
		ret = ret + " = " ;
		if(tb.toCode().replace("\"","").length()>1){
    		ret+=tb.toCode() + ";\n";
    	}else{
    	    ret+="\'"+tb.toCode().replace("\"","") + "\';\n";
    	}
		return ret;
	}

}

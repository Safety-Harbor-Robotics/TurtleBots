package com.ardublock.translator.block;

import java.util.ResourceBundle;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.BlockException;

public class CreateVariableVectorBlock extends TranslatorBlock
{
	private static ResourceBundle uiMessageBundle = ResourceBundle.getBundle("com/ardublock/block/ardublock");
	
	public CreateVariableVectorBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}
	
	@Override
	public String toCode() throws Exception
	{
		String varName="";
		TranslatorBlock name = this.getRequiredTranslatorBlockAtSocket(0);
		TranslatorBlock size = this.getRequiredTranslatorBlockAtSocket(1);
		if (!(name instanceof VariableFakeBlock)) {
			throw new BlockException(getBlockId(), uiMessageBundle.getString("ardublock.error_msg.array_var_slot"));
		}
		if (!(size instanceof NumberBlock)) {
			throw new BlockException(getBlockId(), uiMessageBundle.getString("ardublock.error_msg.array_size_slot"));
		}
		
		varName+=name.toCode();
		int foo = Integer.parseInt(size.toCode());
		varName+="[";
		translator.addDefinitionCommand("int " + varName +foo+"];\n");
		translator.addSetupCommand("\tfor (int i=0;i<"+foo+";i++) "+ varName+"i]=0;\n"   );
		return "";
	}

}

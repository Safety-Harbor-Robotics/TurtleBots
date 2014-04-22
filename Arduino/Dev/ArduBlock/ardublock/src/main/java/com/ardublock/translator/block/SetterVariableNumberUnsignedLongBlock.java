package com.ardublock.translator.block;

import java.util.ResourceBundle;

import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.BlockException;

public class SetterVariableNumberUnsignedLongBlock extends TranslatorBlock
{
  private static ResourceBundle uiMessageBundle = ResourceBundle.getBundle("com/ardublock/block/ardublock");
  
  public SetterVariableNumberUnsignedLongBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
  {
    super(blockId, translator, codePrefix, codeSuffix, label);
  }

  @Override
  public String toCode() throws Exception
  {
    TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);
    if (!(tb instanceof VariableNumberUnsignedLongBlock)) {
      throw new BlockException(getBlockId(), uiMessageBundle.getString("ardublock.error_msg.long_number_var_slot"));
    }
    
    String ret = tb.toCode();
    tb = this.getRequiredTranslatorBlockAtSocket(1);
    ret = ret + " = " + tb.toCode() + " ;\n";
    //ret = ret + " = " + "(unsigned long)" + tb.toCode() + " ;\n";
    return ret;
  }

}
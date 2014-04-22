package com.ardublock.translator.block;

import com.ardublock.translator.Translator;

public class VariableNumberUnsignedLongBlock extends VariableBlock
{
  public VariableNumberUnsignedLongBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
  {
    super(blockId, translator, codePrefix, codeSuffix, label, "unsigned long");
  }

  @Override
  public String toCode()
  {
    String internalVariableName = translator.getNumberVariable(label);
    if (internalVariableName == null)
    {
      internalVariableName = translator.buildVariableName(label);
      translator.addNumberVariable(label, internalVariableName);
      translator.addDefinitionCommand(dataType + " " + internalVariableName + " = 0UL ;");
//      translator.addSetupCommand(internalVariableName + " = 0;");
    }
    return codePrefix + internalVariableName + codeSuffix;
  }

}
package com.ardublock.translator.block.storage;

//import com.ardublock.core.Context;
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.TranslatorBlock;


public class EEPROMReadLongBlock extends TranslatorBlock
{
	public EEPROMReadLongBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		super(blockId, translator, codePrefix, codeSuffix, label);
	}

	@Override
	public String toCode() throws Exception
	{
		setupEEPROMEnvironment(translator);
		
		String ret = "eepromReadLong( ";
		
		TranslatorBlock tb = this.getRequiredTranslatorBlockAtSocket(0);

		ret += tb.toCode() + " ) ";
		
	return codePrefix + ret + codeSuffix;
}
	
	public static void setupEEPROMEnvironment(Translator t)
	{
		t.addHeaderFile("EEPROM.h");
		t.addDefinitionCommand(	"/********************************************************\n" 	+ 
								"A way to read unsigned longs (4 Bytes)from EEPROM \n" 			+
								"EEPROM library natively supports only bytes\n" 				+                              
								"********************************************************/\n" 	+ 
								"unsigned long eepromReadLong(int address){\n" 	+               
                                "\n" 											+        
								"	union u_tag {\n" 							+                                           
								"		byte b[4];\n" 							+                                           
								"		unsigned long ULtime;\n" 				+                                
								"	} time;\n" 									+                                                
								"	time.b[0] = EEPROM.read(address);\n" 		+                      
								"	time.b[1] = EEPROM.read(address+1);\n"		+                   
								"	time.b[2] = EEPROM.read(address+2);\n" 		+                    
								"	time.b[3] = EEPROM.read(address+3);\n" 		+                    
								"	return time.ULtime;\n" +                                   
								"}\n" );                                                       

	}


}

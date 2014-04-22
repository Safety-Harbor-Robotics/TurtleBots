package com.ardublock.translator.block;

import com.ardublock.translator.Translator;
import com.ardublock.translator.adaptor.BlockAdaptor;
import com.ardublock.translator.block.exception.SocketNullException;


abstract public class TranslatorBlock
{
	public abstract String toCode() throws Exception;
	
	private Long blockId;
	
	private BlockAdaptor blockAdaptor;
	
	protected Translator translator;
	
	protected String label;
	protected String comment;
	
	protected String codePrefix;
	protected String codeSuffix;
	
	protected TranslatorBlock(Long blockId, Translator translator)
	{
		this.setBlockId(blockId);
		this.translator = translator;
		this.blockAdaptor = translator.getBlockAdaptor();
		this.codePrefix = "";
		this.codeSuffix = "";
		this.label = "";
	}
	
	protected TranslatorBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix)
	{
		this.setBlockId(blockId);
		this.translator = translator;
		this.blockAdaptor = translator.getBlockAdaptor();
		this.codePrefix = codePrefix;
		this.codeSuffix = codeSuffix;
		this.label = "";
	}
	
	public TranslatorBlock(Long blockId, Translator translator, String codePrefix, String codeSuffix, String label)
	{
		this.setBlockId(blockId);
		this.translator = translator;
		this.blockAdaptor = translator.getBlockAdaptor();
		this.codePrefix = codePrefix;
		this.codeSuffix = codeSuffix;
		this.label = label;
	}
	
	protected Translator getTranslator()
	{
		return translator;
	}
	
	public TranslatorBlock nextTranslatorBlock()
	{
		return this.nextTranslatorBlock("", "");
	}
	
	protected TranslatorBlock nextTranslatorBlock(String codePrefix, String codeSuffix)
	{
		return blockAdaptor.nextTranslatorBlock(this.translator, getBlockId(), codePrefix, codeSuffix);
	}
	
	public TranslatorBlock getTranslatorBlockAtSocket(int i)
	{
		return this.getTranslatorBlockAtSocket(i, "", "");
	}
	
	protected TranslatorBlock getTranslatorBlockAtSocket(int i, String codePrefix, String codeSuffix)
	{
		return blockAdaptor.getTranslatorBlockAtSocket(this.translator, getBlockId(), i, codePrefix, codeSuffix);
	}
	
	public TranslatorBlock getRequiredTranslatorBlockAtSocket(int i) throws SocketNullException
	{
		return this.getRequiredTranslatorBlockAtSocket(i, "", "");
	}
	
	protected TranslatorBlock getRequiredTranslatorBlockAtSocket(int i, String codePrefix, String codeSuffix) throws SocketNullException
	{
		TranslatorBlock translatorBlock = blockAdaptor.getTranslatorBlockAtSocket(this.translator, getBlockId(), i, codePrefix, codeSuffix);
		if (translatorBlock == null)
		{
			throw new SocketNullException(getBlockId());
		}
		return translatorBlock;
	}
	
	protected void setComment(String comment)
	{
		this.comment = comment;
	}
	
	protected String getComment()
	{
		return this.comment;
	}
	
	public void onTranslateBodyFinished(){}

	public String toParameter(String subrName) throws Exception {
		return label;
	}

	public Long getBlockId() {
		return blockId;
	}

	public void setBlockId(Long blockId) {
		this.blockId = blockId;
	}
	
}

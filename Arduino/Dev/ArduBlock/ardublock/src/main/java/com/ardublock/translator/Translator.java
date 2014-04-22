package com.ardublock.translator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ardublock.translator.adaptor.BlockAdaptor;
import com.ardublock.translator.adaptor.OpenBlocksAdaptor;
import com.ardublock.translator.block.TranslatorBlock;
import com.ardublock.translator.block.TranslatorBlockFactory;
import com.ardublock.translator.block.exception.SubroutineNameDuplicatedException;

import edu.mit.blocks.codeblocks.Block;
import edu.mit.blocks.renderable.RenderableBlock;
import edu.mit.blocks.workspace.Workspace;

public class Translator
{
	private static final String variablePrefix = "_ABVAR_";

	private Set<String> headerFileSet;
	private Set<String> definitionSet;
	private List<String> setupCommand;
	private Set<String> functionNameSet;
	private Set<TranslatorBlock> bodyTranslatreFinishCallbackSet;
	private BlockAdaptor blockAdaptor;
	
	private Set<String> inputPinSet;
	private Set<String> outputPinSet;
	
	private int variableCnt;
	private Map<String, String> numberVariableSet;
	private Map<String, String> booleanVariableSet;
	private Map<String, String> parameterVariableSet;
	
	private Workspace workspace;

	private String enteredSubroutine = null;
	
	
	public Translator(Workspace ws)
	{
		workspace = ws;
		reset();
	}
	
	public String genreateHeaderCommand()
	{
		StringBuilder headerCommand = new StringBuilder();
		
		if (!headerFileSet.isEmpty())
		{
			for (String file:headerFileSet)
			{
				headerCommand.append("#include <" + file + ">\n");
			}
			headerCommand.append("\n");
		}
		
		if (!definitionSet.isEmpty())
		{
			for (String command:definitionSet)
			{
				headerCommand.append(command + "\n");
			}
			headerCommand.append("\n");
		}
		
		if (!functionNameSet.isEmpty())
		{
			for (String functionName:functionNameSet)
			{
				headerCommand.append("void " + functionName + "();\n");
			}
			headerCommand.append("\n");
		}
		
		headerCommand.append("void setup()\n{\n");
		
		if (!inputPinSet.isEmpty())
		{
			for (String pinNumber:inputPinSet)
			{
				headerCommand.append("pinMode( " + pinNumber + ", INPUT);\n");
			}
		}
		if (!outputPinSet.isEmpty())
		{
			for (String pinNumber:outputPinSet)
			{
				headerCommand.append("pinMode( " + pinNumber + ", OUTPUT);\n");
			}
		}
		
		if (!setupCommand.isEmpty())
		{
			for (String command:setupCommand)
			{
				headerCommand.append(command + "\n");
			}
		}
		
		headerCommand.append("}\n\n");
		return headerCommand.toString();
	}
	
	public String translate(Long blockId) throws Exception
	{
		TranslatorBlockFactory translatorBlockFactory = new TranslatorBlockFactory();
		Block block = workspace.getEnv().getBlock(blockId);
		TranslatorBlock rootTranslatorBlock = translatorBlockFactory.buildTranslatorBlock(this, blockId, block.getGenusName(), "", "", block.getBlockLabel());
		resetSubroutineEntry();
		return rootTranslatorBlock.toCode();
	}

	private void resetSubroutineEntry () {
		enteredSubroutine = null;
	}
	public void enterSubroutine (final String subroutineName) {
		enteredSubroutine = subroutineName;
	}

	public void exitSubroutine() {
		resetSubroutineEntry();
	}

	public boolean isInSubroutine() {
		return enteredSubroutine != null; 
	}

	public BlockAdaptor getBlockAdaptor()
	{
		return blockAdaptor;
	}
	
	public void reset()
	{
		headerFileSet = new LinkedHashSet<String>();
		definitionSet = new LinkedHashSet<String>();
		setupCommand = new LinkedList<String>();
		functionNameSet = new HashSet<String>();
		inputPinSet = new HashSet<String>();
		outputPinSet = new HashSet<String>();
		bodyTranslatreFinishCallbackSet = new HashSet<TranslatorBlock>();
		
		numberVariableSet = new HashMap<String, String>();
		booleanVariableSet = new HashMap<String, String>();
		parameterVariableSet = new HashMap<String, String>();
		
		blockAdaptor = buildOpenBlocksAdaptor();
		resetSubroutineEntry();

		variableCnt = 0;
	}
	
	private BlockAdaptor buildOpenBlocksAdaptor()
	{
		return new OpenBlocksAdaptor();
	}
	
	public void addHeaderFile(String headerFile)
	{
		if (!headerFileSet.contains(headerFile))
		{
			headerFileSet.add(headerFile);
		}
	}
	
	public void addSetupCommand(String command)
	{
		if (!setupCommand.contains(command))
		{
			setupCommand.add(command);
		}
	}
	
	public void addSetupCommandForced(String command)
	{
		setupCommand.add(command);
	}
	
	public void addDefinitionCommand(String command)
	{
		definitionSet.add(command);
	}
	
	public void addInputPin(String pinNumber)
	{
		inputPinSet.add(pinNumber);
	}
	
	public void addOutputPin(String pinNumber)
	{
		outputPinSet.add(pinNumber);
	}
	
	public String getNumberVariable(String userVarName)
	{
		return numberVariableSet.get(userVarName);
	}
	
	public String getBooleanVariable(String userVarName)
	{
		return booleanVariableSet.get(userVarName);
	}

	public String getParamVariable(String paramName) {
		return parameterVariableSet.get(buildParameterName(paramName));
	}

	
	
	public void addNumberVariable(String userVarName, String internalName)
	{
		numberVariableSet.put(userVarName, internalName);
	}
	
	public void addBooleanVariable(String userVarName, String internalName)
	{
		booleanVariableSet.put(userVarName, internalName);
	}
	
	public void addParameterVariable(String qualifiedName, String paramName)
	{
		parameterVariableSet.put(qualifiedName, paramName);
	}
	
	
	public void addFunctionName(Long blockId, String functionName) throws SubroutineNameDuplicatedException
	{
		if (functionName.equals("loop") ||functionName.equals("setup") || functionNameSet.contains(functionName))
		{
			throw new SubroutineNameDuplicatedException(blockId);
		}
		
		functionNameSet.add(functionName);
	}
	
	public boolean containFunctionName(String name)
	{
		return functionNameSet.contains(name.trim());
	}
	
	
	public String buildVariableName()
	{
		return buildVariableName("");
	}
	
	public String buildVariableName(String reference)
	{
		variableCnt = variableCnt + 1;
		String varName = variablePrefix + variableCnt + "_";
		int i;
		for (i=0; i<reference.length(); ++i)
		{
			char c = reference.charAt(i);
			if (Character.isLetter(c) || Character.isDigit(c) || (c == '_'))
			{
				varName = varName + c;
			}
		}
		return varName;
	}

	public String buildParameterName(String reference)
	{
		StringBuffer varName = new StringBuffer(32);
		
		for (int i = 0; i < reference.length(); ++i)
		{
			char c = reference.charAt(i);
			if (Character.isLetterOrDigit(c) || (c == '_'))
			{
				varName.append(c);
			}
			else
				varName.append("_");
		}
		
		return enteredSubroutine + "." + varName.toString();
	}
	
	
	public Workspace getWorkspace() {
		return workspace;
	}
	
	public Block getBlock(Long blockId) {
		return workspace.getEnv().getBlock(blockId);
	}
	
	public void registerBodyTranslateFinishCallback(TranslatorBlock translatorBlock)
	{
		bodyTranslatreFinishCallbackSet.add(translatorBlock);
	}

	public void beforeGenerateHeader() {
		for (TranslatorBlock translatorBlock : bodyTranslatreFinishCallbackSet)
		{
			translatorBlock.onTranslateBodyFinished();
		}
		
	}
	
	public Set<RenderableBlock> findEntryBlocks()
	{
		Set<RenderableBlock> loopBlockSet = new HashSet<RenderableBlock>();
		Iterable<RenderableBlock> renderableBlocks = workspace.getRenderableBlocks();
		
		for (RenderableBlock renderableBlock:renderableBlocks)
		{
			Block block = renderableBlock.getBlock();
			
			if (!block.hasPlug() && (Block.NULL.equals(block.getBeforeBlockID())))
			{
				if(block.getGenusName().equals("loop"))
				{
					loopBlockSet.add(renderableBlock);
				}
				if(block.getGenusName().equals("loop1"))
				{
					loopBlockSet.add(renderableBlock);
				}
				if(block.getGenusName().equals("loop2"))
				{
					loopBlockSet.add(renderableBlock);
				}
				if(block.getGenusName().equals("loop3"))
				{
					loopBlockSet.add(renderableBlock);
				}
				if(block.getGenusName().equals("program"))
				{
					loopBlockSet.add(renderableBlock);
				}
				if(block.getGenusName().equals("setup"))
				{
					loopBlockSet.add(renderableBlock);
				}
			}
		}
		
		return loopBlockSet;
	}
	
	public Set<RenderableBlock> findSubroutineBlocks() throws SubroutineNameDuplicatedException
	{
		Set<RenderableBlock> subroutineBlockSet = new HashSet<RenderableBlock>();
		Iterable<RenderableBlock> renderableBlocks = workspace.getRenderableBlocks();
		
		for (RenderableBlock renderableBlock:renderableBlocks)
		{
			Block block = renderableBlock.getBlock();
			
			if (!block.hasPlug() && (Block.NULL.equals(block.getBeforeBlockID())))
			{
				if (block.getGenusName().startsWith("subroutine"))
				{
					String functionName = block.getBlockLabel().trim();
					this.addFunctionName(block.getBlockID(), functionName);
					subroutineBlockSet.add(renderableBlock);
				}
				
			}
		}
		
		return subroutineBlockSet;
	}
	
	public String translate(Set<RenderableBlock> loopBlocks, Set<RenderableBlock> subroutineBlocks) throws Exception
	{
		StringBuilder code = new StringBuilder();
		for (RenderableBlock renderableBlock : loopBlocks)
		{
			Block loopBlock = renderableBlock.getBlock();
			code.append(translate(loopBlock.getBlockID()));
		}
		
		for (RenderableBlock renderableBlock : subroutineBlocks)
		{
			Block subroutineBlock = renderableBlock.getBlock();
			code.append(translate(subroutineBlock.getBlockID()));
		}
		beforeGenerateHeader();
		code.insert(0, genreateHeaderCommand());
		
		return code.toString();
	}
}

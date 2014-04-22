package com.ardublock.ui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.ardublock.core.Context;
import com.ardublock.translator.AutoFormat;
import com.ardublock.translator.Translator;
import com.ardublock.translator.block.exception.BlockException;
import com.ardublock.translator.block.exception.SocketNullException;
import com.ardublock.translator.block.exception.SubroutineNameDuplicatedException;
import com.ardublock.translator.block.exception.SubroutineNeedsParamBlock;
import com.ardublock.translator.block.exception.SubroutineNotDeclaredException;
import com.ardublock.translator.block.exception.SubroutineParamNeedsGlue;
import com.ardublock.translator.block.exception.SubroutineParamNeedsVariable;
import com.ardublock.translator.block.exception.SubroutineParamNotUnique;

import edu.mit.blocks.codeblocks.Block;
import edu.mit.blocks.renderable.RenderableBlock;
import edu.mit.blocks.workspace.Workspace;

public class GenerateCodeButtonListener implements ActionListener
{
	private JFrame parentFrame;
	private Context context;
	private Workspace workspace; 
	private ResourceBundle uiMessageBundle;
	
	public GenerateCodeButtonListener(JFrame frame, Context context)
	{
		this.parentFrame = frame;
		this.context = context;
		workspace = context.getWorkspaceController().getWorkspace();
		uiMessageBundle = ResourceBundle.getBundle("com/ardublock/block/ardublock");
	}

	public void actionPerformed(ActionEvent e)
	{
		boolean success;
		success = true;
		Translator translator = new Translator(workspace);
		translator.reset();
		
		Set<RenderableBlock> loopBlockSet = translator.findEntryBlocks();
		Set<RenderableBlock> subroutineBlockSet;
		try
		{
			subroutineBlockSet = translator.findSubroutineBlocks();
		}
		catch (SubroutineNameDuplicatedException e4)
		{
			Iterable<RenderableBlock> rbs = workspace.getRenderableBlocks();
			String subroutineName = null;
			
			//determine duplicated subroutine name
			for (RenderableBlock rb : rbs)
			{
				if (rb.getBlockID() .equals(e4.getBlockId()))
				{
					subroutineName = rb.getBlock().getBlockLabel().trim();
					break;
				}
			}
			
			//highlight duplicated blocks
			for (RenderableBlock rb : rbs)
			{
				if (rb.getBlock().getBlockLabel().trim().equals(subroutineName))
				{
					context.highlightBlock(rb);
				}
			}
			
			//context.highlightBlock(renderableBlock);
			//find the second subroutine whose name is defined, and make it highlight. though it cannot happen due to constraint of OpenBlocks -_-
			JOptionPane.showMessageDialog(parentFrame, uiMessageBundle.getString("ardublock.translator.exception.subroutineNameDuplicated"), "Error", JOptionPane.ERROR_MESSAGE);
			//e4.printStackTrace();
			return ;
			
		}
		
		
		if (loopBlockSet.size() == 0) {
			JOptionPane.showMessageDialog(parentFrame, uiMessageBundle.getString("ardublock.translator.exception.noLoopFound"), "Error", JOptionPane.ERROR_MESSAGE);
			return ;
		}
		if (loopBlockSet.size() > 1) {
			for (RenderableBlock rb : loopBlockSet)
			{
				context.highlightBlock(rb);
			}
			JOptionPane.showMessageDialog(parentFrame, uiMessageBundle.getString("ardublock.translator.exception.multipleLoopFound"), "Error", JOptionPane.ERROR_MESSAGE);
			return ;
		}
		
		String code = "";

		try
		{
			code = translator.translate(loopBlockSet, subroutineBlockSet);
		}
		catch (SocketNullException e1)
		{
			//e1.printStackTrace();
			success = false;
			highlightRenderableBlock(e1.getBlockId());
			JOptionPane.showMessageDialog(parentFrame, uiMessageBundle.getString("ardublock.translator.exception.socketNull"), "Error", JOptionPane.ERROR_MESSAGE);
		}
		catch (BlockException e2)
		{
			e2.printStackTrace();
			success = false;
			highlightRenderableBlock(e2.getBlockId());
			JOptionPane.showMessageDialog(parentFrame, e2.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		catch (SubroutineNotDeclaredException e3)
		{
			//e3.printStackTrace();
			success = false;
			highlightRenderableBlock(e3.getBlockId());
			JOptionPane.showMessageDialog(parentFrame, uiMessageBundle.getString("ardublock.translator.exception.subroutineNotDeclared"), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (SubroutineParamNeedsGlue ePGlue) {
			success = false;
			highlightRenderableBlock(ePGlue.getBlockId());
			JOptionPane.showMessageDialog(parentFrame, uiMessageBundle.getString("ardublock.translator.exception.subrParameter"), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (SubroutineParamNeedsVariable ePVar) {
			success = false;
			highlightRenderableBlock(ePVar.getBlockId());
			JOptionPane.showMessageDialog(parentFrame, uiMessageBundle.getString("ardublock.translator.exception.subrParameterDecl"), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (SubroutineParamNotUnique ePUnique) {
			success = false;
			highlightRenderableBlock(ePUnique.getBlockId());
			JOptionPane.showMessageDialog(parentFrame, uiMessageBundle.getString("ardublock.translator.exception.subrParameterNotUnique"), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (SubroutineNeedsParamBlock ePBlock) {
			success = false;
			highlightRenderableBlock(ePBlock.getBlockId());
			JOptionPane.showMessageDialog(parentFrame, uiMessageBundle.getString("ardublock.translator.exception.subrParameterBlockNeeded"), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			ex.printStackTrace();
			success = false;
			JOptionPane.showMessageDialog(parentFrame, uiMessageBundle.getString("ardublock.translator.exception.subrParameter"), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		if (success)
		{
			AutoFormat formatter = new AutoFormat();
			String codeOut = code.toString();
			
			if (context.isNeedAutoFormat)
			{
				codeOut = formatter.format(codeOut);
			}
			
			if (!context.isInArduino())
			{
				System.out.println(codeOut);
			}		
			context.didGenerate(codeOut);
		}
	}

	private void highlightRenderableBlock(Long blockId) {
		Iterable<RenderableBlock> blocks = workspace.getRenderableBlocks();
		for (RenderableBlock renderableBlock : blocks)
		{
			Block block = renderableBlock.getBlock();
			if (block.getBlockID().equals(blockId))
			{
				context.highlightBlock(renderableBlock);
				break;
			}
		}
	}
}

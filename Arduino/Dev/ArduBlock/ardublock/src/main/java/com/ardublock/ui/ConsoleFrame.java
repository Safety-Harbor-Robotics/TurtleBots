package com.ardublock.ui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ConsoleFrame extends JFrame
{
	private static final long serialVersionUID = -4057010070646255290L;

	// ////////////////////////////////////////////////////////////
	public ConsoleFrame()
	{	
		this.setLayout(new FlowLayout());
		
		this.add(createButtonGenerateCode());
		this.add(createButtonSave());
		this.add(createButtonOpen());
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(200, 100);
	}

	// ////////////////////////////////////////////////////////////
	private JButton createButtonOpen() {
		JButton openButton = new JButton("open");
		//openButton.addActionListener(new OpenButtonListener(this));
		return openButton;
	}

	// ////////////////////////////////////////////////////////////
	private JButton createButtonSave() {
		JButton saveButton = new JButton("save");
		//saveButton.addActionListener(new SaveButtonListener(this));
		return saveButton;
	}

	// ////////////////////////////////////////////////////////////
	private JButton createButtonGenerateCode() {
		JButton generateButton = new JButton("generate code");
		//generateButton.addActionListener(new GenerateCodeButtonListener(this));
		return generateButton;
	}
}

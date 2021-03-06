package com.ardublock.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import processing.app.Editor;

import com.ardublock.core.Context;
import com.ardublock.ui.listener.ArdublockWorkspaceListener;
import com.ardublock.ui.listener.GenerateCodeButtonListener;
import com.ardublock.ui.listener.NewButtonListener;
import com.ardublock.ui.listener.OpenButtonListener;
import com.ardublock.ui.listener.OpenblocksFrameListener;
import com.ardublock.ui.listener.SaveAsButtonListener;
import com.ardublock.ui.listener.SaveButtonListener;

import edu.mit.blocks.controller.WorkspaceController;
import edu.mit.blocks.workspace.Workspace;

public class OpenblocksFrame extends JFrame {
	private static final long serialVersionUID = 2841155965906223806L;

	private Context context;
	private JFileChooser fileChooser;
	private FileFilter ffilter;

	private ResourceBundle uiMessageBundle;

	// ////////////////////////////////////////////////////////////
	public void addListener(OpenblocksFrameListener ofl) {
		context.registerOpenblocksFrameListener(ofl);
	}

	// ////////////////////////////////////////////////////////////
	public String makeFrameTitle() {
		String title = Context.APP_NAME + " " + context.getSaveFileName();
		if (context.isWorkspaceChanged()) {
			title = title + " *";
		}
		return title;

	}

	// ////////////////////////////////////////////////////////////
	public OpenblocksFrame() {
		context = Context.getContext();
		this.setTitle(makeFrameTitle());
		this.setSize(new Dimension(1024, 760));
		this.setLayout(new BorderLayout());
		// put the frame to the center of screen
		this.setLocationRelativeTo(null);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		uiMessageBundle = ResourceBundle
				.getBundle("com/ardublock/block/ardublock");

		fileChooser = new JFileChooser();
		ffilter = new FileNameExtensionFilter(
				uiMessageBundle.getString("ardublock.file.suffix"), "abp");
		fileChooser.setFileFilter(ffilter);
		fileChooser.addChoosableFileFilter(ffilter);

		initOpenBlocks();
	}

	// ////////////////////////////////////////////////////////////
	private void initOpenBlocks() {
		final Context context = Context.getContext();
		final Workspace workspace = createWorkspace(context);

		this.add(createTopButtons(context), BorderLayout.NORTH);
		this.add(createBottomButtons(workspace), BorderLayout.SOUTH);
		this.add(workspace, BorderLayout.CENTER);
	}

	// ////////////////////////////////////////////////////////////
	private Workspace createWorkspace(final Context context) {
		/*
		 * WorkspaceController workspaceController =
		 * context.getWorkspaceController(); JComponent workspaceComponent =
		 * workspaceController.getWorkspacePanel();
		 */

		final Workspace workspace = context.getWorkspace();

		// WTF I can't add workSpaceListener by workspace controller
		workspace.addWorkspaceListener(new ArdublockWorkspaceListener(this));
		return workspace;
	}

	// ////////////////////////////////////////////////////////////
	private JPanel createBottomButtons(final Workspace workspace) {
		JPanel bottomButtonBar = new JPanel();

		bottomButtonBar.add(createButtonSaveImage(workspace));
		bottomButtonBar.add(createButtonBrowseWebsite());
		bottomButtonBar.add(createLabelVersion());

		return bottomButtonBar;
	}

	// ////////////////////////////////////////////////////////////
	private JLabel createLabelVersion() {
		return new JLabel("v "
				+ uiMessageBundle.getString("ardublock.ui.version"));
	}

	// ////////////////////////////////////////////////////////////
	private JButton createButtonBrowseWebsite() {
		JButton websiteButton = new JButton(
				uiMessageBundle.getString("ardublock.ui.website"));
		websiteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Desktop desktop = Desktop.isDesktopSupported() ? Desktop
						.getDesktop() : null;
				URL url;
				if (desktop != null
						&& desktop.isSupported(Desktop.Action.BROWSE)) {
					try {
						url = new URL("http://ardublock.com");
						desktop.browse(url.toURI());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		return websiteButton;
	}

	// ////////////////////////////////////////////////////////////
	private JButton createButtonSaveImage(final Workspace workspace) {
		JButton saveImageButton = new JButton(
				uiMessageBundle.getString("ardublock.ui.saveImage"));
		saveImageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dimension size = workspace.getCanvasSize();
				System.out.println("size: " + size);
				BufferedImage bi = new BufferedImage(1280, 2560,
						BufferedImage.TYPE_INT_RGB);
				Graphics g = bi.createGraphics();
				workspace.getBlockCanvas().getPageAt(0).getJComponent()
						.paint(g);
				try {
					final JFileChooser fc = new JFileChooser();
					fc.setSelectedFile(new File("ardublock.jpg"));
					int returnVal = fc.showSaveDialog(workspace
							.getBlockCanvas().getJComponent());
					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						ImageIO.write(bi, "jpg", file);
					}
				} catch (Exception e1) {

				} finally {
					g.dispose();
				}
			}
		});

		return saveImageButton;
	}

	// ////////////////////////////////////////////////////////////
	private JPanel createTopButtons(final Context context) {
		JPanel topButtonBar = new JPanel();
		topButtonBar.setLayout(new FlowLayout());

		topButtonBar.add(createButtonNew());
		topButtonBar.add(createButtonSave());
		topButtonBar.add(createButtonSaveAs());
		topButtonBar.add(createButtonOpen());
		topButtonBar.add(createButtonGenerate(context));
		topButtonBar.add(createButtonSerialMonitor(context));

		return topButtonBar;
	}

	// ////////////////////////////////////////////////////////////
	private JButton createButtonSerialMonitor(final Context context) {
		JButton serialMonitorButton = new JButton(
				uiMessageBundle.getString("ardublock.ui.serialMonitor"));
		serialMonitorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Editor editor = context.getEditor();
				if (editor != null)  editor.handleSerial();
				else {
					showErrorPopUp(_("message.no.arduino.available"), context.getWorkspace());
				}
			}
		});
		return serialMonitorButton;
	}

	// ////////////////////////////////////////////////////////////
	private JButton createButtonGenerate(final Context context) {
		JButton generateButton = new JButton(
				uiMessageBundle.getString("ardublock.ui.upload"));
		generateButton.addActionListener(new GenerateCodeButtonListener(this,
				context));
		return generateButton;
	}

	// ////////////////////////////////////////////////////////////
	private JButton createButtonOpen() {
		JButton openButton = new JButton(
				uiMessageBundle.getString("ardublock.ui.load"));
		openButton.addActionListener(new OpenButtonListener(this));
		return openButton;
	}

	// ////////////////////////////////////////////////////////////
	private JButton createButtonSaveAs() {
		JButton saveAsButton = new JButton(
				uiMessageBundle.getString("ardublock.ui.saveAs"));
		saveAsButton.addActionListener(new SaveAsButtonListener(this));
		return saveAsButton;
	}

	// ////////////////////////////////////////////////////////////
	private JButton createButtonSave() {
		JButton saveButton = new JButton(
				uiMessageBundle.getString("ardublock.ui.save"));
		saveButton.addActionListener(new SaveButtonListener(this));
		return saveButton;
	}

	// ////////////////////////////////////////////////////////////
	private JButton createButtonNew() {
		JButton newButton = new JButton(
				uiMessageBundle.getString("ardublock.ui.new"));
		newButton.addActionListener(new NewButtonListener(this));
		return newButton;
	}

	// ////////////////////////////////////////////////////////////
	public void doOpenArduBlockFile() {
		if (context.isWorkspaceChanged()) {
			int optionValue = JOptionPane.showOptionDialog(this,
					uiMessageBundle.getString("message.content.open_unsaved"),
					uiMessageBundle.getString("message.title.question"),
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, null,
					JOptionPane.YES_OPTION);
			if (optionValue == JOptionPane.YES_OPTION) {
				doSaveArduBlockFile();
				this.loadFile();
			} else {
				if (optionValue == JOptionPane.NO_OPTION) {
					this.loadFile();
				}
			}
		} else {
			this.loadFile();
		}
		this.setTitle(makeFrameTitle());
	}

	// ////////////////////////////////////////////////////////////
	private void loadFile() {
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			File savedFile = fileChooser.getSelectedFile();
			if (!savedFile.exists()) {
				JOptionPane.showOptionDialog(this,
						uiMessageBundle.getString("message.file_not_found"),
						uiMessageBundle.getString("message.title.error"),
						JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null,
						null, JOptionPane.OK_OPTION);
				return;
			}

			try {
				this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
				context.loadArduBlockFile(savedFile);
				context.setWorkspaceChanged(false);
			} catch (IOException e) {
				JOptionPane.showOptionDialog(this,
						uiMessageBundle.getString("message.file_not_found"),
						uiMessageBundle.getString("message.title.error"),
						JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null,
						null, JOptionPane.OK_OPTION);
				e.printStackTrace();
			} finally {
				this.setCursor(Cursor
						.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
	}

	// ////////////////////////////////////////////////////////////
	public void doSaveArduBlockFile() {
		if (!context.isWorkspaceChanged()) {
			return;
		}

		String saveString = getArduBlockString();

		if (context.getSaveFilePath() == null) {
			chooseFileAndSave(saveString);
		} else {
			File saveFile = new File(context.getSaveFilePath());
			writeFileAndUpdateFrame(saveString, saveFile);
		}
	}

	// ////////////////////////////////////////////////////////////
	public void doSaveAsArduBlockFile() {
		if (context.isWorkspaceEmpty()) {
			return;
		}

		String saveString = getArduBlockString();

		chooseFileAndSave(saveString);

	}

	// ////////////////////////////////////////////////////////////
	private void chooseFileAndSave(String ardublockString) {
		File saveFile;

		saveFile = letUserChooseSaveFile();
		if (saveFile == null)
			return;

		saveFile = checkFileSuffix(saveFile);
		if (saveFile == null)
			return;

		if (saveFile.exists()) {
			if (didUserOkOverwriteOfExistingFile()) {
				writeFileAndUpdateFrame(ardublockString, saveFile);
			}
		}

		return;
	}

	// ////////////////////////////////////////////////////////////
	private String getArduBlockString() {
		WorkspaceController workspaceController = context
				.getWorkspaceController();
		return workspaceController.getSaveString();
	}

	// ////////////////////////////////////////////////////////////
	private void writeFileAndUpdateFrame(String ardublockString, File saveFile) {
		try {
			saveArduBlockToFile(ardublockString, saveFile);
			context.setWorkspaceChanged(false);
			this.setTitle(this.makeFrameTitle());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// ////////////////////////////////////////////////////////////
	private File letUserChooseSaveFile() {
		int chooseResult;
		chooseResult = fileChooser.showSaveDialog(this);
		if (chooseResult == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile();
		}
		return null;
	}

	// ////////////////////////////////////////////////////////////
	private boolean didUserOkOverwriteOfExistingFile() {
		int optionValue = JOptionPane.showOptionDialog(this,
				uiMessageBundle.getString("message.content.overwrite"),
				uiMessageBundle.getString("message.title.question"),
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, null, JOptionPane.YES_OPTION);
		return (optionValue == JOptionPane.YES_OPTION);
	}

	// ////////////////////////////////////////////////////////////
	private void saveArduBlockToFile(String ardublockString, File saveFile)
			throws IOException {
		context.saveArduBlockFile(saveFile, ardublockString);
		context.setSaveFileName(saveFile.getName());
		context.setSaveFilePath(saveFile.getAbsolutePath());
	}

	// ////////////////////////////////////////////////////////////
	public void doNewArduBlockFile() {
		if (context.isWorkspaceChanged()) {
			int optionValue = JOptionPane
					.showOptionDialog(
							this,
							uiMessageBundle
									.getString("message.question.newfile_on_workspace_changed"),
							uiMessageBundle.getString("message.title.question"),
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, null,
							JOptionPane.YES_OPTION);
			if (optionValue != JOptionPane.YES_OPTION) {
				return;
			}
		}
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		context.resetWorkspace();
		context.setWorkspaceChanged(false);
		this.setTitle(this.makeFrameTitle());
		this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	// ////////////////////////////////////////////////////////////
	private File checkFileSuffix(File saveFile) {
		String filePath = saveFile.getAbsolutePath();
		if (filePath.endsWith(".abp")) {
			return saveFile;
		} else {
			return new File(filePath + ".abp");
		}
	}
	
	// ////////////////////////////////////////////////////////////
	protected int showErrorPopUp (String message, Component parent) {
		return JOptionPane.showOptionDialog(parent,
				message,
				_("message.title.error"),
				JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null,
				null, JOptionPane.OK_OPTION);
	}

	// ////////////////////////////////////////////////////////////
	protected String _(final String textToTranslate) {
		return uiMessageBundle.getString(textToTranslate);
	}
	
	// ////////////////////////////////////////////////////////////
}

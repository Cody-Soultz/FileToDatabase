/**
 * 
 */
package myCustomDataBaseSolution;
//

//import java.io.IOException;
//
//import javax.swing.JFrame;
//
//public class GUIforApplication {
//	public GUIforApplication() throws IOException{
//		FilePicker application = new FilePicker();
//		application.setSize(400, 400); 
//		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		application.setVisible(true); 
//	}
//}

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GUIforApplication extends JFrame {

	private static final long serialVersionUID = 1L;

	private JTextField pinName;
	private JLabel pinText;

	private JTextField fileName;
	private JLabel fileText;

	private JButton browse;
	private JButton submit;

	private boolean goodFileName = false;
	private boolean goodPinName = false;
	
	private boolean isDirectory = false;
	private Path filePath = null;

	public GUIforApplication() {
		super("Add to Database");
		setLayout(new GridBagLayout());

		int[] objectPadding = { 0, 0, 5, 5 };

		GridBagConstraints pinTextSetup = MySetup(0, 0, 1, 4, GridBagConstraints.HORIZONTAL, objectPadding);
		pinText = new JLabel("Pin Name");
		pinText.setHorizontalAlignment(SwingConstants.LEFT);
		add(pinText, pinTextSetup);

		GridBagConstraints pinNameSetup = MySetup(0, 1, 1, 8, GridBagConstraints.HORIZONTAL, objectPadding);
		pinName = new JTextField();
		pinName.setHorizontalAlignment(SwingConstants.LEFT);
		add(pinName, pinNameSetup);

		GridBagConstraints fileTextSetup = MySetup(0, 3, 1, 4, GridBagConstraints.HORIZONTAL, objectPadding);
		fileText = new JLabel("File Name");
		fileText.setHorizontalAlignment(SwingConstants.LEFT);
		add(fileText, fileTextSetup);

		GridBagConstraints fileNameSetup = MySetup(0, 4, 1, 8, GridBagConstraints.HORIZONTAL, objectPadding);
		fileName = new JTextField("Browse for File Name");
		fileName.setEnabled(false);
		fileName.setHorizontalAlignment(SwingConstants.LEFT);
		add(fileName, fileNameSetup);

		GridBagConstraints submitSetup = MySetup(0, 5, 1, 2, GridBagConstraints.HORIZONTAL, objectPadding);
		submit = new JButton("submit");
		submit.setEnabled(false);
		submit.setHorizontalAlignment(SwingConstants.LEFT);
		add(submit, submitSetup);

		GridBagConstraints browseNameSetup = MySetup(8, 4, 1, 2, GridBagConstraints.HORIZONTAL, objectPadding);
		browse = new JButton("Browse");
		browse.setHorizontalAlignment(SwingConstants.LEFT);
		add(browse, browseNameSetup);

		pinName.addKeyListener(new pinNameKeyHandler());
		submit.addActionListener(new submitButtonHandler());
		browse.addActionListener(new browseButtonHandler());
	}

	private class pinNameKeyHandler implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			goodPinName=true;
			if (goodFileName&&goodPinName)
			{
				submit.setEnabled(true);
			}
			else
			{
				submit.setEnabled(false);
			}
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}

	}

	private class submitButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				ProcessFile myFileProcesser= new ProcessFile(pinName.getText());
				if(isDirectory){
					myFileProcesser.ProcessMultibleFiles(filePath);
				}else{
					myFileProcesser.ProcessSingleFile(filePath.toString());
				}
				myFileProcesser.closeDB();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	private class browseButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// configure dialog allowing selection of a file or directory
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
			fileChooser.showOpenDialog(new JFrame());

			// if user clicked Cancel button on dialog, return
			//if (result == JFileChooser.CANCEL_OPTION)
				//System.exit(1);
			
			Path thisFilePath = fileChooser.getSelectedFile().toPath();
			
			if (thisFilePath != null && Files.exists(thisFilePath)){
				setFilePath(thisFilePath);
				goodFileName=true;
				if (goodFileName&&goodPinName)
				{
					submit.setEnabled(true);
				}
				else
				{
					submit.setEnabled(false);
				}
			}
			else{
				goodFileName=false;
			}
		}

	}
	
	private void setFilePath(Path filePath)
	{
		this.filePath=filePath;
		fileName.setText(filePath.toString());
		if(Files.isDirectory(filePath))
		{
			setDirectoryOrFile(true);
		}
		else{
			setDirectoryOrFile(false);
		}
	}
	
	private void setDirectoryOrFile(boolean isDirectory){
		this.isDirectory=isDirectory;
	}
	
	/**
	 * @param myGridX
	 * @param myGridY
	 * @param myGridHeight
	 * @param myGridWidth
	 * @param myFill
	 * @param myInsets
	 * @return MySetup is a basically a constructor of GridBagConstraints but it
	 *         only has the variables that I care about
	 */
	private GridBagConstraints MySetup(int myGridX, int myGridY, int myGridHeight, int myGridWidth, int myFill,
			int[] myInsets) {
		GridBagConstraints mySetup = MySetup(myGridX, myGridY, myGridHeight, myGridWidth, myFill);
		if (myInsets.length == 4) {
			mySetup.insets = new Insets(myInsets[0], myInsets[1], myInsets[2], myInsets[3]);
		}
		return mySetup;
	}

	/**
	 * @param myGridX
	 * @param myGridY
	 * @param myGridHeight
	 * @param myGridWidth
	 * @param myFill
	 * @return MySetup is a basically a constructor of GridBagConstraints but it
	 *         only has the variables that I care about
	 */
	private GridBagConstraints MySetup(int myGridX, int myGridY, int myGridHeight, int myGridWidth, int myFill) {
		GridBagConstraints mySetup = MySetup(myGridX, myGridY, myGridHeight, myGridWidth);
		mySetup.fill = myFill;
		return mySetup;
	}

	/**
	 * @param myGridX
	 * @param myGridY
	 * @param myGridHeight
	 * @param myGridWidth
	 * @return MySetup is a basically a constructor of GridBagConstraints but it
	 *         only has the variables that I care about
	 */
	private GridBagConstraints MySetup(int myGridX, int myGridY, int myGridHeight, int myGridWidth) {
		GridBagConstraints mySetup = new GridBagConstraints();
		mySetup.gridx = myGridX;
		mySetup.gridy = myGridY;
		mySetup.gridheight = myGridHeight;
		mySetup.gridwidth = myGridWidth;
		return mySetup;
	}
}
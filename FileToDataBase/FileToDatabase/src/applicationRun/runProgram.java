/**
 * 
 */
package applicationRun;

import java.io.IOException;

import javax.swing.JFrame;

import myCustomDataBaseSolution.GUIforApplication;

/**
 * @author Cody Soultz
 *
 */
public class runProgram {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		GUIforApplication Application = new GUIforApplication(); 
		Application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Application.setSize(300, 230); 
		Application.setVisible(true); 
	}

}

package my_package;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Message extends JFrame implements Serializable {
	
	//
	JPanel panel = new JPanel();
	
	JLabel errorMessage ;
	JButton OK;
	
	private GUI parentGUI;
	
	public Message(String name, GUI parentGUI) {
		this.parentGUI = parentGUI;
		
		this.setVisible(true);
		this.setSize(200, 100);
		this.setTitle("Message");
		
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		errorMessage = new JLabel("User "+ name +" Exists already");
		OK = new JButton("OK");
		
		panel.add(errorMessage);
		panel.add(OK);
		
		this.setContentPane(panel);
		
		OKButton buttonListener = new OKButton();
		OK.addActionListener(buttonListener);
	}
	
	class OKButton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Message.this.dispose(); 
			
			if (parentGUI != null) {
				parentGUI.setVisible(true);
		}
		
	}
}
	
}

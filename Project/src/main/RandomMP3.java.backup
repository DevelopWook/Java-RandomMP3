package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;

public class RandomMP3 extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RandomMP3 frame = new RandomMP3();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RandomMP3() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 515, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSource = new JLabel("Source");
		lblSource.setBounds(12, 10, 57, 15);
		contentPane.add(lblSource);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setText("\uB178\uB798\uB97C \uBF51\uC744 \uACF3\uC744 \uC120\uD0DD\uD558\uC138\uC694.");
		textField.setBounds(81, 10, 289, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblDestnation = new JLabel("Destnation");
		lblDestnation.setBounds(12, 43, 69, 15);
		contentPane.add(lblDestnation);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setText("\uBCF5\uC0AC\uB420 \uACF3\uC744 \uC120\uD0DD\uD558\uC138\uC694");
		textField_1.setBounds(81, 41, 289, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("\uC120\uD0DD");
		btnNewButton.setBounds(382, 10, 97, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\uC120\uD0DD");
		btnNewButton_1.setBounds(382, 39, 97, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblSize = new JLabel("Size");
		lblSize.setBounds(12, 88, 57, 15);
		contentPane.add(lblSize);
		
		textField_2 = new JTextField();
		textField_2.setBounds(81, 85, 116, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblGb = new JLabel("MB");
		lblGb.setBounds(209, 88, 57, 15);
		contentPane.add(lblGb);
		
		JList list = new JList();
		list.setBounds(12, 139, 342, 346);
		contentPane.add(list);
		
		JButton btnNewButton_2 = new JButton("\uB9AC\uC2A4\uD2B8 \uBF51\uAE30");
		btnNewButton_2.setBounds(382, 179, 97, 21);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("\uBCF5\uC0AC \uC9C4\uD589");
		btnNewButton_3.setBounds(382, 268, 97, 23);
		contentPane.add(btnNewButton_3);
		
		JLabel lblgbmb = new JLabel("1GB = 1024MB");
		lblgbmb.setBounds(209, 114, 90, 15);
		contentPane.add(lblgbmb);
	}
}

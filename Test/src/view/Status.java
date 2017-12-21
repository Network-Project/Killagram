package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import server.User;

public class Status extends JPanel {

	public Status(User user) {
		initGUI(user);
	}
	
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(new ImageIcon("image/main1.jpg").getImage(), 0, 0, getWidth(), getHeight(), this);
        setOpaque(false);
        super.paintComponent(g);
    }
    
	private void initGUI(User user) {
		JTextField IDField = Utility.createTextField(user.getId(), 150, 122, 100, 30, false, Color.BLACK);
		IDField.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		JTextField NameField = Utility.createTextField(user.getName(), 150, 177, 100, 30, false, Color.BLACK);
		NameField.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		JTextField AgeField = Utility.createTextField(String.valueOf(user.getAge()), 150, 230, 100, 30, false, Color.BLACK);
		AgeField.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		JTextField GenderField = Utility.createTextField(user.getGender(), 150, 286, 100, 30, false,Color.BLACK);
		GenderField.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		JTextField HeightField = Utility.createTextField(String.valueOf(user.getHeight()), 150, 340, 100, 30, false,Color.BLACK);
		HeightField.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		JTextField WeightField = Utility.createTextField(String.valueOf(user.getWeight()), 153, 393, 100, 30, false,Color.BLACK);
		WeightField.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 500, 500);
		layeredPane.setLayout(null);

		layeredPane.add(IDField);
		layeredPane.add(NameField);
		layeredPane.add(AgeField);
		layeredPane.add(GenderField);
		layeredPane.add(HeightField);
		layeredPane.add(WeightField);
		this.add(layeredPane);
		this.setVisible(true);
		this.setLayout(null);
		this.setSize(500,500);

	}
}
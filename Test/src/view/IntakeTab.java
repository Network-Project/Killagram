package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import server.Food;
import server.IntakeInfo;
import view.Main.MainListener;

public class IntakeTab extends JPanel {
	private MainListener listener;
	private Vector<IntakeInfo> infoList = null;
	private FoodFrame foodFrame = null;
	JTable food_table;
	DefaultTableModel model = null;
	JPanel table_panel;
	IntakeTab parent;
	
	 @Override
	    protected void paintComponent(Graphics g) {
	        g.drawImage(new ImageIcon("image/main2.jpg").getImage(), 0, 0, getWidth(), getHeight(), this);
	        setOpaque(false);
	        super.paintComponent(g);
	    }
	
	public FoodFrame getFoodFrame() {
		return foodFrame;
	}
	

	public IntakeTab(MainListener listener) {
		this.listener = listener;
		parent = this;
		JButton jbt = Utility.createButton("추가", 410, 535, 60, 30);
		jbt.setForeground(Color.WHITE);
		table_panel = new JPanel();
		table_panel.setLayout(null);
		table_panel.setBounds(0, 0, 500, 533);
		table_panel.setOpaque(false);

		jbt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				foodFrame = new FoodFrame(listener,parent);
			}

		});
		this.add(jbt);
		this.add(table_panel);
		this.setSize(500, 550);
		this.setLayout(null);
		this.setVisible(true);
	}

	public void insertIntake(Food food, int quantity) {
		System.out.println(food.getName());
		listener.insertIntakeInfo(food, quantity);
		Vector<String> vec = new Vector<String>();
		vec.addElement(food.getName());
		vec.addElement(quantity + food.getUnit().substring(1));
		vec.addElement(String.valueOf(quantity*food.getKal()));
		model.addRow(vec);
		revalidate();
	}
	
	
	public void setIntakeList(Vector<IntakeInfo> infoList) {
			this.infoList = infoList;
	}

	
	public void showIntakeList() {
		Vector<String> column = new Vector<String>();
		column.addElement("음식이름");
		column.addElement("양");
		column.addElement("칼로리");
		model = new DefaultTableModel(column, 0);
		food_table = new JTable(model);
		food_table.setBackground(Color.WHITE);
		for (int i = 0; i < infoList.size(); i++) {
			Vector<String> vec = new Vector<String>();
			vec.addElement(infoList.get(i).getName());
			vec.addElement(infoList.get(i).getQuantity() + infoList.get(i).getUnit().substring(1));
			vec.addElement(String.valueOf(infoList.get(i).getKal()));
			model.addRow(vec);
		}
		JScrollPane scroll = new JScrollPane(food_table);
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.setBounds(20, 81, 454, 441);
		table_panel.add(scroll);
	}

}

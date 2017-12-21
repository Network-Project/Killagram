package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import server.Food;
import view.Main.MainListener;

public class FoodFrame extends JFrame implements ChangeListener {
	private MainListener listener;
	private IntakeTab parent;
	private JTabbedPane childTab;
	private HashMap<String, FoodTab> tabs;
	private FoodTab currentTab;

	private int width;

	public FoodFrame(MainListener listener, IntakeTab parent) {
		this.listener = listener;
		this.parent = parent;
		JTextField unit = Utility.createTextField(270, 515, 100, 30, true, Color.BLACK);
		JButton jbt = Utility.createButton("추가", 390, 515, 60, 30);
		jbt.setBorderPainted(true);
		jbt.setFocusPainted(true);
		jbt.setContentAreaFilled(true);
		JLabel count = Utility.createLabel("수량", 200, 515, 60, 30);
		childTab = new JTabbedPane(JTabbedPane.LEFT);
		tabs = new HashMap<String, FoodTab>();
		tabs.put("과일류", new FoodTab());
		tabs.put("구이_튀김_전류", new FoodTab());
		tabs.put("국_찌개류", new FoodTab());
		tabs.put("김치_장아찌_젓갈류", new FoodTab());
		tabs.put("밥_죽_떡류", new FoodTab());
		tabs.put("양식", new FoodTab());
		tabs.put("일식_중식", new FoodTab());
		tabs.put("일품요리_분식", new FoodTab());
		tabs.put("조림_볶음류", new FoodTab());
		tabs.put("주류", new FoodTab());

		for (Map.Entry<String, FoodTab> tab : tabs.entrySet()) {
			childTab.addTab(tab.getKey(), tab.getValue());
		}
		childTab.setBounds(20, 20, 500, 600);
		childTab.addChangeListener(this);
		jbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FoodTab currentTab = (FoodTab) childTab.getSelectedComponent();
				int current = currentTab.getFood_table().getSelectedRow();
				if(unit.getText().length()!=0) {
				parent.insertIntake(currentTab.getFoodList().get(current), Integer.parseInt(unit.getText()));
				dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "수량을 입력해주세요");
				}
				
			}

		});

		this.add(count);
		this.add(jbt);
		this.add(unit);
		this.add(childTab);
		this.setSize(500, 600);
		setBounds(100, 100, 500, 600);
		this.setVisible(true);
		this.setLayout(null);
		showFoodList("김치_장아찌_젓갈류");
	}

	@Override
	public void stateChanged(ChangeEvent ce) {
		JTabbedPane tab = (JTabbedPane) ce.getSource();
		showFoodList(tab.getTitleAt(tab.getSelectedIndex()));
	}

	public void setFoodList(String tableName, Vector<Food> foodList) {
		if (tabs.containsKey(tableName)) {
			FoodTab tab = tabs.get(tableName);
			tab.setFoodList(foodList);
			tab.removeAll();
			JScrollPane scroll = tab.showList();
			scroll.setPreferredSize(new Dimension(325, 500));
			tab.add(scroll);
			revalidate();
		}

	}

	public void showFoodList(String tableName) {
		if (tabs.containsKey(tableName)) {
			FoodTab tab = tabs.get(tableName);
			if (tab.getFoodList() == null) { // 최초요청
				listener.requestFoodList(tableName);
			} else {
				tab.add(tab.showList());
				revalidate();
			}
		}
	}

	private class FoodTab extends JPanel {
		private Vector<Food> foodList;
		JTable food_table;
		DefaultTableModel model = null;
		
		public JTable getFood_table() {
			return food_table;
		}

		public Vector<Food> getFoodList() {
			return foodList;
		}

		public void setFoodList(Vector<Food> foodList) {
			this.foodList = foodList;
		}

		public JScrollPane showList() {
			JScrollPane scroll = null;
			if (foodList != null) {
				Vector<String> column = new Vector<String>();
				column.addElement("음식이름");
				column.addElement("단위");
				column.addElement("칼로리");
				model = new DefaultTableModel(column, 0);
				food_table = new JTable(model);
				for (int i = 0; i < foodList.size(); i++) {
					Vector<String> vec = new Vector<String>();
					vec.addElement(foodList.get(i).getName());
					vec.addElement(foodList.get(i).getUnit());
					vec.addElement(String.valueOf(foodList.get(i).getKal()));
					model.addRow(vec);
				}
				scroll = new JScrollPane(food_table);
			}
			return scroll;
		}

	}
}
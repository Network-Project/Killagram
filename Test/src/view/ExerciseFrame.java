package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.table.DefaultTableModel;

import server.Exercise;
import view.Main.MainListener;

public class ExerciseFrame extends JFrame{
	private MainListener listener;
	private SportTab parent;
	private Vector<Exercise> exerciseList;
	private JTabbedPane childTab;
	
	private Vector<Exercise> foodList;
	JTable exercise_table;
	DefaultTableModel model = null;
	
	private JPanel tab;
	public ExerciseFrame(MainListener listener, SportTab parent) {
		super();
		this.listener = listener;
		this.parent = parent;
		childTab = new JTabbedPane(JTabbedPane.LEFT);
		JTextField unit = Utility.createTextField(200, 515, 100, 30, true, Color.BLACK);
		JButton jbt = Utility.createButton("추가", 310, 515, 60, 30);
		jbt.setBorderPainted(true);
		jbt.setFocusPainted(true);
		jbt.setContentAreaFilled(true);
		JLabel hour = Utility.createLabel("시간", 130, 515, 60, 30);
		jbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(unit.getText().length()!=0) {
					int current = exercise_table.getSelectedRow();
					parent.insertExerciseInfo(getExerciseList().get(current), Integer.parseInt(unit.getText()));
					dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "시간을 입력해주세요");
					}
			}

		});
		tab = new JPanel();
		childTab.setBounds(20, 20, 500, 600);
		this.add(hour);
		this.add(jbt);
		this.add(unit);
		this.add(childTab);
		this.setSize(500, 600);
		setBounds(100, 100, 500, 600);
		this.setVisible(true);
		this.setLayout(null);
		listener.requestExerciseList();
	}

	public Vector<Exercise> getExerciseList() {
		return exerciseList;
	}


	public void setExerciseList(Vector<Exercise> exerciseList) {
		this.exerciseList = exerciseList;
	}


	public void showExerciseList() {
		JScrollPane scroll = null;
		if(exerciseList != null) {
			System.out.println(exerciseList.size());
			Vector<String> column = new Vector<String>();
			column.addElement("운동이름");
			column.addElement("MET");
			model = new DefaultTableModel(column, 0);
			exercise_table = new JTable(model);
			for (int i = 0; i < exerciseList.size(); i++) {
				Vector<String> vec = new Vector<String>();
				vec.addElement(exerciseList.get(i).getName());
				vec.addElement(String.valueOf(exerciseList.get(i).getMET()));
				model.addRow(vec);
			}
			scroll = new JScrollPane(exercise_table);
			childTab.add("운동", scroll);
			revalidate();
		}
	}
		
}

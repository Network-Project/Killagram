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

import server.Exercise;
import server.ExerciseInfo;
import view.Main.MainListener;

public class SportTab extends JPanel {
	private MainListener listener;
	private Vector<ExerciseInfo> infoList = null;
	private ExerciseFrame exerciseFrame= null;
	SportTab parent;
	JTable exerciseInfo_table;
	DefaultTableModel model = null;
	JPanel table_panel;
	
	@Override
    protected void paintComponent(Graphics g) {
        g.drawImage(new ImageIcon("image/main2.jpg").getImage(), 0, 0, getWidth(), getHeight(), this);
        setOpaque(false);
        super.paintComponent(g);
    }
	
	public ExerciseFrame getExerciseFrame() {
		return exerciseFrame;
	}

	public SportTab(MainListener listener) {
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
				exerciseFrame = new ExerciseFrame(listener,parent);
			}

		});
		this.add(jbt);
		this.add(table_panel);
		this.setSize(500, 550);
		this.setLayout(null);
		this.setVisible(true);
	}
	
	public void insertExerciseInfo(Exercise exercise, float time) {
		System.out.println(exercise.getName());
		Vector<String> vec = listener.insertExerciseInfo(exercise, time);
		model.addRow(vec);
		revalidate();
	}
	
	public void setExerciseInfoList(Vector<ExerciseInfo> infoList) {
			this.infoList = infoList;
	}
	
	public void showExerciseInfoList() {
		Vector<String> column = new Vector<String>();
		column.addElement("운동이름");
		column.addElement("시간");
		column.addElement("소모칼로리");
		model = new DefaultTableModel(column, 0);
		exerciseInfo_table = new JTable(model);
		for (int i = 0; i < infoList.size(); i++) {
			Vector<String> vec = new Vector<String>();
			vec.addElement(infoList.get(i).getName());
			vec.addElement(String.valueOf(infoList.get(i).getTime()));
			vec.addElement(String.valueOf(infoList.get(i).getKal()));
			model.addRow(vec);
		}
		JScrollPane scroll = new JScrollPane(exerciseInfo_table);
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.setBounds(20, 81, 454, 441);
		table_panel.add(scroll);
	}
}

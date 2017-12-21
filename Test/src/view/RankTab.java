package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import view.Main.MainListener;

public class RankTab extends JPanel {
	private MainListener listener;
	DefaultTableModel model = null;
	HashMap<String, Float> rank = null;
	JTable rank_table = null;
	
	@Override
    protected void paintComponent(Graphics g) {
        g.drawImage(new ImageIcon("image/main2.jpg").getImage(), 0, 0, getWidth(), getHeight(), this);
        setOpaque(false);
        super.paintComponent(g);
    }

	public HashMap<String, Float> getRank() {
		return rank;
	}

	public void setRank(HashMap<String, Float> rank) {
		this.rank = rank;
	}

	public RankTab(MainListener listener) {
		this.listener =listener;
		this.setSize(409, 668);
		this.setVisible(true);
		this.setLayout(null);
	}

	public void showRankList() {
		int rankNum = 1;
		Vector<String> column = new Vector<String>();
		column.addElement("순위");
		column.addElement("ID");
		column.addElement("소모 칼로리");
		model = new DefaultTableModel(column, 0);
		rank_table = new JTable(model);
		Iterator it = sortByValue(rank).iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			Vector<String> row = new Vector<String>();
			row.addElement(String.valueOf(rankNum));
			rankNum++;
			row.addElement(key);
			row.addElement(String.valueOf(rank.get(key)));
			model.addRow(row);
		}
		JScrollPane scroll = new JScrollPane(rank_table);
		scroll.getViewport().setBackground(Color.WHITE);
		scroll.setBounds(20, 81, 454, 441);
		this.add(scroll);
	}

	public static List sortByValue(final Map map) {
		List<String> list = new ArrayList();
		list.addAll(map.keySet());
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				Object v1 = map.get(o1);
				Object v2 = map.get(o2);
				return ((Comparable) v1).compareTo(v2);

			}
		});
		Collections.reverse(list);
		return list;
	}
}
package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import server.Exercise;
import server.Food;
import server.User;

public class Main extends JFrame implements ChangeListener {

	BufferedImage img = null;
	 Image resizeImage = null;
	JTabbedPane jtp = null;
	JPanel status_pan = null;
	private IntakeTab intakeTab;
	private SportTab sportTab;
	private RankTab rankTab;

	public SportTab getSportTab() {
		return sportTab;
	}

	public RankTab getRankTab() {
		return rankTab;
	}

	private MainListener listener;

	public interface MainListener {
		User getUser();

		void requestFoodList(String tableName);

		void requestIntakeList();

		Vector<String> insertExerciseInfo(Exercise exercise, float time);

		void insertIntakeInfo(Food food, int quantity);

		void requestExerciseInfoList();

		void requestExerciseList();

		void requestRankList();
	}

	public IntakeTab getIntakeTab() {
		return intakeTab;
	}

	public Main(MainListener listener) {
		this.listener = listener;
		initGUI();
	}

	public void initGUI() {
		
		jtp = new JTabbedPane();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 종료하고 자원 반환

		// 패널 설정

		jtp.setBounds(0, 0, 500, 600);

		// 탭설정
		status_pan = new status_pan("Test");

		// 음식
		intakeTab = new IntakeTab(listener);
		jtp.add("섭취현황", intakeTab);
		// 스포츠
		sportTab = new SportTab(listener);
		jtp.addTab("운동현황", sportTab);

		// 랭킹
		rankTab = new RankTab(listener);
		jtp.addTab("랭킹", rankTab);

		this.setLayout(null);
		this.setLocation(600, 200);
		this.getContentPane().add(jtp);
		this.setTitle("Kill a Gram");
		this.setSize(520, 650);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jtp.addChangeListener(this);
	}

	class status_pan extends JPanel {

		public status_pan(String id) {
			jtp.add("STATUS", new Status(listener.getUser()));

		}
	}

	@Override
	public void stateChanged(ChangeEvent ce) {
		// TODO Auto-generated method stub
		JTabbedPane tab = (JTabbedPane) ce.getSource();

		switch (tab.getSelectedIndex()) {
		case 1: {
			listener.requestIntakeList();
			break;
		}
		case 2: {
			listener.requestExerciseInfoList();
			break;
		}
		case 3: {
			listener.requestRankList();
			break;
		}
		}

	}
	class Mypanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(resizeImage, 0, 0, null);
		}
	}
}

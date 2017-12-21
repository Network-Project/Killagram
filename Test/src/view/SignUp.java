package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import server.User;
import view.Login.LoginListener;

public class SignUp extends JFrame {

   BufferedImage img = null;
   Image resizeImage = null;
   JTextField IDTextField;
   JTextField AgeTextField;
   JTextField NameTextField;
   JTextField HeightTextField;
   JTextField WeightTextField;
   JTextField GoalTextField;
   JPasswordField passwordField;
   JPasswordField passwordConfirmField;
   JRadioButton rdbt1, rdbt2;
   JButton Ybt;
   JButton Nbt;
   JButton checkDuplicate;
   ButtonGroup genderButton = new ButtonGroup();
   String s;

   private boolean duplicate = true;
   private LoginListener listener;

   public SignUp(LoginListener listener) {
      this.listener = listener;
      initGUI();
   }

   public void initGUI() {
      this.setTitle("회원가입");
      this.setSize(400, 690);
      this.setLocation(1000,200);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      // 레이아웃
      this.setLayout(null);
      JLayeredPane layeredPane = new JLayeredPane();
      layeredPane.setBounds(0, 0, 500, 700);
      layeredPane.setLayout(null);

      try {
         img = ImageIO.read(new File("image/signup.jpg")); // 이미지 변경부분
      } catch (IOException e) {
         System.out.println("Fail to load image");
         System.exit(0);
      }
      
      resizeImage = img.getScaledInstance(400, 650, Image.SCALE_SMOOTH);
      
      // 패널
      Mypanel panel = new Mypanel();
      panel.setBounds(0, 0, 400, 700);
      // 아이디
      IDTextField = Utility.createTextField(120, 135, 100, 30, false, Color.white);
      IDTextField.setFont(new Font("맑은 고딕", Font.BOLD, 17));
      layeredPane.add(IDTextField); // 패널에 놓기

      // 비밀번호
      passwordField = Utility.createPasswordField(120, 185, 100, 30, false, Color.white);
      passwordField.setFont(new Font("맑은 고딕", Font.BOLD, 17));
      layeredPane.add(passwordField);
      // 비밀번호확인
      passwordConfirmField = Utility.createPasswordField(120, 235, 100, 30, false, Color.white);
      passwordConfirmField.setFont(new Font("맑은 고딕", Font.BOLD, 17));
      layeredPane.add(passwordConfirmField);
      // 이름
      NameTextField = Utility.createTextField(120, 290, 100, 30, false, Color.white);
      NameTextField.setFont(new Font("맑은 고딕", Font.BOLD, 17));
      layeredPane.add(NameTextField);
      // 나이
      AgeTextField = Utility.createTextField(120, 345, 100, 30, false, Color.white);
      AgeTextField.setFont(new Font("맑은 고딕", Font.BOLD, 17));
      layeredPane.add(AgeTextField);
      // 성
      JRadioButton rdbt1= Utility.createRadioButton("F", 120, 400, 35, 31, false, Color.WHITE);
      JRadioButton rdbt2 = Utility.createRadioButton("M", 160,400, 40, 31, false, Color.WHITE);
      // 버튼 그룹화

      genderButton.add(rdbt1);
      genderButton.add(rdbt2);
     
      layeredPane.add(rdbt1);
      layeredPane.add(rdbt2);

      // 키
      HeightTextField = Utility.createTextField(120, 450, 100, 30, false, Color.white);
      HeightTextField.setFont(new Font("맑은 고딕", Font.BOLD, 17));
      layeredPane.add(HeightTextField);
      // 몸무게
      WeightTextField =  Utility.createTextField(120, 500, 100, 30, false, Color.white);
      WeightTextField.setFont(new Font("맑은 고딕", Font.BOLD, 17));
      layeredPane.add(WeightTextField);
      // 목표
      GoalTextField =  Utility.createTextField(120, 550, 100, 30, false, Color.white);
      GoalTextField.setFont(new Font("맑은 고딕", Font.BOLD, 20));
      layeredPane.add(GoalTextField);

      // 확인버튼
      Ybt = Utility.createButton("", 60, 610, 115, 23);
      layeredPane.add(Ybt);// 패널에 넣기

      // 취소버튼
      Nbt = Utility.createButton("", 220, 610, 115, 23);   
      layeredPane.add(Nbt);// 패널에 넣기

      // 중복확인
      checkDuplicate=Utility.createButton("", 270, 120, 90, 30);
      checkDuplicate.setBounds(270, 120, 90, 30);
      layeredPane.add(checkDuplicate);

      layeredPane.add(panel);
      add(layeredPane);
      setVisible(true);

      Nbt.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            dispose();
         }

      });

      Ybt.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            String id = IDTextField.getText();
            String temp_age = AgeTextField.getText();
            int age = Integer.parseInt(temp_age);

            String name = NameTextField.getText();

            String temp_height = HeightTextField.getText();
            float height = Float.parseFloat(temp_height);

            String temp_weight = WeightTextField.getText();
            float weight = Float.parseFloat(temp_weight);

            String temp_goal = GoalTextField.getText();
            float goal = Float.parseFloat(temp_goal);

            char[] pass = passwordField.getPassword();
            String pw = new String(pass);

            char[] passconfirm = passwordConfirmField.getPassword();
            String pwconf = new String(passconfirm);

            Enumeration<AbstractButton> enums = genderButton.getElements();
            String gender = null;

            while (enums.hasMoreElements()) {
               AbstractButton ab = enums.nextElement();
               JRadioButton jb = (JRadioButton) ab;

               if (jb.isSelected())
                  gender = jb.getText().trim();
            }

            if (id.equals("")) {
               JOptionPane.showMessageDialog(null, "아이디 입력칸이 비었습니다");
            } else if (temp_age.equals("")) {
               JOptionPane.showMessageDialog(null, "나이 입력칸이 비었습니다");
            } else if (name.equals("")) {
               JOptionPane.showMessageDialog(null, "이름 입력칸이 비었습니다");
            } else if (temp_height.equals("")) {
               JOptionPane.showMessageDialog(null, "키 입력칸이 비었습니다");
            } else if (temp_weight.equals("")) {
               JOptionPane.showMessageDialog(null, "몸무게 입력칸이 비었습니다");
            } else if (temp_goal.equals("")) {
               JOptionPane.showMessageDialog(null, "목표 칼로리 입력칸이 비었습니다");
            } else if (pw.equals("")) {
               JOptionPane.showMessageDialog(null, "비밀번호 입력칸이 비었습니다");
            } else if (pwconf.equals("")) {
               JOptionPane.showMessageDialog(null, "비밀번호확인 입력칸이 비었습니다");
            } else if (pw.equals(pwconf) == false) {
               JOptionPane.showMessageDialog(null, "비밀번호와 비밀번호 확인 입력칸이 다릅니다");
            } else {
               if (!duplicate) {
                  boolean valid = listener
                        .onClickSignUp(new User(id, pw, name, age, gender, height, weight, goal));
                  if (valid) {
                     JOptionPane.showMessageDialog(null, "회원가입을 축하합니다");
                     dispose();
                  } else {
                     JOptionPane.showMessageDialog(null, "회원가입이 실패했습니다");
                  }
               }
               else {
                  JOptionPane.showMessageDialog(null, "중복체크를 해주세요");
               }
            }
         }
      });

      checkDuplicate.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            if (IDTextField.getText().length() > 0) {
               duplicate = listener.onCheckDuplicate(IDTextField.getText());
               if (duplicate) {
                  JOptionPane.showMessageDialog(null, "이미 사용중인 아이디 입니다");
               } else {
                  JOptionPane.showMessageDialog(null, "시용가능 한 아이디 입니다");
               }
            } else {
               JOptionPane.showMessageDialog(null, "아이디를 입력하세요");
            }
         }
      });

   }

   class Mypanel extends JPanel {
      public void paint(Graphics g) {
         g.drawImage(resizeImage, 0, 0, null);
      }
   }

}
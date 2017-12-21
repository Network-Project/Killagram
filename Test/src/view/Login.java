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

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import server.User;

public class Login extends JFrame {


   BufferedImage img = null;
   Image resizeImage = null;
   JTextField loginTextField;
   JPasswordField passwordField;
   JButton lgbt;
   JButton sgbt;
   String idpw;
   String id, pw;
   public boolean flag = true;

   LoginListener listener;

   // main
   String S;

   public interface LoginListener {
      boolean onClickLogin(String id, String password);

      boolean onClickSignUp(User user);

      boolean onCheckDuplicate(String id);
   }

   public Login(LoginListener listener) {
      this.listener = listener;
      initGUI();
   }

   public void initGUI() {
	  
      setTitle("Kill a Gram");
      setSize(520, 750);
      setLocation(600,200);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      setLayout(null);
      JLayeredPane layeredPane = new JLayeredPane();
      layeredPane.setBounds(0, 0, 500, 700);
      layeredPane.setLayout(null);

      try {
         img = ImageIO.read(new File("image/login.jpg"));
      } catch (IOException e) {
         System.out.println("Fail to load image");
         System.exit(0);
      }
      resizeImage = img.getScaledInstance(500, 700, Image.SCALE_SMOOTH);

      Mypanel panel = new Mypanel();
      panel.setBounds(0, 0, 500, 700);

      loginTextField = Utility.createTextField(15, 150, 434, 200, 25, false, Color.WHITE);
      loginTextField.setFont(new Font("맑은 고딕", Font.BOLD, 20));
      layeredPane.add(loginTextField);

      passwordField = Utility.createPasswordField(150, 470, 200, 25, false, Color.WHITE);
      passwordField.setFont(new Font("맑은 고딕", Font.BOLD, 20));
      layeredPane.add(passwordField);

      lgbt = Utility.createButton("", 100, 565, 350, 35);
      layeredPane.add(lgbt);

      sgbt = Utility.createButton("", 100, 620, 350, 35);
      layeredPane.add(sgbt);

      // action login
      lgbt.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            String id = loginTextField.getText();
            pw = new String(passwordField.getPassword());
            if (id.equals("") || pw.equals("")) {
               // send message
               JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 입력하세요");
            } else {
               if (!listener.onClickLogin(id, pw)) {
                  JOptionPane.showMessageDialog(null, "아이디와 비밀번호를 확인해주세요");
               }

            }
         }

      });

      // action sign up
      sgbt.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent arg0) {
            // TODO Auto-generated method stub
            new SignUp(listener);
         }

      });
      // add
      layeredPane.add(panel);
      add(layeredPane);
      setVisible(true);
   }

   class Mypanel extends JPanel {
      public void paint(Graphics g) {
         g.drawImage(resizeImage, 0, 0, null);
      }
   }
}
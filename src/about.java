import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JDialog;

public class about extends JDialog {
  JLabel jLabel1 = new JLabel();
  JTextArea jTextArea1 = new JTextArea();
  JButton jButton1 = new JButton();

  public about() {
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    //ImageIcon logo=new ImageIcon("picture/jnbus.jpg");
    jLabel1.setText("�����й�����ѯϵͳ");
    jLabel1.setBounds(new Rectangle(100, 30, 150, 29));
    this.getContentPane().setLayout(null);
    //this.getContentPane().setBackground(SystemColor.inactiveCaption);
    //jTextArea1.setBackground(SystemColor.inactiveCaption);
    //jTextArea1.setFont(new java.awt.Font("Serif", 0, 12));
    //jTextArea1.setEditable(false);
    //jTextArea1.append("������ѯϵͳ ");
    //jTextArea1.setBounds(new Rectangle(116, 2, 140, 58));
    //jButton1.setBackground(SystemColor.inactiveCaption);
    jButton1.setBounds(new Rectangle(115, 85, 84, 25));
    //jButton1.setFont(new java.awt.Font("Serif", 0, 12));
    jButton1.setText("ȷ��");
    jButton1.addActionListener(new about_jButton1_actionAdapter(this));
    this.getContentPane().add(jLabel1, null);
    //this.getContentPane().add(jTextArea1, null);
    this.getContentPane().add(jButton1, null);

    //jLabel1.setIcon(logo);
  }

  void jButton1_actionPerformed(ActionEvent e) {
    this.dispose();
  }
}

class about_jButton1_actionAdapter implements java.awt.event.ActionListener {
  about adaptee;
  about_jButton1_actionAdapter(about adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

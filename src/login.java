import java.awt.*;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;  
import javax.swing.JLabel;  
import javax.swing.JPanel;  
import javax.swing.JPasswordField;  
import javax.swing.JTextField;  
import javax.swing.JDialog;
 
public class login extends JDialog
{  
	clientFrame c;
	connectDB con = new connectDB(); //�����������ݿ����һ������
	JButton login = new JButton("��¼");  
	JButton exit = new JButton("ȡ��");  
	JLabel  name = new JLabel("�û�����");
	JLabel Jpassword = new JLabel("���룺"); 
	JLabel  check = new JLabel();
	JTextField JName = new JTextField(10); //�����˺�����  
	JPasswordField JPassword = new JPasswordField(10); // �������������룻  
	JPanel login1 = new JPanel();  
	String managername;
	String password;
    public login(clientFrame d)   
    {  
    	c=d;
    	try {
    	      Init();
    	    }
    	    catch(Exception e) {
    	      e.printStackTrace();
    	    }
    }  
    private void Init() throws Exception {
    	   
    	 
    	 name.setBounds(new Rectangle(60, 35, 60, 29));
    	 
    	 Jpassword.setBounds(new Rectangle(60, 75, 60, 29));
    	 
    	 JName.setBounds(new Rectangle(120, 35, 100, 29));
    	 
    	 JPassword.setBounds(new Rectangle(120, 75, 100, 29));
    	 
    	 exit.setBounds(new Rectangle(60, 120, 80, 29));
    	 login.setBounds(new Rectangle(140, 120, 80, 29));
    	 
    	 check.setBounds(new Rectangle(85, 5, 150, 29));
    	 
    	 login1.setLayout(null);
         login1.add(name);   //�����ݼӵ����jp��  
         login1.add(JName);    
         login1.add(Jpassword);  
         login1.add(JPassword);    
         login1.add(login);  
         login1.add(exit);  
         login1.add(check);
         
         login.addActionListener(new login_actionAdapter(this)); //��¼�����¼�����  
         exit.addActionListener(new login_actionAdapter(this));   //�˳������¼�����  
           
         this.add(login1,null);   //��������嶨�����м�             
         this.setTitle("����Ա��¼");   
         this.setAlwaysOnTop(true);
      }
     void loginactionPerformed(ActionEvent e)  // ���¼����д���  
    {  
        if(e.getSource() == exit)  
        {  
            this.dispose();  
        }  
        else  
        {  
        	con.connectDb("jnbus"); //�������ݿ�
        	
        	String sql1 ="select managername from manager"; //�����˻�����ѯ
        	String sql2 ="select password from manager"; //�����˻�����ѯ
                ResultSet rs1,rs2;
                try {
                  rs1 = con.stmt.executeQuery(sql1);                
                  while (rs1.next()){
                  managername = rs1.getString("managername").trim(); }//�ҵ��û�����
                    rs2 = con.stmt.executeQuery(sql2); 
                    while (rs2.next())
                    {password = rs2.getString("password").trim();} //�ҵ�����
                }
                catch (SQLException e1) {
                }	
            if(JName.getText().equals(managername)&& String.valueOf(JPassword.getPassword()).equals(password))  
            {  
                this.dispose(); //�ص���ѯ����
                c.dispose();
                busmanage jnbusm = new busmanage();  
            }  
            else   
            {  
                check.setText("�û������������");
                check.setForeground(Color.red);
                JName.setText("");   
                JPassword.setText("");  
            }  
        }  
    }  
}  
class login_actionAdapter implements java.awt.event.ActionListener {
	  login adaptee;
	  login_actionAdapter(login adaptee) {
	    this.adaptee = adaptee;
	  }
	  public void actionPerformed(ActionEvent e) {
	    adaptee.loginactionPerformed(e);
	  }
	}








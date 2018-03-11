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
	connectDB con = new connectDB(); //声明连接数据库类的一个对象
	JButton login = new JButton("登录");  
	JButton exit = new JButton("取消");  
	JLabel  name = new JLabel("用户名：");
	JLabel Jpassword = new JLabel("密码："); 
	JLabel  check = new JLabel();
	JTextField JName = new JTextField(10); //明文账号输入  
	JPasswordField JPassword = new JPasswordField(10); // 非明文密码输入；  
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
         login1.add(name);   //将内容加到面板jp上  
         login1.add(JName);    
         login1.add(Jpassword);  
         login1.add(JPassword);    
         login1.add(login);  
         login1.add(exit);  
         login1.add(check);
         
         login.addActionListener(new login_actionAdapter(this)); //登录增加事件监听  
         exit.addActionListener(new login_actionAdapter(this));   //退出增加事件监听  
           
         this.add(login1,null);   //将整块面板定义在中间             
         this.setTitle("管理员登录");   
         this.setAlwaysOnTop(true);
      }
     void loginactionPerformed(ActionEvent e)  // 对事件进行处理  
    {  
        if(e.getSource() == exit)  
        {  
            this.dispose();  
        }  
        else  
        {  
        	con.connectDb("jnbus"); //连接数据库
        	
        	String sql1 ="select managername from manager"; //进行账户名查询
        	String sql2 ="select password from manager"; //进行账户名查询
                ResultSet rs1,rs2;
                try {
                  rs1 = con.stmt.executeQuery(sql1);                
                  while (rs1.next()){
                  managername = rs1.getString("managername").trim(); }//找到用户名名
                    rs2 = con.stmt.executeQuery(sql2); 
                    while (rs2.next())
                    {password = rs2.getString("password").trim();} //找到密码
                }
                catch (SQLException e1) {
                }	
            if(JName.getText().equals(managername)&& String.valueOf(JPassword.getPassword()).equals(password))  
            {  
                this.dispose(); //关掉查询窗口
                c.dispose();
                busmanage jnbusm = new busmanage();  
            }  
            else   
            {  
                check.setText("用户名或密码错误！");
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








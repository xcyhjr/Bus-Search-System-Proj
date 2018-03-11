import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.io.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.*;
import java.text.*;
import java.sql.Time;
import java.util.Vector;
import javax.swing.table.*;

public class clientFrame extends JFrame {
  busSearch bs = new busSearch(); //声明公交查询类的一个对象
  connectDB con = new connectDB(); //声明连接数据库类的一个对象
  JPanel contentPane;

  JTabbedPane jTabbedPane1 = new JTabbedPane(); //有选项卡的容器
  
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  JPanel jPanel4 = new JPanel();
  JPanel jPanel5 = new JPanel();
  JPanel jPanel6 = new JPanel();
  JPanel jPanel7 = new JPanel();
  JPanel jPanel8 = new JPanel();
  JPanel jPanel9 = new JPanel();
  
  JTextField jTextField1 = new JTextField();
  JTextField jTextField4 = new JTextField();

  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  JButton jButton3 = new JButton();
  
  JButton jButton8 = new JButton();
  JButton jButton9 = new JButton();
  JButton jButton10 = new JButton();
  
  JTextArea jTextArea1 = new JTextArea();
  JTextArea jTextArea2 = new JTextArea();
  JTextArea jTextArea3 = new JTextArea();
  JTextArea jTextArea4 = new JTextArea();
  JTextArea jTextArea5 = new JTextArea();
  JTextArea jTextArea6 = new JTextArea();
  
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JLabel jLabel4 = new JLabel();
  JLabel jLabel5 = new JLabel();
  JLabel jLabel6 = new JLabel();
  JLabel jLabel7 = new JLabel();
  JLabel jLabel8 = new JLabel();
  JLabel jLabel9 = new JLabel();
  JLabel jLabel10 = new JLabel();
  JLabel jLabel11 = new JLabel();
  JLabel jLabel12 = new JLabel();
  JLabel jLabel13 = new JLabel();
  JLabel jLabel14 = new JLabel();
  JLabel jLabel15 = new JLabel();
  JLabel jLabel16 = new JLabel();
  JLabel jLabel17 = new JLabel();
  JLabel jLabel18 = new JLabel();
  JLabel jLabel19 = new JLabel();
  
  JPanel jPanel10 = new JPanel();
  JPanel jPanel11 = new JPanel();
  JPanel jPanel12 = new JPanel();
  JPanel jPanel13 = new JPanel();
  
  JScrollPane jScrollPane1 = new JScrollPane();
  JScrollPane jScrollPane2 = new JScrollPane();
  JScrollPane jScrollPane3 = new JScrollPane();
  JScrollPane jScrollPane4 = new JScrollPane();
  JScrollPane jScrollPane5 = new JScrollPane();
 
  JComboBox jComboBox1 = new JComboBox();
  JComboBox jComboBox2 = new JComboBox();
  
//******************************************************//
//Construct the frame  构造函数，构建框架
  public clientFrame() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      Init();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  
//*******************时间线程****************************//
  class timeThread extends Thread { //功能：求出当前时间
    String currenttime; //当前时间
    public void run() {
      while (true) {
        Date now = new Date();
        String s = now.toString();
        currenttime = s.substring(11, 19); //时间
    	  Calendar calendar = Calendar.getInstance();
    	  String year = String.valueOf(calendar.get(Calendar.YEAR));  
          String month =String.valueOf(calendar.get(Calendar.MONTH) + 1);  
          String day = String.valueOf(calendar.get(Calendar.DATE));
          String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
          int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
          jTextArea5.setText("    " + year+"年"+month+"月"+day+"日"+"\n         "+weekDays[w]+"\n      "+currenttime); //显示当前时间*/
        try {
          sleep(1000);
        }
        catch (Exception e) {}
      }
    }
  }
//*******************时间线程****************************//
  

//****************************读取交通信息文件************************************//
//****************************************************************************//
  String transinfoline = null; //交通信息文件中的一行
  String transinfotxt = "";
 
  public void readtransinfo() { //读取 公交公司资料,读出文件transinfo的内容
    try {
      BufferedReader br1 = new BufferedReader(new FileReader("transinfo.txt"));
      transinfoline = br1.readLine();
      while (transinfoline != null) {
        transinfotxt = transinfotxt + transinfoline + "\n";
        transinfoline = br1.readLine();
      }
      br1.close();
    }
    catch (IOException e) {
      System.out.println(e);
    }
  }

//*********************************读取帮助文件*******************************************//
  String helpline = null; //help文件中每一行的内容
  String helptxt = ""; //help文件的内容
  public void readhelp() { //功能：读出文件help的内容
    try {
      BufferedReader br = new BufferedReader(new FileReader("help.txt")); //输入流
      helpline = br.readLine(); //读文件中的一行
      while (helpline != null) { //判断文件内容是否为空，不为空进行循环
        helptxt = helptxt + helpline + "\n"; //文件内容加上一行
        helpline = br.readLine(); //读文件中的一行
      }
      br.close(); //关闭输入流
    }
    catch (IOException e) {
      System.out.println(e);
    }
  }

//****************************初始化图形界面组件************************************************//
//****************************************************************************//
  //Component initialization
  private void Init() throws Exception {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//设置外观
    }
    catch (Exception ee) {
      ee.printStackTrace();
    }
    timeThread time = new timeThread(); //创建一个时间线程对象
    time.start(); //启动时间线程
    con.connectDb("jnbus"); //连接数据库
    contentPane = (JPanel)this.getContentPane();//初始化容器
    contentPane.setLayout(null);
    contentPane.add(jPanel2, null);
    
    this.setResizable(false); //窗口大小不可以改变
    this.setSize(new Dimension(600, 400)); //设置窗口大小
    this.setTitle("宣城市公交查询系统");
  
    jPanel2.setLayout(null);
    jPanel2.setBounds(new Rectangle(0, 0, 600, 400));
    jPanel2.add(jTabbedPane1, null);
    jPanel2.add(jPanel8, null);

    /**********************************************************/
    /******************站点查询面板初始化************************/
    jPanel3.setLayout(null);
    jPanel3.add(jLabel1, null);
    jPanel3.add(jScrollPane1, null);
    jPanel3.add(jTextField1, null);
    jPanel3.add(jButton1, null);
    jPanel3.add(jLabel15, null);

    jLabel1.setBounds(new Rectangle(0, 0, 305, 25));
    jLabel1.setText("公交查询系统->站点查询");
    
    jLabel15.setFont(new java.awt.Font("黑体", 0, 12));
    jLabel15.setBorder(BorderFactory.createEtchedBorder());
    jLabel15.setText("（输入查询的站点名称，如：合工大）");
    jLabel15.setBounds(new Rectangle(1, 30, 218, 24));
        
    jTextField1.setBackground(Color.white);
    jTextField1.setText("");
    jTextField1.setBounds(new Rectangle(240, 30, 140, 23));
    
    jButton1.setBounds(new Rectangle(385, 26, 66, 32));
    jButton1.setText("查询");
    jButton1.addActionListener(new clientFrame_jButton1_actionAdapter(this));

    jTextArea1.setEditable(false);
    jTextArea1.setLineWrap(true);
    jTextArea1.setText("");
    
    jScrollPane1.setBorder(BorderFactory.createLineBorder(Color.black));
    jScrollPane1.setBounds(new Rectangle(2, 60, 445, 269));
    jScrollPane1.getViewport().add(jTextArea1, null);

    /**********************************************************/
    /******************线路查询面板初始化************************/
    
    jPanel4.setLayout(null);
    jPanel4.add(jLabel2, null);
    jPanel4.add(jScrollPane2, null);
    jPanel4.add(jTextField4, null);
    jPanel4.add(jButton2, null);
    jPanel4.add(jLabel17, null);
    
    jLabel2.setBounds(new Rectangle(0, 0, 305, 25));
    jLabel2.setText("公交查询系统->线路查询");
    
    jLabel17.setFont(new java.awt.Font("黑体", 0, 12));
    jLabel17.setText("（输入要查询的线路名，如：10）");
    jLabel17.setBounds(new Rectangle(1, 30, 218, 24));
    jLabel17.setBorder(BorderFactory.createEtchedBorder());
    
    jTextField4.setFont(new java.awt.Font("Serif", 0, 15));
    jTextField4.setBorder(BorderFactory.createLineBorder(Color.black));
    jTextField4.setBounds(new Rectangle(240, 30, 140, 23));

    jButton2.setBounds(new Rectangle(385, 26, 66, 32));
    jButton2.setText("查询");
    jButton2.addActionListener(new clientFrame_jButton2_actionAdapter(this));

    jTextArea2.setForeground(Color.black);
    jTextArea2.setEditable(false);
    jTextArea2.setText("");
   
    jScrollPane2.setBorder(BorderFactory.createLineBorder(Color.black));
    jScrollPane2.setBounds(new Rectangle(2, 60, 445, 269));
    jScrollPane2.getViewport().add(jTextArea2, null);

    /**********************************************************/
    /******************乘车方案查询面板初始化*********************/

    jPanel5.setLayout(null);
    jPanel5.add(jLabel3, null);
    jPanel5.add(jScrollPane3, null);
    jPanel5.add(jLabel18, null);
    jPanel5.add(jComboBox1, null);
    jPanel5.add(jComboBox2, null);
    jPanel5.add(jButton3, null);
    jPanel5.add(jLabel19, null);
    
    jLabel3.setBounds(new Rectangle(0, 0, 305, 25));
    jLabel3.setText("  公交查询系统->乘车查询");
    
    jLabel19.setFont(new java.awt.Font("黑体", 0, 12));
    jLabel19.setBorder(BorderFactory.createEtchedBorder());
    jLabel19.setText(" (如:合工大<->宣城宾馆)");
    jLabel19.setBounds(new Rectangle(1, 30, 150, 24));
    
    jComboBox1.setEditable(true);
    jComboBox1.setBounds(new Rectangle(155, 28, 100, 27));
    jComboBox1.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.SELECTED) {
          if (jComboBox1.getSelectedIndex() == -1) {
            String start_station_name = (String) event.getItem(); //得到输入jcomboBox1中的内容
            String sql =
                "select distinct 站点名 from stationinfo where 站点名 like '%" +
                start_station_name + "%'"; //进行模糊查询
            ResultSet rs;
            try {
              jComboBox1.removeAllItems(); //先把jComboBox1中的内容清空
              rs = con.stmt.executeQuery(sql);
              while (rs.next()) {
                String station_name = rs.getString("站点名"); //找到相近站点名
                jComboBox1.addItem(station_name.trim()); //把查询结果加入jcombobox1中
              }
            }
            catch (SQLException e1) {
            }
          }
        
        }
      }
    });

    jLabel18.setBounds(new Rectangle(258, 28, 30, 27));
    jLabel18.setText("<->");
    
    jComboBox2.setEditable(true);
    jComboBox2.setBounds(new Rectangle(285, 28, 100, 27));
    jComboBox2.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.SELECTED) {
          if (jComboBox2.getSelectedIndex() == -1) {
            String start_station_name = (String) event.getItem(); //得到输入jcomboBox1中的内容
            String sql =
                "select distinct 站点名 from stationinfo where 站点名 like '%" +
                start_station_name + "%'"; //进行模糊查询
            ResultSet rs;
            try {
              jComboBox2.removeAllItems(); //先把jComboBox1中的内容清空
              rs = con.stmt.executeQuery(sql);
              while (rs.next()) {
                String station_name = rs.getString("站点名"); //找到相近站点名
                jComboBox2.addItem(station_name.trim()); //把查询结果加入jcombobox1中
              }
            }
            catch (SQLException e1) {
            }
          }
        }
      }
    });
    
    jButton3.setBounds(new Rectangle(385, 30, 66, 23));
    jButton3.setText("查询");
    jButton3.addActionListener(new clientFrame_jButton3_actionAdapter(this));

    jTextArea3.setForeground(Color.black);
    jTextArea3.setEditable(false);
    jTextArea3.setText("");

    jScrollPane3.setBorder(BorderFactory.createLineBorder(Color.black));
    jScrollPane3.setBounds(new Rectangle(2, 60, 445, 269));
    jScrollPane3.getViewport().add(jTextArea3, null);
    
    /***********************交通信息***********************************/
    jPanel6.setLayout(null);
    jPanel6.add(jLabel4, null);
    jPanel6.add(jScrollPane5, null);
   
    jLabel4.setBounds(new Rectangle(0, 0, 305, 25));
    jLabel4.setText("公交查询系统->交通信息");

    readtransinfo();
    jTextArea6.setEditable(false);
    jTextArea6.setLineWrap(true);
    jTextArea6.setText(transinfotxt);
    
    jScrollPane5.setBounds(new Rectangle(2, 29, 450, 300));
    jScrollPane5.getViewport().add(jTextArea6, null);
    
    /***********************帮助内容***********************************/
    
    jPanel7.setLayout(null);
    jPanel7.add(jLabel5, null);
    jPanel7.add(jScrollPane4, null);
    
    jLabel5.setBounds(new Rectangle(0, 0, 305, 25));
    jLabel5.setText("公交查询系统->帮助");
    
    readhelp(); //读取帮助内容
    jTextArea4.setLineWrap(true);
    jTextArea4.setEditable(false);
    jTextArea4.setText(helptxt);
    
    jScrollPane4.setBounds(new Rectangle(2, 29, 450, 300));
    jScrollPane4.getViewport().add(jTextArea4, null);
    
    /***********************左侧面板***********************************/
    
    jTabbedPane1.setEnabled(true);
    jTabbedPane1.setForeground(Color.black);
    jTabbedPane1.setBorder(BorderFactory.createLineBorder(Color.black));
    jTabbedPane1.setPreferredSize(new Dimension(79, 130));
    jTabbedPane1.setBounds(new Rectangle( 0, 0, 480, 400));
    jTabbedPane1.setTabPlacement(JTabbedPane.TOP);
    jTabbedPane1.add(jPanel3, "站点查询");
    jTabbedPane1.add(jPanel4, "线路查询");
    jTabbedPane1.add(jPanel5, "乘车查询");
    jTabbedPane1.add(jPanel6, "交通信息");
    jTabbedPane1.add(jPanel7, "帮助");
    jTabbedPane1.setSelectedComponent(jPanel3);

    /***********************右侧面板***********************************/
 
    jPanel8.setBounds(new Rectangle(480, 0, 118, 396));
    jPanel8.setLayout(null);
    jPanel8.add(jTextArea5, null);
    jPanel8.add(jButton8, null);
    jPanel8.add(jButton9, null);
    jPanel8.add(jButton10, null);
    jPanel8.add(jLabel11, null);
    
    //系统时间信息
    jTextArea5.setFont(new java.awt.Font("Dialog", 0, 12));
    jTextArea5.setBorder(BorderFactory.createRaisedBevelBorder());
    jTextArea5.setEditable(false);
    jTextArea5.setBounds(new Rectangle(0, 0, 118, 60));
    jTextArea5.setOpaque(false);
    //更多功能
    
    jLabel11.setFont(new java.awt.Font("黑体", 2, 16));
    jLabel11.setBorder(BorderFactory.createRaisedBevelBorder());
    jLabel11.setText("     更多功能");
    jLabel11.setBounds(new Rectangle(0, 60, 118, 30));
        
    jButton8.setBounds(new Rectangle(10, 120, 100, 50));
    jButton8.setText("公交管理");
    jButton8.addActionListener(new clientFrame_jMenuItem1_actionAdapter(this));
    
    jButton9.setBounds(new Rectangle(10, 185, 100, 50));
    jButton9.setText("关于");
    jButton9.addActionListener(new clientFrame_jMenuItem6_actionAdapter(this));
    
    jButton10.setBounds(new Rectangle(10, 250, 100, 50));
    jButton10.setText("退出");
    jButton10.addActionListener(new clientFrame_jMenuItem2_actionAdapter(this));
    
  }

  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      System.exit(0);
    }
  }

  void jMenuItem2_actionPerformed(ActionEvent e) {
    //菜单系统退出
    System.exit(0);
  }

//***************************************************************************//
//*********************判断该车次是否有空调*************************************//
  public boolean shifoukongtiao(int checinum) {
    boolean sfkongtiao = false;
    ResultSet rs;
    try {
      String sql = "select 是否空调 from businfo where 车次号=" + checinum + "";
      rs = con.stmt.executeQuery(sql);
      while (rs.next()) {
        String kongtiao = rs.getString("是否空调").trim();
        if (kongtiao.equals("是")) {
          sfkongtiao = true;
        }
        else
          sfkongtiao = true;
      }
    }
    catch (SQLException e1) {
    }
    return sfkongtiao;
  }

//***************************************************************************//
//****************************站点查询***************************************//
  void jButton1_actionPerformed(ActionEvent e) {
    ResultSet rs;//jdbc结果集对象
    jTextArea1.setText("");
    String stationname;
    stationname = jTextField1.getText().trim();
    if (stationname.equals("")) {
      JOptionPane.showMessageDialog(null, "请先输入站点名，再点击查询按钮！");
    }
    else {
      Vector line_vector = bs.search_Station(stationname); //得到通过该站点的线路向量
      int line_size = line_vector.size();
      if (line_size == 0) {
        jTextArea1.setText("");
        jTextArea1.append("找不到您所查询的站点\n");
        jTextArea1.append("您是否想查询以下站点？\n");
        //进行模糊查询
        Vector station = bs.search_closeStation(stationname); //对站点进行模糊查询
        int station_size = station.size();
        if (station_size == 0) {
          // 模糊查询失败
          jTextArea1.append("对不起，没有类似站点，查询失败！");
        }
        else {
          for (int i = 0; i < station_size; i++) {
            jTextArea1.append(station.get(i).toString() + "\n");
          }
        }
      }
      else {
        //精确查询
    	  jTextArea1.append("经过站点"+stationname+"的车次有:\n");
        for (int j = 0; j < line_size; j++) {
          jTextArea1.append(line_vector.get(j).toString() +"路     ");
        }
      }
    }
  }

//***************************************************************************//
//************************************线路查询*********************************//
  void jButton2_actionPerformed(ActionEvent e) {
    jTextArea2.setText("");
    String linename;
    linename = jTextField4.getText().trim();
    int linenumber;
    linenumber = Integer.parseInt(linename);
    if (linename.equals("")) {
      JOptionPane.showMessageDialog(null, "请输入公交线路，再点击查询按钮！");
    }
    else {
      //线路属性
      Vector line_info = bs.search_LineProperty(linenumber);
      int line_info_size = line_info.size();
      if (line_info_size == 0) {
        JOptionPane.showMessageDialog(null, "不存在" + linename + "路车");
      }
      else {
        Vector line_pro = (Vector) line_info.get(0);
        String start_station = line_pro.get(0).toString().trim(); //始发站
        String start_time = line_pro.get(1).toString().trim(); //营运时间上
        String end_station = line_pro.get(2).toString().trim(); //终点站
        String end_time = line_pro.get(3).toString().trim(); //营运时间下
        String kongtiao = line_pro.get(4).toString().trim(); //是否空调
        if (kongtiao.equals("是")) {
          //空调车
          jTextArea2.append(linename +"路空调车，票价2元\n");
        }
        else
        jTextArea2.append(linename + "路非空调车，票价1元\n");
        jTextArea2.append(String.format("%-10s%-15s%-10s%-10s\r\n","始发站: ",start_station,"运行时间：",start_time));
        jTextArea2.append(String.format("%-10s%-15s%-10s%-10s\r\n","始发站: ",end_station,"运行时间：",end_time));
        //线路站点
        Vector line_station_vector = new Vector();
        line_station_vector = bs.search_Linestation(linenumber);
        int line_vector_size = line_station_vector.size();
        Vector line_station = new Vector();
        jTextArea2.append("以下是该车次的站点：\n");
        for (int k = 0; k < line_vector_size; k++) {
          line_station = (Vector) line_station_vector.get(k);
          jTextArea2.append(line_station.get(0) + "    " +
                            line_station.get(1) + "\n");
          line_station.removeAllElements();
        }
        jTextArea2.setCaretPosition(0); 
      }
    }
  }

//***************************************************************************//
//*************************乘车方案查询****************************************//
  
  void jButton3_actionPerformed(ActionEvent e) {

    String delsql="delete from huancheng";
    try{
      con.stmt.execute(delsql);//清空huancheng表
    }
    catch(SQLException dele)
    {
    }
    jTextArea3.setText(""); //先将上次查询记录清空
    String s1, s2;
    boolean s1cunzai = false, s2cunzai = false;
    s1 = jComboBox1.getSelectedItem().toString().trim();
    s2 = jComboBox2.getSelectedItem().toString().trim();
    String sql = "select * from stationinfo where 站点名='" + s1 + "'";
    String sql1 = "select * from stationinfo where 站点名='" + s2 + "'";
    try {
      ResultSet rs = con.stmt.executeQuery(sql);
      s1cunzai = rs.next();
      rs.close();
      ResultSet rs1 = con.stmt.executeQuery(sql1);
      s2cunzai = rs1.next();
      rs1.close();
    }
    catch (SQLException e1) {
    }
    if (s1.equals("") & s2.equals("")) {
      JOptionPane.showMessageDialog(null, "请输入起点站和终点站！");
    }
    else if (s1.equals("") & !s2.equals("")) {
      JOptionPane.showMessageDialog(null, "请输入起点站！");
    }
    else if (s2.equals("") & !s1.equals("")) {
      JOptionPane.showMessageDialog(null, "请输入终点站！");
    }
    else {
      if (!s1cunzai | !s2cunzai) {
        JOptionPane.showMessageDialog(null, "你输入的站点不存在，请先查询站点是否存在！");
      }
      else {
        //进行直达查询
        Vector nonstop = bs.search_nonstop(s1, s2);
        int nonstopsize = nonstop.size();
        if (nonstopsize == 0) {
          jTextArea3.append("没有找到直达方案！！\n");
          //进行一次查询
          Vector first_line;
          Vector change_line;
          Vector change_vector;
          String change_station;
          Vector change_solution = bs.search_oncechange(s1, s2);
          int change_solution_size = change_solution.size();
          if (change_solution_size == 0) {
            jTextArea3.append("没有找到一次换乘方案！\n由于计算复杂，请人工查询！");
          }
          else {
            jTextArea3.append("一次换乘方案共有" + change_solution_size + "种\n");
            jTextArea3.append("先乘车次    中转站        换乘车次    总站数\n");
            for (int i = 0; i < change_solution_size; i++) {
              change_vector = (Vector) change_solution.get(i);
              first_line = (Vector) change_vector.get(0); //先乘车次向量
              change_station = change_vector.get(1).toString(); //换乘站点
              change_line = (Vector) change_vector.get(2); //换乘车次向量

              int first_line_size = first_line.size();
              int change_line_size = change_line.size();
              for (int j = 0; j < first_line_size; j++) {
                for (int k = 0; k < change_line_size; k++) {
                  int a = bs.countStation(s1, change_station,
                                          Integer.parseInt(first_line.get(j).
                      toString().trim())); //先乘车次经过的站点数
                  int b = bs.countStation(change_station, s2,
                                          Integer.parseInt(change_line.get(k).
                      toString().trim())); //换乘车次经过的站点数
                  int c = a + b; //经过的总站点数
                  String xianchengcheci=first_line.get(j).toString().trim();//先乘车次
                  String huanchengcheci=change_line.get(k).toString().trim();//换乘车次
                  String insertsql="insert into huancheng values('"+xianchengcheci+"','"+change_station+"','"+huanchengcheci+"',"+c+")";
                  //System.out.println(insertsql);
                  try{
                    con.stmt.execute(insertsql);//将一次换乘方案插入表huancheng中
                  }catch(SQLException inserte){
                  }
                  /*jTextArea3.append(first_line.get(j).toString().trim() +
                                    "        " +
                                    change_station + "" +
                                    change_line.get(k).toString() + "         " +
                                    c + "\n");*/
                }

              }

            }
            //显示出所有的一次换乘方案
              String selsql="select * from huancheng order by 总站数";
              ResultSet selrs;
              try{
                selrs=con.stmt.executeQuery(selsql);
                while(selrs.next())
                {
                  String xiancheng=selrs.getString("先乘车次").trim();
                  String zhongzhuan=selrs.getString("中转站");
                  String huancheng=selrs.getString("换乘车次").trim();
                  int zongzhanshu=selrs.getInt("总站数");            
                  jTextArea3.append(String.format("%-15s%-15s%-15s%-15s\r\n",xiancheng,zhongzhuan,huancheng,zongzhanshu));
                }
              }
              catch(SQLException sele){}

          }
          //****************************************************//
          //tm.fireTableStructureChanged(); //更新表格，显示向量vector的内容
          //****************************************************//
        }
        else {
          //输出
          jTextArea3.append("共有" + nonstopsize + "种直达方案\n");
          jTextArea3.append("车次    站点数\n");
          for (int i = 0; i < nonstopsize; i++) {
            int a = bs.countStation(s1, s2,
                                    Integer.parseInt(nonstop.get(i).toString())); //经过的站点数
            jTextArea3.append(nonstop.get(i).toString() + "        " + a + "\n");
          }
        }
      }
    }
  }

//***************************************************************************//
//***************************************************************************//
  void jMenuItem1_actionPerformed(ActionEvent e) { //进入登录界面
	    login b = new login(this);
	    b.setModal(true);
	    b.setSize(300, 200);
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    Dimension frameSize = b.getSize();
	    if (frameSize.height > screenSize.height) {
	      frameSize.height = screenSize.height;
	    }
	    if (frameSize.width > screenSize.width) {
	      frameSize.width = screenSize.width;
	    }
	    b.setLocation( (screenSize.width - frameSize.width) / 2,
	                  (screenSize.height - frameSize.height) / 2);
	    b.setVisible(true); 
    
  }

//***************************************************************************//
//***************************************************************************//
  void jMenuItem6_actionPerformed(ActionEvent e) {
    //打开关于系统
    about a = new about();
    a.setSize(300, 150);
    a.setModal(true);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = a.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    a.setLocation( (screenSize.width - frameSize.width) / 2,
                  (screenSize.height - frameSize.height) / 2);
    a.setVisible(true);
  }

}

class clientFrame_jMenuItem2_actionAdapter
    implements java.awt.event.ActionListener {
  clientFrame adaptee;

  clientFrame_jMenuItem2_actionAdapter(clientFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem2_actionPerformed(e);
  }
}

class clientFrame_jButton1_actionAdapter
    implements java.awt.event.ActionListener {
  clientFrame adaptee;

  clientFrame_jButton1_actionAdapter(clientFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class clientFrame_jButton2_actionAdapter
    implements java.awt.event.ActionListener {
  clientFrame adaptee;

  clientFrame_jButton2_actionAdapter(clientFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}

class clientFrame_jButton3_actionAdapter
    implements java.awt.event.ActionListener {
  clientFrame adaptee;

  clientFrame_jButton3_actionAdapter(clientFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton3_actionPerformed(e);
  }
}
class clientFrame_jMenuItem1_actionAdapter
    implements java.awt.event.ActionListener {
  clientFrame adaptee;

  clientFrame_jMenuItem1_actionAdapter(clientFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem1_actionPerformed(e);
  }
}

class clientFrame_jMenuItem6_actionAdapter
    implements java.awt.event.ActionListener {
  clientFrame adaptee;

  clientFrame_jMenuItem6_actionAdapter(clientFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jMenuItem6_actionPerformed(e);
  }
}
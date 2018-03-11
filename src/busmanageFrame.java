import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.swing.table.*;

public class busmanageFrame extends JFrame {
  JPanel contentPane;

  JTabbedPane jTabbedPane1 = new JTabbedPane(); //有选项卡的容器
  
  JRadioButton jRadioButton1 = new JRadioButton();
  JRadioButton jRadioButton2 = new JRadioButton();
 
  JPanel jPanel1 = new JPanel();
  JPanel jPanel2 = new JPanel();
  JPanel jPanel3 = new JPanel();
  JPanel jPanel4 = new JPanel();
  JPanel jPanel5 = new JPanel();
  JPanel jPanel6 = new JPanel();
  JPanel jPanel7 = new JPanel();
  JPanel jPanel8 = new JPanel();
  JPanel jPanel9 = new JPanel();
  JPanel jPanel10 = new JPanel();
  JPanel jPanel11 = new JPanel();
  JPanel jPanel12 = new JPanel();
  JPanel jPanel13 = new JPanel();
  JPanel jPanel14 = new JPanel();
  
  JButton jButton6 = new JButton();
  JButton jButton7 = new JButton();
  JButton jButton8 = new JButton();
  JButton jButton9 = new JButton();
  JButton jButton10 = new JButton();
  JButton jButton11 = new JButton();
  JButton jButton12 = new JButton();
  JButton jButton13 = new JButton();
  JButton jButton14 = new JButton();
  JButton jButton15 = new JButton();
  JButton jButton16 = new JButton();
  JButton jButton17 = new JButton();
  JButton jButton18 = new JButton();
  
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
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
  

  JTextField jTextField1 = new JTextField();
  JTextField jTextField2 = new JTextField();
  JTextField jTextField3 = new JTextField();
  JTextField jTextField4 = new JTextField();
  JTextField jTextField6 = new JTextField();
  JTextField jTextField7 = new JTextField();
  JTextField jTextField8 = new JTextField();
  JTextField jTextField9 = new JTextField();
  JTextField jTextField10 = new JTextField();
  JTextField jTextField11 = new JTextField();

  JScrollPane jScrollPane1 = new JScrollPane();
  JScrollPane jScrollPane2 = new JScrollPane();

  JTextArea jTextArea1 = new JTextArea();
  JTextArea jTextArea2 = new JTextArea();
  JTextArea jTextArea3 = new JTextArea();
  JTextArea jTextArea4 = new JTextArea();
  JTextArea jTextArea5 = new JTextArea();
  JTextArea jTextArea6 = new JTextArea();

  ButtonGroup bg = new ButtonGroup();
  
  //Construct the frame
  public busmanageFrame() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      Init();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
//*************************************************************************//
//********************读写help文件******************************************//
  String helpline = null;
  String helptxt = "";
  public void readhelp()
  {
    //读出文件help的内容
    try
    {
      BufferedReader br = new BufferedReader(new FileReader("help.txt"));
      helpline = br.readLine();
      while (helpline != null)
      {
        helptxt = helptxt + helpline + "\n";
        helpline = br.readLine();
      }
      br.close();
    }
    catch (IOException e)
    {
      System.out.println(e);
    }
  }

  public void writehelp()
  {
    //编辑帮助
    PrintWriter outputhelp = null;
    try
    {
      outputhelp = new PrintWriter(new FileOutputStream("help.txt"));
    }
    catch (FileNotFoundException e)
    {
    }
    String helptxt = jTextArea2.getText();
    outputhelp.write(helptxt);
    outputhelp.close();
  }
//*************************************************************************//
//*************************读写交通信息*******************************//
  String transinfoline = null;
  String transinfotxt = "";
  public void readtransinfo()
  {
    //读取 公交公司资料，读出文件transinfo的内容
    try
    {
      BufferedReader br1 = new BufferedReader(new FileReader("transinfo.txt"));
      transinfoline = br1.readLine();
      while (transinfoline != null)
      {
        transinfotxt = transinfotxt + transinfoline + "\n";
        transinfoline = br1.readLine();
      }
      br1.close();
    }
    catch (IOException e)
    {
      System.out.println(e);
    }
  }

  public void writetransinfo()
  {
    //编辑公交公司资料
    PrintWriter outputtransinfo = null;
    try
    {
      outputtransinfo = new PrintWriter(new FileOutputStream("transinfo.txt"));
    }
    catch (FileNotFoundException e)
    {
    }
    String jnbustxt = jTextArea1.getText();
    outputtransinfo.write(jnbustxt);
    outputtransinfo.close();
  }
//*************************************************************************//
//****************连接数据库************************************************//
  Connection conn;
  Statement stmt;
  public void condatabase()
  {
    
    try
    {
    	String url="jdbc:sqlserver://172.20.10.2:1433;DatabaseName=jnbus;";
	      String user = "sa";
	      String password = "12345";
	      conn = DriverManager.getConnection(url, user, password);
	      stmt = conn.createStatement();
    }
    catch (SQLException e) {
    	System.out.println("数据库连接失败！");  
    }
  }
//*************************************************************************//
//*****************************初始化***************************************//
  private void Init() throws Exception
  {
    condatabase(); //连接数据库
    contentPane = (JPanel)this.getContentPane();
    contentPane.setLayout(null);//设置布局为空
    this.setResizable(false);//不可改变大小
    this.setSize(new Dimension(600, 400));//设置显示窗口大小
    this.setTitle("宣城市公交管理系统");//设置标题

    contentPane.add(jPanel2, null);
    
    //********主容器，用于放置各个panel和菜单************//
    jPanel2.setBounds(new Rectangle(0, 0, 600, 400));
    jPanel2.setLayout(null);
    jPanel2.add(jTabbedPane1,null);
    
    
    
    jTabbedPane1.setEnabled(true);
    jTabbedPane1.setForeground(Color.black);
    jTabbedPane1.setBorder(BorderFactory.createLineBorder(Color.black));
    jTabbedPane1.setPreferredSize(new Dimension(79, 130));
    jTabbedPane1.setBounds(new Rectangle( 0, 0, 600, 400));
    jTabbedPane1.setTabPlacement(JTabbedPane.TOP);
    jTabbedPane1.add(jPanel4, "站点管理");
    jTabbedPane1.add(jPanel5, "线路管理");
    jTabbedPane1.add(jPanel7, "交通信息编辑");
    jTabbedPane1.add(jPanel8, "帮助内容编辑");
    
    
    //********站点管理容器************//
    
    jPanel4.setLayout(null);
    
    jTextField1.setText("输入车次号");
    jTextField1.setBounds(new Rectangle(5, 13, 200, 32));
    
    jButton11.setBounds(new Rectangle(210, 15, 50, 33));
    jButton11.setText("确定");
    jButton11.addActionListener(new busmanageFrame_jButton11_actionAdapter(this));
    
    jRadioButton1.setBackground(SystemColor.inactiveCaptionText);
    jRadioButton1.setText("增加车次");   
    jRadioButton1.setBounds(new Rectangle(5, 45, 100, 25));
    
    jRadioButton2.setBackground(SystemColor.inactiveCaptionText);
    jRadioButton2.setSelected(true);
    jRadioButton2.setText("查询车次");
    jRadioButton2.setBounds(new Rectangle(105, 45, 100, 25));
    
    bg.add(jRadioButton1);
    bg.add(jRadioButton2);
    
    jButton12.setBounds(new Rectangle(10, 10, 80, 35));
    jButton12.setText("添加站点");
    jButton12.addActionListener(new busmanageFrame_jButton12_actionAdapter(this));

    jButton13.setBounds(new Rectangle(10, 50, 80, 35));
    jButton13.setText("删除站点");
    jButton13.addActionListener(new busmanageFrame_jButton13_actionAdapter(this));

    jButton14.setBounds(new Rectangle(10, 90, 80, 35));
    jButton14.setText("修改");
    jButton14.addActionListener(new busmanageFrame_jButton14_actionAdapter(this));
    
    jButton15.setBounds(new Rectangle(10, 130, 80, 35));
    jButton15.setText("清空");
    jButton15.addActionListener(new busmanageFrame_jButton15_actionAdapter(this));

    jButton6.setBounds(new Rectangle(10, 170, 80, 35));
    jButton6.setText("退出");
    jButton6.addActionListener(new busmanageFrame_jButton6_actionAdapter(this));
    
    jPanel9.setBorder(BorderFactory.createEtchedBorder());
    jPanel9.setBounds(new Rectangle(260, 80, 102, 250));
    jPanel9.setLayout(null);
    jPanel9.add(jButton13, null);
    jPanel9.add(jButton12, null);
    jPanel9.add(jButton15, null);
    jPanel9.add(jButton14, null);
    jPanel9.add(jButton6, null);
    
    jLabel8.setText("车次号");
    jLabel8.setBounds(new Rectangle(10, 5, 50, 25));
    
    jTextField2.setEditable(false);
    jTextField2.setText("");
    jTextField2.setBounds(new Rectangle(60, 3, 120, 30));

    jLabel9.setText("序号");
    jLabel9.setBounds(new Rectangle(10, 40, 50, 25));
   
    jTextField3.setSelectionStart(11);
    jTextField3.setText("");
    jTextField3.setBounds(new Rectangle(60, 38, 120, 30));
    
    jLabel10.setText("站点名");
    jLabel10.setBounds(new Rectangle(10, 75, 50, 25));
    
    jTextField4.setText("");
    jTextField4.setBounds(new Rectangle(60, 75, 120, 30));
    
    jPanel10.setBorder(BorderFactory.createEtchedBorder());
    jPanel10.setBounds(new Rectangle(370, 16, 200, 115));
    jPanel10.setLayout(null);
    jPanel10.add(jLabel8, null);
    jPanel10.add(jLabel10, null);
    jPanel10.add(jTextField2, null);
    jPanel10.add(jTextField4, null);
    jPanel10.add(jTextField3, null);
    jPanel10.add(jLabel9, null);
    
    jTextArea3.setEditable(false);
    jTextArea3.setText("请先进行查询或是增加车次操作，然后进行相应的添加删除或者是修改操作。添加删除和修改操作是针对某一具体线路的站点。当车次号一栏里显示了车次时，你就可以进行相关操作了。你可以参照左边的表格进行操作");
    jTextArea3.setLineWrap(true);
    jTextArea3.setBounds(new Rectangle(1, 1, 201, 194));
    jTextArea3.setOpaque(false);
    
    jPanel12.add(jTextArea3, null);
    jPanel12.setBorder(BorderFactory.createEtchedBorder());
    jPanel12.setBounds(new Rectangle(370, 136, 201, 194));
    jPanel12.setLayout(null);
    
    //********线路管理容器************//
    
    jPanel5.setLayout(null);
    jPanel5.add(jLabel11, null);
    jPanel5.add(jLabel12, null);
    jPanel5.add(jLabel13, null);
    jPanel5.add(jLabel14, null);
    jPanel5.add(jLabel15, null);
    jPanel5.add(jLabel16, null);
    jPanel5.add(jTextField6, null);
    jPanel5.add(jTextField7, null);
    jPanel5.add(jTextField8, null);
    jPanel5.add(jTextField9, null);
    jPanel5.add(jTextField10, null);
    jPanel5.add(jTextField11, null);
    jPanel5.add(jButton16, null);
    jPanel5.add(jButton17, null);
    //jPanel5.add(jComboBox1, null);
    jPanel5.add(jButton18, null);
    jPanel5.add(jPanel6, null);
    jPanel5.add(jLabel1, null);
    
    jLabel11.setText("车次");
    jLabel11.setBounds(new Rectangle(40, 50, 60, 20));
    
    jTextField11.setBounds(new Rectangle(100, 50, 80, 20));
    jTextField11.setBorder(BorderFactory.createLineBorder(Color.black));
    
    jButton17.setBounds(new Rectangle(190, 50, 46, 21));
    jButton17.addActionListener(new busmanageFrame_jButton17_actionAdapter(this));
    jButton17.setText("确定");
    
    jLabel12.setText("起始站");
    jLabel12.setBounds(new Rectangle(40, 100, 60, 20));
    jTextField6.setBounds(new Rectangle(100, 100, 150, 20));
    jTextField6.setBorder(BorderFactory.createLineBorder(Color.black));
    jTextField6.setText("");
    
    jLabel13.setText("上行营运时间");
    jLabel13.setBounds(new Rectangle(310, 100, 80, 20));
    jTextField7.setBounds(new Rectangle(400, 100, 150, 20));
    jTextField7.setBorder(BorderFactory.createLineBorder(Color.black));
    jTextField7.setText("");
    
    jLabel14.setText("终点站");
    jLabel14.setBounds(new Rectangle(40, 150, 60, 20));
    jTextField8.setBounds(new Rectangle(100, 150, 150, 20));
    jTextField8.setBorder(BorderFactory.createLineBorder(Color.black));
    jTextField8.setText("");
    
    jLabel15.setToolTipText("");
    jLabel15.setText("下行营运时间");
    jLabel15.setBounds(new Rectangle(310, 150, 80, 20));
    jTextField9.setBounds(new Rectangle(400, 150, 150, 20));
    jTextField9.setBorder(BorderFactory.createLineBorder(Color.black));
    jTextField9.setText("");
    
    jLabel16.setText("是否空调");
    jLabel16.setBounds(new Rectangle(40, 200, 60, 20));
    jTextField10.setBounds(new Rectangle(100, 200, 150, 20));
    jTextField10.setBorder(BorderFactory.createLineBorder(Color.black));
    jTextField10.setText("");
 
    jButton16.setBounds(new Rectangle(200, 230, 75, 30));
    jButton16.setText("修改");
    jButton16.addActionListener(new busmanageFrame_jButton16_actionAdapter(this));

    jButton18.setBounds(new Rectangle(330, 230, 75, 30));
    jButton18.setText("删除");
    jButton18.addActionListener(new busmanageFrame_jButton18_actionAdapter(this));
  
    jTextArea4.setEditable(false);
    jTextArea4.setLineWrap(true);
    jTextArea4.setText("请先输入车次号，点击确定文本框会显示此车次的属性信息，你可以直接在文本框中进行修改属性和删除线路操作。编辑完成后点击修改按钮即可保存");
    jTextArea4.setBounds(new Rectangle(1, 1, 550, 50));
    jTextArea4.setOpaque(false);

    jPanel6.setBounds(new Rectangle(15, 280, 550, 50));
    jPanel6.setLayout(null);
    jPanel6.setBorder(BorderFactory.createEtchedBorder());
    jPanel6.add(jTextArea4, null);
  
    //********交通信息编辑容器************//
    
    jPanel7.setLayout(null);
    jPanel7.add(jScrollPane1, null);
    jPanel7.add(jButton7, null);
    jPanel7.add(jButton8, null);
    jPanel7.add(jPanel13, null);
    
    jScrollPane1.setBounds(new Rectangle(3, 10, 500, 250));
    jScrollPane1.getViewport().add(jTextArea1, null);
    
    jButton7.setBounds(new Rectangle(320, 280, 80, 25));
    jButton7.setText("保存");
    jButton7.addActionListener(new busmanageFrame_jButton7_actionAdapter(this));

    jButton8.setBounds(new Rectangle(150, 280, 80, 25));
    jButton8.setText("读取");
    jButton8.addActionListener(new busmanageFrame_jButton8_actionAdapter(this));
    
    jTextArea5.setLineWrap(true);
    jTextArea5.setText("先点击读取按钮，文本域中会显示已存在的内容，在文本域中进行编辑，然后点击保存按钮保存。");
    jTextArea5.setBounds(new Rectangle(1, 1, 60, 320));
    jTextArea5.setOpaque(false);
    
    jPanel13.setBorder(BorderFactory.createEtchedBorder());
    jPanel13.setBounds(new Rectangle(510, 10, 60, 320));//文档编辑帮助panel
    jPanel13.setLayout(null);
    jPanel13.add(jTextArea5, null);
    
  //********帮助信息编辑容器************//
    
    jPanel8.setLayout(null);
    jPanel8.add(jScrollPane2, null);
    jPanel8.add(jButton9, null);
    jPanel8.add(jButton10, null);
    jPanel8.add(jPanel14, null);
    
    jButton9.setBounds(new Rectangle(150, 280, 80, 25));
    jButton9.setText("读取");
    jButton9.addActionListener(new busmanageFrame_jButton9_actionAdapter(this));

    jButton10.setBounds(new Rectangle(320, 280, 80, 25));
    jButton10.setText("保存");
    jButton10.addActionListener(new busmanageFrame_jButton10_actionAdapter(this));
    
    jTextArea6.setLineWrap(true);
    jTextArea6.setText("先点击读取按钮，文本域中会显示已存在的内容，在文本域中进行编辑，然后点击保存按钮保存。");
    jTextArea6.setBounds(new Rectangle(1, 1, 60, 320));
    jTextArea6.setOpaque(false);
    
    jScrollPane2.setBounds(new Rectangle(3, 10, 500, 250));
    jScrollPane2.getViewport().add(jTextArea2, null);
    
    jPanel14.setBorder(BorderFactory.createEtchedBorder());
    jPanel14.setBounds(new Rectangle(510, 10, 60, 320));
    jPanel14.setLayout(null);
    jPanel14.add(jTextArea6, null);
    
    jPanel4.add(jTextField1, null);
    jPanel4.add(jButton11, null);
   
    createtable();

  }
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      System.exit(0);
    }
  }
  void jButton6_actionPerformed(ActionEvent e) {
    //系统退出
    this.dispose();
    new client();
    //this.setVisible(false);
    //System.exit(0);
  }

  void jButton8_actionPerformed(ActionEvent e) {
    //读取 公交公司资料
    readtransinfo();
    jTextArea1.setText(transinfotxt);
    jButton8.setEnabled(false);
    jButton7.setEnabled(true);
  }

  void jButton7_actionPerformed(ActionEvent e) {
    //编辑公交公司资料
    writetransinfo();
    jButton8.setEnabled(true);
    jButton7.setEnabled(true);
  }

  void jButton9_actionPerformed(ActionEvent e) {
    //读取帮助
    readhelp();
    jTextArea2.setText(helptxt);
    jButton9.setEnabled(false);
    jButton10.setEnabled(true);
  }

  void jButton10_actionPerformed(ActionEvent e) {
    //编辑帮助文档
    writehelp();
    jButton9.setEnabled(true);
    jButton10.setEnabled(true);
  }

  String title[] = {
      "车次号", "序号", "站点名"}; //二维表列名
  Vector vector;
  JScrollPane scroll;
  AbstractTableModel tm;
  
  void createtable() {
    JTable table;
    vector = new Vector();
    tm = new AbstractTableModel() {
      public int getColumnCount() {
        //取得表格列数
        return title.length;
      }

      public int getRowCount() {
        //取得表格行数
        return vector.size();
      }

      public Object getValueAt(int row, int column) {
        //取得单元格中的属性值
        if (!vector.isEmpty()) {
          return ( (Vector) vector.elementAt(row)).elementAt(column);
        }
        else {
          return null;
        }
      }

      public void setValueAt(Object value, int row, int column) {
        //数据模型不可编辑，该方法设置为空
      }

      public String getColumnName(int column) {
        //取得表格列名
        return title[column];
      }

      public Class getColumnClass(int c) {
        //取得列所属对象类
        return getValueAt(0, c).getClass();
      }

      public boolean isCellEditable(int row, int column) {
        //设置单元格不可编辑
        return false;
      }
    };
    table = new JTable(tm); //生成自己的数据模型
    table.setToolTipText("路线"); //设置帮助提示
    //table.setAutoResizeMode(table.AUTO_RESIZE_OFF); //设置表格调整尺寸模式
    table.setCellSelectionEnabled(true); //设置单元格选择方式
    table.getColumn("车次号").setPreferredWidth(60);
    table.getColumn("序号").setPreferredWidth(60);
    table.getColumn("站点名").setPreferredWidth(130);
    
    table.setShowHorizontalLines(true); //设置是否显示单元格之间的分割线
    table.setShowVerticalLines(true);
    //table.setFont(new Font("MS Sans Serif", 0, 13));
    //table.setBackground(SystemColor.inactiveCaptionText);
    //table.setForeground(SystemColor.activeCaption);

    scroll = new JScrollPane(table);
    scroll.setBounds(new Rectangle(3, 80, 250, 250));
    jPanel4.add(jTextField1, null);
    jPanel4.add(jButton11, null);
    jPanel4.add(jPanel9, null);
    jPanel4.add(jPanel10, null);
    jPanel4.add(jRadioButton1, null);
    jPanel4.add(jRadioButton2, null);
    jPanel4.add(scroll);
    jPanel4.add(jPanel12, null);
  }

/////////////////////////////////////站点管理
  String checihao, xuhao, stationname;
  int checinum, stationnum;
  ////////////////////////增加车次函数
  public void addcheci(int linenum) {
    String sql = "select * from stationinfo where 车次号=" + linenum + "";
    ResultSet rs;
    boolean checicunzai = false;
    try {
      rs = stmt.executeQuery(sql);
      while (rs.next()) {
        checicunzai = true;
      }
      rs.close();
    }
    catch (SQLException e) {
    }
    if (checicunzai) {
      JOptionPane.showMessageDialog(null, "你要添加的车次车次已经存在！");
    }
    else {
      //车次不存在，可进行添加。
      String sqlinsert = "insert into stationinfo values(" + linenum +
          ",1,'0')";
      String sqlline = "insert into businfo values(" + linenum +
          ",'startstation','5:00-22:00','end','5:35-22:35','不是')";
      try {
        if (!stmt.execute(sqlinsert) & !stmt.execute(sqlline)) {
          JOptionPane.showMessageDialog(null, "车次添加成功！请添加车次的线路信息！");
        }
        else {
          JOptionPane.showMessageDialog(null, "车次添加失败！");
        }
      }
      catch (SQLException e) {

      }
    }
  }

//////////////////////////查找车次函数
  public void searchcheci() {
    checihao = jTextField1.getText();
  }

  //////////////////////////每路车的站点数函数
  public int countnum(int c) {
    int countnum = 0;
    String countsql = "select * from stationinfo where 车次号=" + c + "";
    System.out.println("sql语句："+countsql);
    try {
      ResultSet rs = stmt.executeQuery(countsql);
      while(rs.next()){
        countnum++;
      }
      System.out.println("站点数："+countnum);
      rs.close();
    }
    catch (SQLException sqle1) {
    }
    return countnum;
  }

  ///////////////////////////////////////////增加站点函数
  public void addstation(int c, int x, String sn) {
    int countnum; //站点数目
    countnum = countnum(c);
    //调整插入序号后面的序号都相应的加1
    String updatesql = "update stationinfo set 序号=序号+1 where 车次号="+c+" and 序号>=" + x + " ";
    try {
      stmt.execute(updatesql);
    }
    catch (SQLException updatesqle) {
    }
    //插入要增加的站点
    String sql = "insert into stationinfo values(" + c + "," + x + "," + "\'" +
        sn + "\'" + ")";
    try {
      boolean a = stmt.execute(sql);
      if (!a) {
        JOptionPane.showMessageDialog(null, "插入成功！");
      }
      else {
        JOptionPane.showMessageDialog(null, "插入失败！");
      }
    }
    catch (SQLException inserte) {

    }
    String sql1="select 站点名 from stationinfo where 车次号="+c+" ";
    ResultSet rs1;
    Vector station=new Vector();
    String stationname=null,start=null,end=null;
    try{
      rs1=stmt.executeQuery(sql1);
      while(rs1.next())
      {
        stationname = rs1.getString("站点名");
        station.addElement(stationname);
      }
    }
    catch(SQLException e){}
    int stationsize=station.size();
    start=station.get(0).toString().trim();
    end=station.get(stationsize-1).toString().trim();
    String sql2="update businfo set 始发站='"+start+"' , 终点站='"+end+"' where 车次号="+c+"";
    System.out.println(sql2);
    try{
      stmt.executeUpdate(sql2);
    }catch(SQLException e2){}
  }

  ///////////////////////////////////////////删除站点函数
  public void delstation(int c, int x, String sn) {
    checihao = jTextField2.getText(); //车次号
    stationname = jTextField3.getText(); //站点名
    xuhao = jTextField4.getText(); //序号
    int countnum=0;//每次删除后记录站点数
    boolean laststation=false;//是否是最后一个站点
    String delsql = "delete from stationinfo where 车次号=" + c + " and 序号=" + x +
        "";
    if(!laststation){
      countnum = countnum(c); //返回该线路的站点数
    }
    if(countnum==1){
      laststation=true;//最后一个站点
      //如果站点数为0，则该线路为空
      int b=JOptionPane.showConfirmDialog(null,"最后一个站点，删除后，该线路将被删除！确实要删除？");
      if(b==0){
        //确认删除
        try{
          if(stmt.execute(delsql))
          {//删除失败
            JOptionPane.showMessageDialog(null,"删除失败！");
          }
          else{
            JOptionPane.showMessageDialog(null,"删除成功！");
          }
        }catch(SQLException e){}
      }
    }
    else{//如果站点数不为空，可以继续进行删除
      try {
        int a = JOptionPane.showConfirmDialog(null, "确实要删除" + sn + "站点？");
        if (a == 0) { //确认删除
          stmt.execute(delsql);
        }
      }
      catch (SQLException delsqle) {
      }
      //删除序号为x的站点，后边的站点序号要减一
      String add1sql = "update stationinfo set 序号=序号-1 where 车次号=" + c +
          " and 序号>" + x + "";
      try {
        if (!stmt.execute(add1sql)) {
          JOptionPane.showMessageDialog(null, "删除成功！");
        }
        else {
          JOptionPane.showMessageDialog(null, "删除失败！");
        }
      }
      catch (SQLException add1sqle) {

      }
      String sql1 = "select 站点名 from stationinfo where 车次号=" + c + " ";
      ResultSet rs1;
      Vector station = new Vector();
      String stationname = null, start = null, end = null;
      try {
        rs1 = stmt.executeQuery(sql1);
        while (rs1.next()) {
          stationname = rs1.getString("站点名");
          station.addElement(stationname);
        }
      }
      catch (SQLException e) {}
      int stationsize = station.size();
      start = station.get(0).toString().trim();
      end = station.get(stationsize - 1).toString().trim();
      String sql2 = "update businfo set 始发站='" + start + "' , 终点站='" + end +
          "' where 车次号=" + c + "";
      System.out.println(sql2);
      try {
        stmt.executeUpdate(sql2);
      }
      catch (SQLException e2) {}
    }

  }

///////////////////////////////修改站点函数
  public void changestation(int c, int x, String sn) {
    String sql = "update stationinfo set 站点名=" + "\'" + sn + "\'" +
        " where 车次号=" + c + " and 序号=" + x + "";
    //ResultSet rs;
    try {
      if (!stmt.execute(sql)) { //执行更新语句
        JOptionPane.showMessageDialog(null, "更新成功！");
      }
      else {
        JOptionPane.showMessageDialog(null, "更新失败！");
      }
    }
    catch (SQLException e1) {

    }
    String sql1="select 站点名 from stationinfo where 车次号="+c+" ";
    ResultSet rs1;
    Vector station=new Vector();
    String stationname=null,start=null,end=null;
    try{
      rs1=stmt.executeQuery(sql1);
      while(rs1.next())
      {
        stationname = rs1.getString("站点名");
        station.addElement(stationname);
      }
    }
    catch(SQLException e){}
    int stationsize=station.size();
    start=station.get(0).toString().trim();
    end=station.get(stationsize-1).toString().trim();
    String sql2="update businfo set 始发站='"+start+"' , 终点站='"+end+"' where 车次号="+c+"";
    System.out.println(sql2);
    try{
      stmt.executeUpdate(sql2);
    }catch(SQLException e2){}

  }

  //////////////////////////////////////判断车次是否存在
  boolean checicunzai(int checinum) {
    String sql = "select * from stationinfo where 车次号=" + checinum + "";
    ResultSet rs;
    boolean checicunzai = false;
    try {
      rs = stmt.executeQuery(sql);
      while (rs.next()) {
        checicunzai = true;
      }
      rs.close();
    }
    catch (SQLException e) {
    }
    if (checicunzai) {
      return true;
    }
    else
      return false;
  }

/////////////////////////////////////////////////GO按钮
  void jButton11_actionPerformed(ActionEvent e) {
    String line = jTextField1.getText().trim();
    if (line.equals("")) {
      JOptionPane.showMessageDialog(null, "线路名称不能为空！");
    }
    else { //输入线路不为空
      if (jRadioButton1.isSelected()) { //增加车次被选中
    	  int linenum = Integer.parseInt(line);
        jTextField2.setText(line);
        addcheci(linenum);
      }
      else if (jRadioButton2.isSelected()) { //查询车次被选中
        jTextField2.setText(line);
        int linenum = Integer.parseInt(line);
        if (!checicunzai(linenum)) {
          JOptionPane.showMessageDialog(null, "所查车次不存在！");
        }
        else {
          ResultSet rs;
          String sql = "select * from stationinfo where 车次号=" + linenum + "";
          vector.removeAllElements(); //初始化向量对象
          tm.fireTableStructureChanged(); //更新表格内容

          try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
              Vector line_vector = new Vector(); //从结果集中取数据放入向量line_vector中
              line_vector.addElement(String.valueOf(rs.getInt("车次号")));
              line_vector.addElement(String.valueOf(rs.getInt("序号")));
              line_vector.addElement(rs.getString("站点名"));
              vector.addElement(line_vector); //向量line_vector加入向量vector中
            }
            tm.fireTableStructureChanged(); //更新表格，显示向量vector的内容
            rs.close(); //关闭结果集
          }
          catch (SQLException sqle) {
          }
        }
      }
    }
  }

///////////////////////////////////////////修改按钮
  void jButton14_actionPerformed(ActionEvent e) {
    checihao = jTextField2.getText().trim();
    xuhao = jTextField3.getText().trim();
    stationname = jTextField4.getText().trim();

    if (checihao.equals("")) {
      JOptionPane.showMessageDialog(null, "请先查询车次！");
    }
    else if (stationname.equals("")) {
      JOptionPane.showMessageDialog(null, "请填写站点名！");
    }
    else if (xuhao.equals("")) {
      JOptionPane.showMessageDialog(null, "请填写序号！");
    }
    else {
      checinum = Integer.parseInt(checihao);
      stationnum = Integer.parseInt(xuhao);
      changestation(checinum, stationnum, stationname);
    }

  }

  //////////////////////////////////////////增加站点
  void jButton12_actionPerformed(ActionEvent e) {

    boolean stationcunzai = false;
    checihao = jTextField2.getText().trim(); //车次号
    stationname = jTextField4.getText().trim(); //站点名
    xuhao = jTextField3.getText().trim(); //序号
    if (checihao.equals("")) {
      JOptionPane.showMessageDialog(null, "请先查询车次！");
    }
    else if (stationname.equals("")) {
      JOptionPane.showMessageDialog(null, "请填写站点名！");
    }
    else if (xuhao.equals("")) {
      JOptionPane.showMessageDialog(null, "请填写序号！");
    }
    else {
      checinum = Integer.parseInt(checihao);
      stationnum = Integer.parseInt(xuhao);
      String sql = "select * from stationinfo where 车次号=" + checinum +
          " and 站点名=" + "\'" + stationname + "\'" + "";
      try {
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
          //判断该站点是否存在
          stationcunzai = true;
        }
        rs.close(); //关闭结果集
      }
      catch (SQLException sqle) {
      }
      if (stationcunzai) {
        //若站点存在
        JOptionPane.showMessageDialog(null, "" + stationname + "站点已经存在！");
      }
      else {
        //该站点不存在，再判断序号是否正确，是否越界
        int countnum = 0;
        String sql1 = "select * from stationinfo where 车次号=" + checinum + "";
        try {
          ResultSet rs1 = stmt.executeQuery(sql1);
          while (rs1.next()) {
            countnum++;
          }
          rs1.close();
        }
        catch (SQLException sqle1) {
        }
        if (stationnum > (countnum + 1)) { //如果输入序号大于车次中最大序号，越界
          JOptionPane.showMessageDialog(null, "输入序号越界！");
        }
        else if (stationnum <= 0) {
          JOptionPane.showMessageDialog(null, "输入序号应大于等于1！");
        }
        else {
          //输入没有错误，可以添加
          addstation(checinum, stationnum, stationname); //添加站点函数
        }
      }
    }
  }

  ////////////////////////////////////////////////删除站点
  void jButton13_actionPerformed(ActionEvent e) {

    boolean stationcunzai = false;
    checihao = jTextField2.getText().trim(); //车次号
    stationname = jTextField4.getText().trim(); //站点名
    xuhao = jTextField3.getText().trim(); //序号
    if (checihao.equals("")) {
      JOptionPane.showMessageDialog(null, "请车次号！");
    }
    else if (stationname.equals("")) {
      JOptionPane.showMessageDialog(null, "请站点名！");
    }
    else if (xuhao.equals("")) {
      JOptionPane.showMessageDialog(null, "请填写序号！");
    }
    else {
      checinum = Integer.parseInt(checihao);
      stationnum = Integer.parseInt(xuhao);
      String sql = "select * from stationinfo where 车次号=" + checinum +
          " and 站点名=" + "\'" + stationname + "\'" + "";
      try {
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
          //判断该站点是否存在
          stationcunzai = true;
        }
        rs.close(); //关闭结果集
      }
      catch (SQLException sqle) {
      }
      if (!stationcunzai) {
        JOptionPane.showMessageDialog(null, "要删除站点不存在！");
      }
      else {
        //可以进行删除
        delstation(checinum, stationnum, stationname);
      }
    }
  }
  class busmanageFrame_jButton6_actionAdapter
      implements java.awt.event.ActionListener {
    busmanageFrame adaptee;

    busmanageFrame_jButton6_actionAdapter(busmanageFrame adaptee) {
      this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
      adaptee.jButton6_actionPerformed(e);
    }
  }

  class busmanageFrame_jButton7_actionAdapter
      implements java.awt.event.ActionListener {
    busmanageFrame adaptee;

    busmanageFrame_jButton7_actionAdapter(busmanageFrame adaptee) {
      this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
      adaptee.jButton7_actionPerformed(e);
    }
  }

  class busmanageFrame_jButton9_actionAdapter
      implements java.awt.event.ActionListener {
    busmanageFrame adaptee;

    busmanageFrame_jButton9_actionAdapter(busmanageFrame adaptee) {
      this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
      adaptee.jButton9_actionPerformed(e);
    }
  }

  class busmanageFrame_jButton8_actionAdapter
      implements java.awt.event.ActionListener {
    busmanageFrame adaptee;

    busmanageFrame_jButton8_actionAdapter(busmanageFrame adaptee) {
      this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
      adaptee.jButton8_actionPerformed(e);
    }
  }

  class busmanageFrame_jButton10_actionAdapter
      implements java.awt.event.ActionListener {
    busmanageFrame adaptee;

    busmanageFrame_jButton10_actionAdapter(busmanageFrame adaptee) {
      this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
      adaptee.jButton10_actionPerformed(e);
    }
  }

  class busmanageFrame_jButton14_actionAdapter
      implements java.awt.event.ActionListener {
    busmanageFrame adaptee;

    busmanageFrame_jButton14_actionAdapter(busmanageFrame adaptee) {
      this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
      adaptee.jButton14_actionPerformed(e);
    }
  }

  class busmanageFrame_jButton12_actionAdapter
      implements java.awt.event.ActionListener {
    busmanageFrame adaptee;

    busmanageFrame_jButton12_actionAdapter(busmanageFrame adaptee) {
      this.adaptee = adaptee;
    }

    public void actionPerformed(ActionEvent e) {
      adaptee.jButton12_actionPerformed(e);
    }
  }

  void jButton17_actionPerformed(ActionEvent e) {
    //查询车次

    int checinum = Integer.parseInt(jTextField11.getText().trim());
    boolean checicunzai = checicunzai(checinum);
    if (checicunzai) {
      String startstation = "", starttime = "", endstation = "", endtime = "",
          kongtiao = "";
      String sql = "select * from businfo where 车次号=" + checinum + "";
      try {
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
          startstation = rs.getString("始发站").trim();
          starttime = rs.getString("营运时间上").trim();
          endstation = rs.getString("终点站").trim();
          endtime = rs.getString("营运时间下").trim();
          kongtiao = rs.getString("是否空调").trim();
        }
        rs.close();
        jTextField6.setText(startstation);
        jTextField7.setText(starttime);
        jTextField8.setText(endstation);
        jTextField9.setText(endtime);
        jTextField10.setText(kongtiao);
      }
      catch (SQLException sqle) {
        System.out.println(sqle);
      }

    }
    else {
      JOptionPane.showMessageDialog(null, "车次不存在！");
    }
  }

  void jButton16_actionPerformed(ActionEvent e) {
//修改车次属性信息
    String line, startstation, starttime, endstation, endtime, kongtiao;
    line = jTextField11.getText().trim();
    int linenum = Integer.parseInt(line);
    startstation = jTextField6.getText().trim();
    starttime = jTextField7.getText().trim();
    endstation = jTextField8.getText().trim();
    endtime = jTextField9.getText().trim();
    kongtiao = jTextField10.getText().trim();


    String sql = "update businfo set 始发站=" + "'" + startstation + "\'" +
        " ,营运时间上=" + "\'" + starttime + "\'" +
        ", 终点站=" + "\'" + endstation + "\'" + " ,营运时间下=" + "\'" + endtime +
        "\'" + ",是否空调=" + "\'" + kongtiao + "\'" +
        " where 车次号=" + linenum + "";
    System.out.println(sql);
    if (checicunzai(linenum)) {
      try {
        if (!stmt.execute(sql)) {
          JOptionPane.showMessageDialog(null, "修改成功！");
        }
        else {
          JOptionPane.showMessageDialog(null, "修改失败！");
        }
      }
      catch (SQLException sqle) {
        System.out.println(sqle);
      }
    }
    else {
      JOptionPane.showMessageDialog(null, "车次不存在，无法修改！");
    }
  }



  void jButton18_actionPerformed(ActionEvent e) {
//删除线路
    String line = jTextField11.getText().trim();
    int linenum = Integer.parseInt(line);
    String sql = "delete from businfo where 车次号=" + linenum + "";
    if (checicunzai(linenum)) {
      int a = JOptionPane.showConfirmDialog(null, "确实要删除" + linenum + "线路？");
      if (a == 0) {
        //确认删除
        try {
          if (!stmt.execute(sql)) {
            JOptionPane.showMessageDialog(null, "删除成功！");
          }
          else {
            JOptionPane.showMessageDialog(null, "删除失败！");
          }
        }
        catch (SQLException sqle) {
          System.out.println(sqle);
        }

      }
    }
    else {
      JOptionPane.showMessageDialog(null, "车次不存在，无法进行删除操作！");
    }

  }

  void jButton15_actionPerformed(ActionEvent e) {
    jTextField3.setText("");
    jTextField4.setText("");
  }
  

}

class busmanageFrame_jButton13_actionAdapter
    implements java.awt.event.ActionListener {
  busmanageFrame adaptee;

  busmanageFrame_jButton13_actionAdapter(busmanageFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton13_actionPerformed(e);
  }
}

class busmanageFrame_jButton11_actionAdapter
    implements java.awt.event.ActionListener {
  busmanageFrame adaptee;

  busmanageFrame_jButton11_actionAdapter(busmanageFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton11_actionPerformed(e);
  }
}
class busmanageFrame_jButton17_actionAdapter
    implements java.awt.event.ActionListener {
  busmanageFrame adaptee;

  busmanageFrame_jButton17_actionAdapter(busmanageFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton17_actionPerformed(e);
  }
}

class busmanageFrame_jButton16_actionAdapter
    implements java.awt.event.ActionListener {
  busmanageFrame adaptee;

  busmanageFrame_jButton16_actionAdapter(busmanageFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton16_actionPerformed(e);
  }
}

class busmanageFrame_jButton18_actionAdapter
    implements java.awt.event.ActionListener {
  busmanageFrame adaptee;
  busmanageFrame_jButton18_actionAdapter(busmanageFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton18_actionPerformed(e);
  }
}

class busmanageFrame_jButton15_actionAdapter
    implements java.awt.event.ActionListener {
  busmanageFrame adaptee;

  busmanageFrame_jButton15_actionAdapter(busmanageFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent e) {
    adaptee.jButton15_actionPerformed(e);
  }
}

class busmanageFrame_jButton12_actionAdapter implements java.awt.event.ActionListener {
  busmanageFrame adaptee;
  busmanageFrame_jButton12_actionAdapter(busmanageFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton12_actionPerformed(e);
  }
}
class busmanageFrame_jButton14_actionAdapter implements java.awt.event.ActionListener {
  busmanageFrame adaptee;

  busmanageFrame_jButton14_actionAdapter(busmanageFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton14_actionPerformed(e);
  }
}

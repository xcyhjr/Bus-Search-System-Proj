import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import javax.swing.table.*;

public class busmanageFrame extends JFrame {
  JPanel contentPane;

  JTabbedPane jTabbedPane1 = new JTabbedPane(); //��ѡ�������
  
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
//********************��дhelp�ļ�******************************************//
  String helpline = null;
  String helptxt = "";
  public void readhelp()
  {
    //�����ļ�help������
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
    //�༭����
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
//*************************��д��ͨ��Ϣ*******************************//
  String transinfoline = null;
  String transinfotxt = "";
  public void readtransinfo()
  {
    //��ȡ ������˾���ϣ������ļ�transinfo������
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
    //�༭������˾����
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
//****************�������ݿ�************************************************//
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
    	System.out.println("���ݿ�����ʧ�ܣ�");  
    }
  }
//*************************************************************************//
//*****************************��ʼ��***************************************//
  private void Init() throws Exception
  {
    condatabase(); //�������ݿ�
    contentPane = (JPanel)this.getContentPane();
    contentPane.setLayout(null);//���ò���Ϊ��
    this.setResizable(false);//���ɸı��С
    this.setSize(new Dimension(600, 400));//������ʾ���ڴ�С
    this.setTitle("�����й�������ϵͳ");//���ñ���

    contentPane.add(jPanel2, null);
    
    //********�����������ڷ��ø���panel�Ͳ˵�************//
    jPanel2.setBounds(new Rectangle(0, 0, 600, 400));
    jPanel2.setLayout(null);
    jPanel2.add(jTabbedPane1,null);
    
    
    
    jTabbedPane1.setEnabled(true);
    jTabbedPane1.setForeground(Color.black);
    jTabbedPane1.setBorder(BorderFactory.createLineBorder(Color.black));
    jTabbedPane1.setPreferredSize(new Dimension(79, 130));
    jTabbedPane1.setBounds(new Rectangle( 0, 0, 600, 400));
    jTabbedPane1.setTabPlacement(JTabbedPane.TOP);
    jTabbedPane1.add(jPanel4, "վ�����");
    jTabbedPane1.add(jPanel5, "��·����");
    jTabbedPane1.add(jPanel7, "��ͨ��Ϣ�༭");
    jTabbedPane1.add(jPanel8, "�������ݱ༭");
    
    
    //********վ���������************//
    
    jPanel4.setLayout(null);
    
    jTextField1.setText("���복�κ�");
    jTextField1.setBounds(new Rectangle(5, 13, 200, 32));
    
    jButton11.setBounds(new Rectangle(210, 15, 50, 33));
    jButton11.setText("ȷ��");
    jButton11.addActionListener(new busmanageFrame_jButton11_actionAdapter(this));
    
    jRadioButton1.setBackground(SystemColor.inactiveCaptionText);
    jRadioButton1.setText("���ӳ���");   
    jRadioButton1.setBounds(new Rectangle(5, 45, 100, 25));
    
    jRadioButton2.setBackground(SystemColor.inactiveCaptionText);
    jRadioButton2.setSelected(true);
    jRadioButton2.setText("��ѯ����");
    jRadioButton2.setBounds(new Rectangle(105, 45, 100, 25));
    
    bg.add(jRadioButton1);
    bg.add(jRadioButton2);
    
    jButton12.setBounds(new Rectangle(10, 10, 80, 35));
    jButton12.setText("���վ��");
    jButton12.addActionListener(new busmanageFrame_jButton12_actionAdapter(this));

    jButton13.setBounds(new Rectangle(10, 50, 80, 35));
    jButton13.setText("ɾ��վ��");
    jButton13.addActionListener(new busmanageFrame_jButton13_actionAdapter(this));

    jButton14.setBounds(new Rectangle(10, 90, 80, 35));
    jButton14.setText("�޸�");
    jButton14.addActionListener(new busmanageFrame_jButton14_actionAdapter(this));
    
    jButton15.setBounds(new Rectangle(10, 130, 80, 35));
    jButton15.setText("���");
    jButton15.addActionListener(new busmanageFrame_jButton15_actionAdapter(this));

    jButton6.setBounds(new Rectangle(10, 170, 80, 35));
    jButton6.setText("�˳�");
    jButton6.addActionListener(new busmanageFrame_jButton6_actionAdapter(this));
    
    jPanel9.setBorder(BorderFactory.createEtchedBorder());
    jPanel9.setBounds(new Rectangle(260, 80, 102, 250));
    jPanel9.setLayout(null);
    jPanel9.add(jButton13, null);
    jPanel9.add(jButton12, null);
    jPanel9.add(jButton15, null);
    jPanel9.add(jButton14, null);
    jPanel9.add(jButton6, null);
    
    jLabel8.setText("���κ�");
    jLabel8.setBounds(new Rectangle(10, 5, 50, 25));
    
    jTextField2.setEditable(false);
    jTextField2.setText("");
    jTextField2.setBounds(new Rectangle(60, 3, 120, 30));

    jLabel9.setText("���");
    jLabel9.setBounds(new Rectangle(10, 40, 50, 25));
   
    jTextField3.setSelectionStart(11);
    jTextField3.setText("");
    jTextField3.setBounds(new Rectangle(60, 38, 120, 30));
    
    jLabel10.setText("վ����");
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
    jTextArea3.setText("���Ƚ��в�ѯ�������ӳ��β�����Ȼ�������Ӧ�����ɾ���������޸Ĳ��������ɾ�����޸Ĳ��������ĳһ������·��վ�㡣�����κ�һ������ʾ�˳���ʱ����Ϳ��Խ�����ز����ˡ�����Բ�����ߵı����в���");
    jTextArea3.setLineWrap(true);
    jTextArea3.setBounds(new Rectangle(1, 1, 201, 194));
    jTextArea3.setOpaque(false);
    
    jPanel12.add(jTextArea3, null);
    jPanel12.setBorder(BorderFactory.createEtchedBorder());
    jPanel12.setBounds(new Rectangle(370, 136, 201, 194));
    jPanel12.setLayout(null);
    
    //********��·��������************//
    
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
    
    jLabel11.setText("����");
    jLabel11.setBounds(new Rectangle(40, 50, 60, 20));
    
    jTextField11.setBounds(new Rectangle(100, 50, 80, 20));
    jTextField11.setBorder(BorderFactory.createLineBorder(Color.black));
    
    jButton17.setBounds(new Rectangle(190, 50, 46, 21));
    jButton17.addActionListener(new busmanageFrame_jButton17_actionAdapter(this));
    jButton17.setText("ȷ��");
    
    jLabel12.setText("��ʼվ");
    jLabel12.setBounds(new Rectangle(40, 100, 60, 20));
    jTextField6.setBounds(new Rectangle(100, 100, 150, 20));
    jTextField6.setBorder(BorderFactory.createLineBorder(Color.black));
    jTextField6.setText("");
    
    jLabel13.setText("����Ӫ��ʱ��");
    jLabel13.setBounds(new Rectangle(310, 100, 80, 20));
    jTextField7.setBounds(new Rectangle(400, 100, 150, 20));
    jTextField7.setBorder(BorderFactory.createLineBorder(Color.black));
    jTextField7.setText("");
    
    jLabel14.setText("�յ�վ");
    jLabel14.setBounds(new Rectangle(40, 150, 60, 20));
    jTextField8.setBounds(new Rectangle(100, 150, 150, 20));
    jTextField8.setBorder(BorderFactory.createLineBorder(Color.black));
    jTextField8.setText("");
    
    jLabel15.setToolTipText("");
    jLabel15.setText("����Ӫ��ʱ��");
    jLabel15.setBounds(new Rectangle(310, 150, 80, 20));
    jTextField9.setBounds(new Rectangle(400, 150, 150, 20));
    jTextField9.setBorder(BorderFactory.createLineBorder(Color.black));
    jTextField9.setText("");
    
    jLabel16.setText("�Ƿ�յ�");
    jLabel16.setBounds(new Rectangle(40, 200, 60, 20));
    jTextField10.setBounds(new Rectangle(100, 200, 150, 20));
    jTextField10.setBorder(BorderFactory.createLineBorder(Color.black));
    jTextField10.setText("");
 
    jButton16.setBounds(new Rectangle(200, 230, 75, 30));
    jButton16.setText("�޸�");
    jButton16.addActionListener(new busmanageFrame_jButton16_actionAdapter(this));

    jButton18.setBounds(new Rectangle(330, 230, 75, 30));
    jButton18.setText("ɾ��");
    jButton18.addActionListener(new busmanageFrame_jButton18_actionAdapter(this));
  
    jTextArea4.setEditable(false);
    jTextArea4.setLineWrap(true);
    jTextArea4.setText("�������복�κţ����ȷ���ı������ʾ�˳��ε�������Ϣ�������ֱ�����ı����н����޸����Ժ�ɾ����·�������༭��ɺ����޸İ�ť���ɱ���");
    jTextArea4.setBounds(new Rectangle(1, 1, 550, 50));
    jTextArea4.setOpaque(false);

    jPanel6.setBounds(new Rectangle(15, 280, 550, 50));
    jPanel6.setLayout(null);
    jPanel6.setBorder(BorderFactory.createEtchedBorder());
    jPanel6.add(jTextArea4, null);
  
    //********��ͨ��Ϣ�༭����************//
    
    jPanel7.setLayout(null);
    jPanel7.add(jScrollPane1, null);
    jPanel7.add(jButton7, null);
    jPanel7.add(jButton8, null);
    jPanel7.add(jPanel13, null);
    
    jScrollPane1.setBounds(new Rectangle(3, 10, 500, 250));
    jScrollPane1.getViewport().add(jTextArea1, null);
    
    jButton7.setBounds(new Rectangle(320, 280, 80, 25));
    jButton7.setText("����");
    jButton7.addActionListener(new busmanageFrame_jButton7_actionAdapter(this));

    jButton8.setBounds(new Rectangle(150, 280, 80, 25));
    jButton8.setText("��ȡ");
    jButton8.addActionListener(new busmanageFrame_jButton8_actionAdapter(this));
    
    jTextArea5.setLineWrap(true);
    jTextArea5.setText("�ȵ����ȡ��ť���ı����л���ʾ�Ѵ��ڵ����ݣ����ı����н��б༭��Ȼ�������水ť���档");
    jTextArea5.setBounds(new Rectangle(1, 1, 60, 320));
    jTextArea5.setOpaque(false);
    
    jPanel13.setBorder(BorderFactory.createEtchedBorder());
    jPanel13.setBounds(new Rectangle(510, 10, 60, 320));//�ĵ��༭����panel
    jPanel13.setLayout(null);
    jPanel13.add(jTextArea5, null);
    
  //********������Ϣ�༭����************//
    
    jPanel8.setLayout(null);
    jPanel8.add(jScrollPane2, null);
    jPanel8.add(jButton9, null);
    jPanel8.add(jButton10, null);
    jPanel8.add(jPanel14, null);
    
    jButton9.setBounds(new Rectangle(150, 280, 80, 25));
    jButton9.setText("��ȡ");
    jButton9.addActionListener(new busmanageFrame_jButton9_actionAdapter(this));

    jButton10.setBounds(new Rectangle(320, 280, 80, 25));
    jButton10.setText("����");
    jButton10.addActionListener(new busmanageFrame_jButton10_actionAdapter(this));
    
    jTextArea6.setLineWrap(true);
    jTextArea6.setText("�ȵ����ȡ��ť���ı����л���ʾ�Ѵ��ڵ����ݣ����ı����н��б༭��Ȼ�������水ť���档");
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
    //ϵͳ�˳�
    this.dispose();
    new client();
    //this.setVisible(false);
    //System.exit(0);
  }

  void jButton8_actionPerformed(ActionEvent e) {
    //��ȡ ������˾����
    readtransinfo();
    jTextArea1.setText(transinfotxt);
    jButton8.setEnabled(false);
    jButton7.setEnabled(true);
  }

  void jButton7_actionPerformed(ActionEvent e) {
    //�༭������˾����
    writetransinfo();
    jButton8.setEnabled(true);
    jButton7.setEnabled(true);
  }

  void jButton9_actionPerformed(ActionEvent e) {
    //��ȡ����
    readhelp();
    jTextArea2.setText(helptxt);
    jButton9.setEnabled(false);
    jButton10.setEnabled(true);
  }

  void jButton10_actionPerformed(ActionEvent e) {
    //�༭�����ĵ�
    writehelp();
    jButton9.setEnabled(true);
    jButton10.setEnabled(true);
  }

  String title[] = {
      "���κ�", "���", "վ����"}; //��ά������
  Vector vector;
  JScrollPane scroll;
  AbstractTableModel tm;
  
  void createtable() {
    JTable table;
    vector = new Vector();
    tm = new AbstractTableModel() {
      public int getColumnCount() {
        //ȡ�ñ������
        return title.length;
      }

      public int getRowCount() {
        //ȡ�ñ������
        return vector.size();
      }

      public Object getValueAt(int row, int column) {
        //ȡ�õ�Ԫ���е�����ֵ
        if (!vector.isEmpty()) {
          return ( (Vector) vector.elementAt(row)).elementAt(column);
        }
        else {
          return null;
        }
      }

      public void setValueAt(Object value, int row, int column) {
        //����ģ�Ͳ��ɱ༭���÷�������Ϊ��
      }

      public String getColumnName(int column) {
        //ȡ�ñ������
        return title[column];
      }

      public Class getColumnClass(int c) {
        //ȡ��������������
        return getValueAt(0, c).getClass();
      }

      public boolean isCellEditable(int row, int column) {
        //���õ�Ԫ�񲻿ɱ༭
        return false;
      }
    };
    table = new JTable(tm); //�����Լ�������ģ��
    table.setToolTipText("·��"); //���ð�����ʾ
    //table.setAutoResizeMode(table.AUTO_RESIZE_OFF); //���ñ������ߴ�ģʽ
    table.setCellSelectionEnabled(true); //���õ�Ԫ��ѡ��ʽ
    table.getColumn("���κ�").setPreferredWidth(60);
    table.getColumn("���").setPreferredWidth(60);
    table.getColumn("վ����").setPreferredWidth(130);
    
    table.setShowHorizontalLines(true); //�����Ƿ���ʾ��Ԫ��֮��ķָ���
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

/////////////////////////////////////վ�����
  String checihao, xuhao, stationname;
  int checinum, stationnum;
  ////////////////////////���ӳ��κ���
  public void addcheci(int linenum) {
    String sql = "select * from stationinfo where ���κ�=" + linenum + "";
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
      JOptionPane.showMessageDialog(null, "��Ҫ��ӵĳ��γ����Ѿ����ڣ�");
    }
    else {
      //���β����ڣ��ɽ�����ӡ�
      String sqlinsert = "insert into stationinfo values(" + linenum +
          ",1,'0')";
      String sqlline = "insert into businfo values(" + linenum +
          ",'startstation','5:00-22:00','end','5:35-22:35','����')";
      try {
        if (!stmt.execute(sqlinsert) & !stmt.execute(sqlline)) {
          JOptionPane.showMessageDialog(null, "������ӳɹ�������ӳ��ε���·��Ϣ��");
        }
        else {
          JOptionPane.showMessageDialog(null, "�������ʧ�ܣ�");
        }
      }
      catch (SQLException e) {

      }
    }
  }

//////////////////////////���ҳ��κ���
  public void searchcheci() {
    checihao = jTextField1.getText();
  }

  //////////////////////////ÿ·����վ��������
  public int countnum(int c) {
    int countnum = 0;
    String countsql = "select * from stationinfo where ���κ�=" + c + "";
    System.out.println("sql��䣺"+countsql);
    try {
      ResultSet rs = stmt.executeQuery(countsql);
      while(rs.next()){
        countnum++;
      }
      System.out.println("վ������"+countnum);
      rs.close();
    }
    catch (SQLException sqle1) {
    }
    return countnum;
  }

  ///////////////////////////////////////////����վ�㺯��
  public void addstation(int c, int x, String sn) {
    int countnum; //վ����Ŀ
    countnum = countnum(c);
    //����������ź������Ŷ���Ӧ�ļ�1
    String updatesql = "update stationinfo set ���=���+1 where ���κ�="+c+" and ���>=" + x + " ";
    try {
      stmt.execute(updatesql);
    }
    catch (SQLException updatesqle) {
    }
    //����Ҫ���ӵ�վ��
    String sql = "insert into stationinfo values(" + c + "," + x + "," + "\'" +
        sn + "\'" + ")";
    try {
      boolean a = stmt.execute(sql);
      if (!a) {
        JOptionPane.showMessageDialog(null, "����ɹ���");
      }
      else {
        JOptionPane.showMessageDialog(null, "����ʧ�ܣ�");
      }
    }
    catch (SQLException inserte) {

    }
    String sql1="select վ���� from stationinfo where ���κ�="+c+" ";
    ResultSet rs1;
    Vector station=new Vector();
    String stationname=null,start=null,end=null;
    try{
      rs1=stmt.executeQuery(sql1);
      while(rs1.next())
      {
        stationname = rs1.getString("վ����");
        station.addElement(stationname);
      }
    }
    catch(SQLException e){}
    int stationsize=station.size();
    start=station.get(0).toString().trim();
    end=station.get(stationsize-1).toString().trim();
    String sql2="update businfo set ʼ��վ='"+start+"' , �յ�վ='"+end+"' where ���κ�="+c+"";
    System.out.println(sql2);
    try{
      stmt.executeUpdate(sql2);
    }catch(SQLException e2){}
  }

  ///////////////////////////////////////////ɾ��վ�㺯��
  public void delstation(int c, int x, String sn) {
    checihao = jTextField2.getText(); //���κ�
    stationname = jTextField3.getText(); //վ����
    xuhao = jTextField4.getText(); //���
    int countnum=0;//ÿ��ɾ�����¼վ����
    boolean laststation=false;//�Ƿ������һ��վ��
    String delsql = "delete from stationinfo where ���κ�=" + c + " and ���=" + x +
        "";
    if(!laststation){
      countnum = countnum(c); //���ظ���·��վ����
    }
    if(countnum==1){
      laststation=true;//���һ��վ��
      //���վ����Ϊ0�������·Ϊ��
      int b=JOptionPane.showConfirmDialog(null,"���һ��վ�㣬ɾ���󣬸���·����ɾ����ȷʵҪɾ����");
      if(b==0){
        //ȷ��ɾ��
        try{
          if(stmt.execute(delsql))
          {//ɾ��ʧ��
            JOptionPane.showMessageDialog(null,"ɾ��ʧ�ܣ�");
          }
          else{
            JOptionPane.showMessageDialog(null,"ɾ���ɹ���");
          }
        }catch(SQLException e){}
      }
    }
    else{//���վ������Ϊ�գ����Լ�������ɾ��
      try {
        int a = JOptionPane.showConfirmDialog(null, "ȷʵҪɾ��" + sn + "վ�㣿");
        if (a == 0) { //ȷ��ɾ��
          stmt.execute(delsql);
        }
      }
      catch (SQLException delsqle) {
      }
      //ɾ�����Ϊx��վ�㣬��ߵ�վ�����Ҫ��һ
      String add1sql = "update stationinfo set ���=���-1 where ���κ�=" + c +
          " and ���>" + x + "";
      try {
        if (!stmt.execute(add1sql)) {
          JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
        }
        else {
          JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�");
        }
      }
      catch (SQLException add1sqle) {

      }
      String sql1 = "select վ���� from stationinfo where ���κ�=" + c + " ";
      ResultSet rs1;
      Vector station = new Vector();
      String stationname = null, start = null, end = null;
      try {
        rs1 = stmt.executeQuery(sql1);
        while (rs1.next()) {
          stationname = rs1.getString("վ����");
          station.addElement(stationname);
        }
      }
      catch (SQLException e) {}
      int stationsize = station.size();
      start = station.get(0).toString().trim();
      end = station.get(stationsize - 1).toString().trim();
      String sql2 = "update businfo set ʼ��վ='" + start + "' , �յ�վ='" + end +
          "' where ���κ�=" + c + "";
      System.out.println(sql2);
      try {
        stmt.executeUpdate(sql2);
      }
      catch (SQLException e2) {}
    }

  }

///////////////////////////////�޸�վ�㺯��
  public void changestation(int c, int x, String sn) {
    String sql = "update stationinfo set վ����=" + "\'" + sn + "\'" +
        " where ���κ�=" + c + " and ���=" + x + "";
    //ResultSet rs;
    try {
      if (!stmt.execute(sql)) { //ִ�и������
        JOptionPane.showMessageDialog(null, "���³ɹ���");
      }
      else {
        JOptionPane.showMessageDialog(null, "����ʧ�ܣ�");
      }
    }
    catch (SQLException e1) {

    }
    String sql1="select վ���� from stationinfo where ���κ�="+c+" ";
    ResultSet rs1;
    Vector station=new Vector();
    String stationname=null,start=null,end=null;
    try{
      rs1=stmt.executeQuery(sql1);
      while(rs1.next())
      {
        stationname = rs1.getString("վ����");
        station.addElement(stationname);
      }
    }
    catch(SQLException e){}
    int stationsize=station.size();
    start=station.get(0).toString().trim();
    end=station.get(stationsize-1).toString().trim();
    String sql2="update businfo set ʼ��վ='"+start+"' , �յ�վ='"+end+"' where ���κ�="+c+"";
    System.out.println(sql2);
    try{
      stmt.executeUpdate(sql2);
    }catch(SQLException e2){}

  }

  //////////////////////////////////////�жϳ����Ƿ����
  boolean checicunzai(int checinum) {
    String sql = "select * from stationinfo where ���κ�=" + checinum + "";
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

/////////////////////////////////////////////////GO��ť
  void jButton11_actionPerformed(ActionEvent e) {
    String line = jTextField1.getText().trim();
    if (line.equals("")) {
      JOptionPane.showMessageDialog(null, "��·���Ʋ���Ϊ�գ�");
    }
    else { //������·��Ϊ��
      if (jRadioButton1.isSelected()) { //���ӳ��α�ѡ��
    	  int linenum = Integer.parseInt(line);
        jTextField2.setText(line);
        addcheci(linenum);
      }
      else if (jRadioButton2.isSelected()) { //��ѯ���α�ѡ��
        jTextField2.setText(line);
        int linenum = Integer.parseInt(line);
        if (!checicunzai(linenum)) {
          JOptionPane.showMessageDialog(null, "���鳵�β����ڣ�");
        }
        else {
          ResultSet rs;
          String sql = "select * from stationinfo where ���κ�=" + linenum + "";
          vector.removeAllElements(); //��ʼ����������
          tm.fireTableStructureChanged(); //���±������

          try {
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
              Vector line_vector = new Vector(); //�ӽ������ȡ���ݷ�������line_vector��
              line_vector.addElement(String.valueOf(rs.getInt("���κ�")));
              line_vector.addElement(String.valueOf(rs.getInt("���")));
              line_vector.addElement(rs.getString("վ����"));
              vector.addElement(line_vector); //����line_vector��������vector��
            }
            tm.fireTableStructureChanged(); //���±����ʾ����vector������
            rs.close(); //�رս����
          }
          catch (SQLException sqle) {
          }
        }
      }
    }
  }

///////////////////////////////////////////�޸İ�ť
  void jButton14_actionPerformed(ActionEvent e) {
    checihao = jTextField2.getText().trim();
    xuhao = jTextField3.getText().trim();
    stationname = jTextField4.getText().trim();

    if (checihao.equals("")) {
      JOptionPane.showMessageDialog(null, "���Ȳ�ѯ���Σ�");
    }
    else if (stationname.equals("")) {
      JOptionPane.showMessageDialog(null, "����дվ������");
    }
    else if (xuhao.equals("")) {
      JOptionPane.showMessageDialog(null, "����д��ţ�");
    }
    else {
      checinum = Integer.parseInt(checihao);
      stationnum = Integer.parseInt(xuhao);
      changestation(checinum, stationnum, stationname);
    }

  }

  //////////////////////////////////////////����վ��
  void jButton12_actionPerformed(ActionEvent e) {

    boolean stationcunzai = false;
    checihao = jTextField2.getText().trim(); //���κ�
    stationname = jTextField4.getText().trim(); //վ����
    xuhao = jTextField3.getText().trim(); //���
    if (checihao.equals("")) {
      JOptionPane.showMessageDialog(null, "���Ȳ�ѯ���Σ�");
    }
    else if (stationname.equals("")) {
      JOptionPane.showMessageDialog(null, "����дվ������");
    }
    else if (xuhao.equals("")) {
      JOptionPane.showMessageDialog(null, "����д��ţ�");
    }
    else {
      checinum = Integer.parseInt(checihao);
      stationnum = Integer.parseInt(xuhao);
      String sql = "select * from stationinfo where ���κ�=" + checinum +
          " and վ����=" + "\'" + stationname + "\'" + "";
      try {
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
          //�жϸ�վ���Ƿ����
          stationcunzai = true;
        }
        rs.close(); //�رս����
      }
      catch (SQLException sqle) {
      }
      if (stationcunzai) {
        //��վ�����
        JOptionPane.showMessageDialog(null, "" + stationname + "վ���Ѿ����ڣ�");
      }
      else {
        //��վ�㲻���ڣ����ж�����Ƿ���ȷ���Ƿ�Խ��
        int countnum = 0;
        String sql1 = "select * from stationinfo where ���κ�=" + checinum + "";
        try {
          ResultSet rs1 = stmt.executeQuery(sql1);
          while (rs1.next()) {
            countnum++;
          }
          rs1.close();
        }
        catch (SQLException sqle1) {
        }
        if (stationnum > (countnum + 1)) { //���������Ŵ��ڳ����������ţ�Խ��
          JOptionPane.showMessageDialog(null, "�������Խ�磡");
        }
        else if (stationnum <= 0) {
          JOptionPane.showMessageDialog(null, "�������Ӧ���ڵ���1��");
        }
        else {
          //����û�д��󣬿������
          addstation(checinum, stationnum, stationname); //���վ�㺯��
        }
      }
    }
  }

  ////////////////////////////////////////////////ɾ��վ��
  void jButton13_actionPerformed(ActionEvent e) {

    boolean stationcunzai = false;
    checihao = jTextField2.getText().trim(); //���κ�
    stationname = jTextField4.getText().trim(); //վ����
    xuhao = jTextField3.getText().trim(); //���
    if (checihao.equals("")) {
      JOptionPane.showMessageDialog(null, "�복�κţ�");
    }
    else if (stationname.equals("")) {
      JOptionPane.showMessageDialog(null, "��վ������");
    }
    else if (xuhao.equals("")) {
      JOptionPane.showMessageDialog(null, "����д��ţ�");
    }
    else {
      checinum = Integer.parseInt(checihao);
      stationnum = Integer.parseInt(xuhao);
      String sql = "select * from stationinfo where ���κ�=" + checinum +
          " and վ����=" + "\'" + stationname + "\'" + "";
      try {
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
          //�жϸ�վ���Ƿ����
          stationcunzai = true;
        }
        rs.close(); //�رս����
      }
      catch (SQLException sqle) {
      }
      if (!stationcunzai) {
        JOptionPane.showMessageDialog(null, "Ҫɾ��վ�㲻���ڣ�");
      }
      else {
        //���Խ���ɾ��
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
    //��ѯ����

    int checinum = Integer.parseInt(jTextField11.getText().trim());
    boolean checicunzai = checicunzai(checinum);
    if (checicunzai) {
      String startstation = "", starttime = "", endstation = "", endtime = "",
          kongtiao = "";
      String sql = "select * from businfo where ���κ�=" + checinum + "";
      try {
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
          startstation = rs.getString("ʼ��վ").trim();
          starttime = rs.getString("Ӫ��ʱ����").trim();
          endstation = rs.getString("�յ�վ").trim();
          endtime = rs.getString("Ӫ��ʱ����").trim();
          kongtiao = rs.getString("�Ƿ�յ�").trim();
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
      JOptionPane.showMessageDialog(null, "���β����ڣ�");
    }
  }

  void jButton16_actionPerformed(ActionEvent e) {
//�޸ĳ���������Ϣ
    String line, startstation, starttime, endstation, endtime, kongtiao;
    line = jTextField11.getText().trim();
    int linenum = Integer.parseInt(line);
    startstation = jTextField6.getText().trim();
    starttime = jTextField7.getText().trim();
    endstation = jTextField8.getText().trim();
    endtime = jTextField9.getText().trim();
    kongtiao = jTextField10.getText().trim();


    String sql = "update businfo set ʼ��վ=" + "'" + startstation + "\'" +
        " ,Ӫ��ʱ����=" + "\'" + starttime + "\'" +
        ", �յ�վ=" + "\'" + endstation + "\'" + " ,Ӫ��ʱ����=" + "\'" + endtime +
        "\'" + ",�Ƿ�յ�=" + "\'" + kongtiao + "\'" +
        " where ���κ�=" + linenum + "";
    System.out.println(sql);
    if (checicunzai(linenum)) {
      try {
        if (!stmt.execute(sql)) {
          JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
        }
        else {
          JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�");
        }
      }
      catch (SQLException sqle) {
        System.out.println(sqle);
      }
    }
    else {
      JOptionPane.showMessageDialog(null, "���β����ڣ��޷��޸ģ�");
    }
  }



  void jButton18_actionPerformed(ActionEvent e) {
//ɾ����·
    String line = jTextField11.getText().trim();
    int linenum = Integer.parseInt(line);
    String sql = "delete from businfo where ���κ�=" + linenum + "";
    if (checicunzai(linenum)) {
      int a = JOptionPane.showConfirmDialog(null, "ȷʵҪɾ��" + linenum + "��·��");
      if (a == 0) {
        //ȷ��ɾ��
        try {
          if (!stmt.execute(sql)) {
            JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
          }
          else {
            JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�");
          }
        }
        catch (SQLException sqle) {
          System.out.println(sqle);
        }

      }
    }
    else {
      JOptionPane.showMessageDialog(null, "���β����ڣ��޷�����ɾ��������");
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

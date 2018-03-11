import java.sql.*;
import java.util.*;

public class busSearch {
  //private String station_Name;//����վ����
  //private int line_Number;//�������κ�
  String databasename = "jnbus"; //����Դ��
  connectDB conndb = new connectDB(); //�����������ݿ����һ������
  public busSearch() {
  }

  /////////////////////////////////////////////////////////////////////////////
  public int countStation(String startStation, String endStation,int line_number) {
    //���ܣ���ĳһ�����Σ���ѯ��ʼվ���յ�վ֮���վ����(��ֱ���վ��)
    //����ֵ�����Σ�վ����
    conndb.connectDb(databasename);
    int countStation = 0; //��¼վ����
    int startnum = 1, endnum = 1; //��ʼվ����ţ���ֹվ����ţ�����ʼ��Ϊ1
    String sql1 = "select ��� from stationinfo where ���κ�=" + line_number +
        " and վ����=" + "'" + startStation + "'" + "";
    String sql2 = "select ��� from stationinfo where ���κ�=" + line_number +
        " and վ����=" + "'" + endStation + "'" + "";
    ResultSet rs1, rs2;
    try {
      rs1 = conndb.stmt.executeQuery(sql1);
      while (rs1.next()) {
        startnum = rs1.getInt("���"); //��ʼվ�����
      }
      rs1.close();
      rs2 = conndb.stmt.executeQuery(sql2);
      while (rs2.next()) {
        endnum = rs2.getInt("���"); //��ֹվ�����
      }
      rs2.close();
    }
    catch (SQLException e) {
      System.out.println(e); //��ӡsqlִ�д�����Ϣ
    }
    countStation = compare(startnum, endnum); //�������վ����վ����
    return countStation; //����վ����
  }

  /////////////////////////////////////////////////////////////////////////////
  public int compare(int a, int b) {
    //���ܣ�������������֮��
    //����ֵ�����Σ�����֮��
    int c = 0;
    if (a >= b) {
      c = a - b;
    }
    else
      c = b - a;
    return c;
  }

  /////////////////////////////////////////////////////////////////////////////
  public Vector search_Station(String station_name) {
    //���ܣ�����վ�����ƣ����������վ��ĳ��Σ���ȷ��ѯ
    //����ֵ�����κ�����
    conndb.connectDb(databasename);
    int line_number;
    Vector linenum_vector = new Vector(); //���κ�����
    linenum_vector.removeAllElements();
    String sql = "select ���κ� from stationinfo where վ����=" + "'" + station_name +
        "'" + "";
    ResultSet rs;
    try {
      rs = conndb.stmt.executeQuery(sql);
      while (rs.next()) {
        line_number = rs.getInt("���κ�"); //ȡ��������еĳ��κ�
        linenum_vector.addElement(Integer.toString(line_number)); //�����κż�������linenumVector
      }
      rs.close(); //�رս����
    }
    catch (SQLException e) {
      System.out.println(e);
    }
    return linenum_vector; //���س��κ�����
  }

  /////////////////////////////////////////////////////////////////////////////
  public Vector search_closeStation(String station_name) {
    //���ܣ�����ģ��վ�����ƣ���������վ������
    //����ֵ��վ����������
    conndb.connectDb(databasename);
    String station_Name;
    Vector stationname_vector = new Vector(); //վ��������
    stationname_vector.removeAllElements();
    String sql = "select distinct վ���� from stationinfo where վ���� like '%" +
        station_name + "%'";
    ResultSet rs;
    try {
      rs = conndb.stmt.executeQuery(sql);
      while (rs.next()) {
        station_Name = rs.getString("վ����");
        stationname_vector.addElement(station_Name);
      }
      rs.close();
    }
    catch (SQLException e) {
      System.out.println(e);
    }
    return stationname_vector;
  }

  /////////////////////////////////////////////////////////////////////////////
  public Vector search_LineProperty(int line_number) {
    //���ܣ����복�κ�,����ó���ʼ��վ,�յ�վ,����ʱ��,����ʱ��,�Ƿ�յ�������
    //����ֵ�������������ݵ�����
    conndb.connectDb(databasename);
    String startstation, starttime, endstation, endtime, shifoukongtiao; //��·����
    Vector lineproperty_vector = new Vector(); //��·��������
    Vector line_vector = new Vector(); //������·���Լ�������
    lineproperty_vector.removeAllElements(); //�������
    line_vector.removeAllElements(); //�������
    String sql = "select ʼ��վ,Ӫ��ʱ����,�յ�վ,Ӫ��ʱ����,�Ƿ�յ� from businfo where ���κ�=" +
        line_number + "";
    ResultSet rs;
    try {
      rs = conndb.stmt.executeQuery(sql); //ִ����·��������
      while (rs.next()) {
        startstation = rs.getString("ʼ��վ");
        starttime = rs.getString("Ӫ��ʱ����");
        endstation = rs.getString("�յ�վ");
        endtime = rs.getString("Ӫ��ʱ����");
        shifoukongtiao = rs.getString("�Ƿ�յ�");
        lineproperty_vector.addElement(startstation);
        lineproperty_vector.addElement(starttime);
        lineproperty_vector.addElement(endstation);
        lineproperty_vector.addElement(endtime);
        lineproperty_vector.addElement(shifoukongtiao);
        line_vector.add(lineproperty_vector); //����·���Լ�������vector
      }
      rs.close();
    }
    catch (SQLException e) {
      System.out.println(e);
    }
    return line_vector;
  }

  /////////////////////////////////////////////////////////////////////////////
  public Vector search_Linestation(int line_number) {
    //���ܣ����복�κţ�����ó��ξ�����վ��
    //����ֵ������վ������
    conndb.connectDb(databasename);
    String station_name; //վ����
    int station_number; //վ�����
    Vector line_vector = new Vector(); //�ó���վ������
    line_vector.removeAllElements();
    String sql = "select ���,վ���� from stationinfo where ���κ�=" + line_number;
    ResultSet rs;
    try {
      rs = conndb.stmt.executeQuery(sql);
      while (rs.next()) {
        Vector station_vector = new Vector(); //վ������
        station_number = rs.getInt("���");
        station_name = rs.getString("վ����");
        station_vector.addElement(Integer.toString(station_number)); //��ż���վ������
        station_vector.addElement(station_name); //վ��������վ������
        line_vector.add(station_vector); //վ���������복��վ������
      }
      rs.close();
    }
    catch (SQLException e) {
      System.out.println(e);
    }
    return line_vector;
  }

  /////////////////////////////////////////////////////////////////////////////
  public Vector search_nonstop(String start_station, String end_station) {
    //���� ��������ʼվ���յ�վ����ѯ��ֱ��ĳ���
    //����ֵ����ֱ�ﳵ������
    conndb.connectDb(databasename);
    Vector nonstop_vector = new Vector(); //��ֱ�ﳵ������
    nonstop_vector.removeAllElements();
    int line_number;
    String sql = "select ���κ�,��� from stationinfo  where  վ����= '" +
        start_station + "' and ���κ� in(select ���κ� from stationinfo where վ����='" +
        end_station + "')";
    ResultSet rs;
    //վ������ڣ����в�ѯ��
    try {
      rs = conndb.stmt.executeQuery(sql);
      while (rs.next()) {
        line_number = rs.getInt("���κ�");
        nonstop_vector.addElement(Integer.toString(line_number));
      }
      rs.close();
    }
    catch (SQLException e) {
      System.out.println(e);
    }
    return nonstop_vector;
  }

  /////////////////////////////////////////////////////////////////////////////
  public Vector vector_intersection(Vector a, Vector b) {
    //���ܣ������������Ľ���
    //����ֵ����������
    conndb.connectDb(databasename);
    Vector intersection = new Vector(); //��������
    int a_length, b_length;
    a_length = a.size(); //����a�ĳ���
    b_length = b.size(); //����b�ĳ���
    for (int i = 0; i < a_length; i++) {
      String a_element = a.get(i).toString();
      for (int j = 0; j < b_length; j++) {
        String b_element = b.get(j).toString();
        if (a_element.equalsIgnoreCase(b_element)) {
          intersection.add(b_element);
          break;
        }
      }
    }
    return intersection; //���ؽ���
  }

  /////////////////////////////////////////////////////////////////////////////
  public Vector search_oncechange(String start_station, String end_station) {
    //���ܣ� ������ʼվ����ֹվ����ѯһ�λ��˷���������վ�㣬�˳�����
    //����ֵ��һ�γ˳��������ȳ˳��Σ�����վ�㣬���˳���
    conndb.connectDb(databasename);
    String start_station_name, end_station_name;
    Vector start_station_vector = new Vector(); //����վ��start_station�ĳ�����������վ������
    Vector end_station_vector = new Vector(); //����վ��end_station�ĳ�����������վ������
    Vector change_station_vector = new Vector(); //����վ������
    Vector change_start_vector = new Vector(); //�ȳ˳���������
    Vector change_end_vector = new Vector(); //���˳���������
    Vector change_solution_vector = new Vector(); //���˷���������

    change_solution_vector.removeAllElements();
    int start_station_length, end_station_length, change_station_length;
    String sql1 = "select distinct վ���� from stationinfo where ���κ� in(select distinct ���κ� from stationinfo where վ����=" +
        "'" + start_station + "'" + ")";
    String sql2 = "select distinct վ���� from stationinfo where ���κ� in(select distinct ���κ� from stationinfo where վ����=" +
        "'" + end_station + "'" + ")";
    ResultSet rs1, rs2;
    try {
      rs1 = conndb.stmt.executeQuery(sql1);
      while (rs1.next()) {
        start_station_name = rs1.getString("վ����");
        start_station_vector.addElement(start_station_name); //��վ�����start_station_vector����
      }
      start_station_length = start_station_vector.size(); //����start_station_vector�ĳ���
      rs1.close(); //�رս����
      rs2 = conndb.stmt.executeQuery(sql2);
      while (rs2.next()) {
        end_station_name = rs2.getString("վ����");
        end_station_vector.addElement(end_station_name); //��վ�����end_station_vector����
      }
      end_station_length = end_station_vector.size(); //����end_station_vector�ĳ���
      rs2.close(); //�رս����
    }
    catch (SQLException e) {
      System.out.println(e);
    }
    change_station_vector = vector_intersection(start_station_vector,
                                                end_station_vector); //��������,����վ������
    change_station_length = change_station_vector.size();
    for (int i = 0; i < change_station_length; i++) { //���˷���
      Vector line_start_vector = new Vector(); //�ȳ˳�������
      Vector line_end_vector = new Vector(); //���˳�������
      Vector change_vector = new Vector(); //���˷�������
      //change_vector.removeAllElements();//����ջ��˷�������
      String sql3 = "select  distinct ���κ� from stationinfo  where  վ����= '" +
          start_station +
          "' and ���κ� in(select distinct ���κ� from stationinfo where վ����='" +
          change_station_vector.get(i).toString().trim() + "')";
      ResultSet rs3, rs4;
      try {
        rs3 = conndb.stmt.executeQuery(sql3); //����ȳ˳��κ�
        while (rs3.next()) {
          line_start_vector.addElement(Integer.toString(rs3.getInt("���κ�")));
        }
        //change_start_vector.add(line_start_vector);//�ȳ˳�������
        rs3.close();
        String sql4 = "select  distinct ���κ� from stationinfo  where  վ����= '" +
            change_station_vector.get(i).toString().trim() +
            "' and ���κ� in(select distinct ���κ� from stationinfo where վ����='" +
            end_station + "')";
        rs4 = conndb.stmt.executeQuery(sql4);
        while (rs4.next()) {
          line_end_vector.addElement(Integer.toString(rs4.getInt("���κ�")));
        }
        //change_end_vector.add(line_end_vector);//���˳�������
        rs4.close();
      }
      catch (SQLException e) {
        System.out.println(e);
      }
      change_vector.add(line_start_vector);
      change_vector.add(change_station_vector.get(i).toString());
      change_vector.add(line_end_vector);
      change_solution_vector.add(change_vector);
    }
    //˵��1���ȳ˳�������change_start_vector������������
    //˵��2�����˳�������change_end_vector������������
    //˵��3���ȳ˳��������ͻ��˳��������ͻ���վ������������һ���ġ�
    //˵��4�����˷�������change_vector�����ǣ��ȳ˳�������������վ�㣬���˳�������
    //˵��5�����˷���������change_solution_vector�����ǻ��˷�������
    return change_solution_vector;
  }

}
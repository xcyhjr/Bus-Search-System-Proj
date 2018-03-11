import java.sql.*;
import java.util.*;

public class busSearch {
  //private String station_Name;//公交站点名
  //private int line_Number;//公交车次号
  String databasename = "jnbus"; //数据源名
  connectDB conndb = new connectDB(); //声明连接数据库类的一个对象
  public busSearch() {
  }

  /////////////////////////////////////////////////////////////////////////////
  public int countStation(String startStation, String endStation,int line_number) {
    //功能：在某一个车次，查询开始站和终点站之间的站点数(可直达的站点)
    //返回值：整形，站点数
    conndb.connectDb(databasename);
    int countStation = 0; //记录站点数
    int startnum = 1, endnum = 1; //开始站点序号，终止站点序号，并初始化为1
    String sql1 = "select 序号 from stationinfo where 车次号=" + line_number +
        " and 站点名=" + "'" + startStation + "'" + "";
    String sql2 = "select 序号 from stationinfo where 车次号=" + line_number +
        " and 站点名=" + "'" + endStation + "'" + "";
    ResultSet rs1, rs2;
    try {
      rs1 = conndb.stmt.executeQuery(sql1);
      while (rs1.next()) {
        startnum = rs1.getInt("序号"); //开始站点序号
      }
      rs1.close();
      rs2 = conndb.stmt.executeQuery(sql2);
      while (rs2.next()) {
        endnum = rs2.getInt("序号"); //终止站点序号
      }
      rs2.close();
    }
    catch (SQLException e) {
      System.out.println(e); //打印sql执行错误信息
    }
    countStation = compare(startnum, endnum); //求出两个站点间的站点数
    return countStation; //返回站点数
  }

  /////////////////////////////////////////////////////////////////////////////
  public int compare(int a, int b) {
    //功能：计算两个整数之差
    //返回值：整形，两数之差
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
    //功能：输入站点名称，输出经过该站点的车次，精确查询
    //返回值：车次号向量
    conndb.connectDb(databasename);
    int line_number;
    Vector linenum_vector = new Vector(); //车次号向量
    linenum_vector.removeAllElements();
    String sql = "select 车次号 from stationinfo where 站点名=" + "'" + station_name +
        "'" + "";
    ResultSet rs;
    try {
      rs = conndb.stmt.executeQuery(sql);
      while (rs.next()) {
        line_number = rs.getInt("车次号"); //取出结果集中的车次号
        linenum_vector.addElement(Integer.toString(line_number)); //将车次号加入向量linenumVector
      }
      rs.close(); //关闭结果集
    }
    catch (SQLException e) {
      System.out.println(e);
    }
    return linenum_vector; //返回车次号向量
  }

  /////////////////////////////////////////////////////////////////////////////
  public Vector search_closeStation(String station_name) {
    //功能：输入模糊站点名称，输出相近的站点名称
    //返回值：站点名称向量
    conndb.connectDb(databasename);
    String station_Name;
    Vector stationname_vector = new Vector(); //站点名向量
    stationname_vector.removeAllElements();
    String sql = "select distinct 站点名 from stationinfo where 站点名 like '%" +
        station_name + "%'";
    ResultSet rs;
    try {
      rs = conndb.stmt.executeQuery(sql);
      while (rs.next()) {
        station_Name = rs.getString("站点名");
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
    //功能：输入车次号,输出该车次始发站,终点站,上行时间,下行时间,是否空调等属性
    //返回值：所有属性内容的向量
    conndb.connectDb(databasename);
    String startstation, starttime, endstation, endtime, shifoukongtiao; //线路属性
    Vector lineproperty_vector = new Vector(); //线路属性向量
    Vector line_vector = new Vector(); //所有线路属性集合向量
    lineproperty_vector.removeAllElements(); //向量清空
    line_vector.removeAllElements(); //向量清空
    String sql = "select 始发站,营运时间上,终点站,营运时间下,是否空调 from businfo where 车次号=" +
        line_number + "";
    ResultSet rs;
    try {
      rs = conndb.stmt.executeQuery(sql); //执行线路属性搜索
      while (rs.next()) {
        startstation = rs.getString("始发站");
        starttime = rs.getString("营运时间上");
        endstation = rs.getString("终点站");
        endtime = rs.getString("营运时间下");
        shifoukongtiao = rs.getString("是否空调");
        lineproperty_vector.addElement(startstation);
        lineproperty_vector.addElement(starttime);
        lineproperty_vector.addElement(endstation);
        lineproperty_vector.addElement(endtime);
        lineproperty_vector.addElement(shifoukongtiao);
        line_vector.add(lineproperty_vector); //将线路属性加入向量vector
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
    //功能：输入车次号，输出该车次经过的站点
    //返回值：车次站点向量
    conndb.connectDb(databasename);
    String station_name; //站点名
    int station_number; //站点序号
    Vector line_vector = new Vector(); //该车次站点向量
    line_vector.removeAllElements();
    String sql = "select 序号,站点名 from stationinfo where 车次号=" + line_number;
    ResultSet rs;
    try {
      rs = conndb.stmt.executeQuery(sql);
      while (rs.next()) {
        Vector station_vector = new Vector(); //站点向量
        station_number = rs.getInt("序号");
        station_name = rs.getString("站点名");
        station_vector.addElement(Integer.toString(station_number)); //序号加入站点向量
        station_vector.addElement(station_name); //站点名加入站点向量
        line_vector.add(station_vector); //站点向量加入车次站点向量
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
    //功能 ：输入起始站和终点站，查询可直达的车次
    //返回值：可直达车次向量
    conndb.connectDb(databasename);
    Vector nonstop_vector = new Vector(); //可直达车次向量
    nonstop_vector.removeAllElements();
    int line_number;
    String sql = "select 车次号,序号 from stationinfo  where  站点名= '" +
        start_station + "' and 车次号 in(select 车次号 from stationinfo where 站点名='" +
        end_station + "')";
    ResultSet rs;
    //站点均存在，进行查询。
    try {
      rs = conndb.stmt.executeQuery(sql);
      while (rs.next()) {
        line_number = rs.getInt("车次号");
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
    //功能：求两个向量的交集
    //返回值：交集向量
    conndb.connectDb(databasename);
    Vector intersection = new Vector(); //交集向量
    int a_length, b_length;
    a_length = a.size(); //向量a的长度
    b_length = b.size(); //向量b的长度
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
    return intersection; //返回交集
  }

  /////////////////////////////////////////////////////////////////////////////
  public Vector search_oncechange(String start_station, String end_station) {
    //功能： 输入起始站和终止站，查询一次换乘方案，换乘站点，乘车车次
    //返回值：一次乘车向量，先乘车次，换乘站点，换乘车次
    conndb.connectDb(databasename);
    String start_station_name, end_station_name;
    Vector start_station_vector = new Vector(); //经过站点start_station的车次所经过的站点向量
    Vector end_station_vector = new Vector(); //经过站点end_station的车次所经过的站点向量
    Vector change_station_vector = new Vector(); //换乘站点向量
    Vector change_start_vector = new Vector(); //先乘车次向量集
    Vector change_end_vector = new Vector(); //换乘车次向量集
    Vector change_solution_vector = new Vector(); //换乘方案向量集

    change_solution_vector.removeAllElements();
    int start_station_length, end_station_length, change_station_length;
    String sql1 = "select distinct 站点名 from stationinfo where 车次号 in(select distinct 车次号 from stationinfo where 站点名=" +
        "'" + start_station + "'" + ")";
    String sql2 = "select distinct 站点名 from stationinfo where 车次号 in(select distinct 车次号 from stationinfo where 站点名=" +
        "'" + end_station + "'" + ")";
    ResultSet rs1, rs2;
    try {
      rs1 = conndb.stmt.executeQuery(sql1);
      while (rs1.next()) {
        start_station_name = rs1.getString("站点名");
        start_station_vector.addElement(start_station_name); //将站点加入start_station_vector向量
      }
      start_station_length = start_station_vector.size(); //向量start_station_vector的长度
      rs1.close(); //关闭结果集
      rs2 = conndb.stmt.executeQuery(sql2);
      while (rs2.next()) {
        end_station_name = rs2.getString("站点名");
        end_station_vector.addElement(end_station_name); //将站点加入end_station_vector向量
      }
      end_station_length = end_station_vector.size(); //向量end_station_vector的长度
      rs2.close(); //关闭结果集
    }
    catch (SQLException e) {
      System.out.println(e);
    }
    change_station_vector = vector_intersection(start_station_vector,
                                                end_station_vector); //交集向量,换乘站点向量
    change_station_length = change_station_vector.size();
    for (int i = 0; i < change_station_length; i++) { //换乘方案
      Vector line_start_vector = new Vector(); //先乘车次向量
      Vector line_end_vector = new Vector(); //换乘车次向量
      Vector change_vector = new Vector(); //换乘方案向量
      //change_vector.removeAllElements();//先清空换乘方案向量
      String sql3 = "select  distinct 车次号 from stationinfo  where  站点名= '" +
          start_station +
          "' and 车次号 in(select distinct 车次号 from stationinfo where 站点名='" +
          change_station_vector.get(i).toString().trim() + "')";
      ResultSet rs3, rs4;
      try {
        rs3 = conndb.stmt.executeQuery(sql3); //求出先乘车次号
        while (rs3.next()) {
          line_start_vector.addElement(Integer.toString(rs3.getInt("车次号")));
        }
        //change_start_vector.add(line_start_vector);//先乘车次向量
        rs3.close();
        String sql4 = "select  distinct 车次号 from stationinfo  where  站点名= '" +
            change_station_vector.get(i).toString().trim() +
            "' and 车次号 in(select distinct 车次号 from stationinfo where 站点名='" +
            end_station + "')";
        rs4 = conndb.stmt.executeQuery(sql4);
        while (rs4.next()) {
          line_end_vector.addElement(Integer.toString(rs4.getInt("车次号")));
        }
        //change_end_vector.add(line_end_vector);//换乘车次向量
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
    //说明1：先乘车次向量change_start_vector内容是向量。
    //说明2：换乘车次向量change_end_vector内容是向量。
    //说明3：先乘车次向量和换乘车次向量和换乘站点向量长度是一样的。
    //说明4：换乘方案向量change_vector内容是：先乘车次向量，换乘站点，换乘车次向量
    //说明5：换乘方案向量集change_solution_vector内容是换乘方案向量
    return change_solution_vector;
  }

}
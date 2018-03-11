import java.sql.*;

public class connectDB {
	  Connection conn;
	  Statement stmt;
	  public connectDB() {
	  }

	  public void connectDb(String dbName) { //连接数据库
	   /* try {
	      //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	    	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    }
	    catch (ClassNotFoundException e) {
	      System.out.println(e);
	    }
		  */
	    try {
	    	//String url="jdbc:sqlserver://172.20.10.2:1433;DatabaseName=jnbus;";
	      //String url="jdbc:sqlserver://localhost:1433;DatabaseName=jnbus;";
	      String url = "jdbc:sqlserver://172.20.10.2:1433;;DatabaseName=" + dbName + ";";
	      String user = "sa";
	      String password = "12345";
	      conn = DriverManager.getConnection(url, user, password);
	      stmt = conn.createStatement();
	    }
	    catch (SQLException e) {
	    	System.out.println("数据库连接失败！");   
	        e.printStackTrace() ;   
	    }
	  }
	}
package assignment_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class booklist {
	Connection con;

	public booklist() {
	   String url="jdbc:oracle:thin:@localhost:1521:XE"; 
        /* 11g express edition은 orcl 대신 XE를 입력한다. */
	   String userid="madang";
	   String pwd="madang";

	   try { /* 드라이버를 찾는 과정 */
	     Class.forName("oracle.jdbc.driver.OracleDriver");
	     System.out.println ("드라이버 로드 성공");
	   } catch(ClassNotFoundException e) {
		e.printStackTrace();
		
	     }

	   try { /* 데이터베이스를 연결하는 과정 */
	     System.out.println ("데이터베이스 연결 준비 ...");
	     con=DriverManager.getConnection(url, userid, pwd);
	     System.out.println ("데이터베이스 연결 성공");
	     } catch(SQLException e) {
	         e.printStackTrace();
		}
	   }

	   private void sqlRun() {
		   String query="SELECT bookname FROM book b1 WHERE  price >=(SELECT AVG(price) FROM book b2 WHERE b1.publisher=b2.publisher ) ";/* SQL 문 */

		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   ResultSet rs=stmt.executeQuery(query);
			   System.out.println("BOOKNAME");
			   while(rs.next()) {
				   System.out.println(rs.getString(1));
			   }

			   con.close();
		     } catch(SQLException e) {
			     e.printStackTrace();
			}
	}

	public static void main(String args[]) {

	   booklist so=new booklist();
	   so.sqlRun();
	}
}
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
        /* 11g express edition�� orcl ��� XE�� �Է��Ѵ�. */
	   String userid="madang";
	   String pwd="madang";

	   try { /* ����̹��� ã�� ���� */
	     Class.forName("oracle.jdbc.driver.OracleDriver");
	     System.out.println ("����̹� �ε� ����");
	   } catch(ClassNotFoundException e) {
		e.printStackTrace();
		
	     }

	   try { /* �����ͺ��̽��� �����ϴ� ���� */
	     System.out.println ("�����ͺ��̽� ���� �غ� ...");
	     con=DriverManager.getConnection(url, userid, pwd);
	     System.out.println ("�����ͺ��̽� ���� ����");
	     } catch(SQLException e) {
	         e.printStackTrace();
		}
	   }

	   private void sqlRun() {
		   String query="SELECT bookname FROM book b1 WHERE  price >=(SELECT AVG(price) FROM book b2 WHERE b1.publisher=b2.publisher ) ";/* SQL �� */

		     try { /* �����ͺ��̽��� ���� ����� �������� ���� */
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
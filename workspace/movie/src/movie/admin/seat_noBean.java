package movie.admin;

import java.sql.*;
import java.util.*;

public class seat_noBean {Connection conn=null;
PreparedStatement pstmt = null;

String jdbc_driver="com.mysql.jdbc.Driver";
String jdbc_url="jdbc:mysql://localhost/film_booking ";

//데이터베이스 연결 메서드
void connect(){
	try{
		//jdbc 드라이버 로드
		Class.forName(jdbc_driver);
		
		//데이터베이스 연결정보를 이용해 Connection 인스턴스 확보
		conn = DriverManager.getConnection(jdbc_url,"root", "wjqthr12");

	}catch(Exception e){
		e.printStackTrace();
	}
}
void disconnect(){
	if(pstmt!=null){
		try{
			pstmt.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	if(conn!=null){
		try{
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}

//좌석 업데이트 (결제시)
public boolean updateDB(int seat_no,int gb_id){
	connect();
	String sql="update seat_no set "+seat_no+"seat_no"+"=? where id_film="+gb_id;
	try{
		pstmt = conn.prepareStatement(sql);
		//pstmt.setInt(1,_film.getId_film());
		pstmt.setInt(1,0);
		pstmt.executeUpdate();
	}catch(SQLException e){
		e.printStackTrace();
		return false;
	}
	finally{
		disconnect();
	}
	return true;
}

//좌석 추가
public boolean insertDB(seat_no _seat_no,int gb_id){
	connect();
	String sql="insert into seat_no(id_film,seat1,seat2,seat3,seat4,seat5,seat6,seat7,seat8,seat9) values(?,?,?,?,?,?,?,?,?,?)";
	try{
	//sql 문자열 ,gb_id는 자동등록되므로 입력하지 않는다.
	//pstmt.setInt(1, _film.getId_film());
	
	pstmt=conn.prepareStatement(sql);
	//pstmt.setInt(1,123);
	pstmt.setInt(1,gb_id);
	pstmt.setInt(2,1);
	pstmt.setInt(3,1);
	pstmt.setInt(4,1);
	pstmt.setInt(5,1);
	pstmt.setInt(6,1);
	pstmt.setInt(7,1);
	pstmt.setInt(8,1);
	pstmt.setInt(9,1);
	pstmt.setInt(10,1);
	
	pstmt.executeUpdate();
	
	//sql="insert into seat_no(id_film,seat1,seat2,seat3,seat4,seat5,seat6,seat7,seat8,seat9) values(?,?,?,?,?,?,?,?,?,?)";
	
	}
	catch(SQLException e){
		e.printStackTrace();
		return false;
	}
	finally{
		disconnect();
	}
	return true;
}
//좌석 확인
public boolean confirmDB(int gb_id){
	connect();
	String sql="select id_film from seat_no where id_film=?";
	try{
	//sql 문자열 ,gb_id는 자동등록되므로 입력하지 않는다.
	
	pstmt=conn.prepareStatement(sql);
	pstmt.setInt(1,gb_id);
	ResultSet rs=pstmt.executeQuery();
	rs=pstmt.executeQuery();
	
		if(rs.next()){
			//중복 되는 아이디 존재
			return false;
		}

	}
	catch(SQLException e){
		e.printStackTrace();
		return false;
	}
	finally{
		disconnect();
	}
	return true;
}

//좌석 삭제
//public boolean deleteDB(int gb_id){
//	connect();
//	String sql="delete from seat_no where id_film=?";
//	try{
//		pstmt = conn.prepareStatement(sql);
//		pstmt.setInt(1,gb_id);
//		pstmt.executeUpdate();
//	}catch(SQLException e){
//		e.printStackTrace();
//		return false;
//	}
//	finally{
//		disconnect();
//	}
//	return true;
//}

public seat_no getDB(int film_id){
	connect();
	
	String sql="select* from seat_no where id_film=? ";
	seat_no _seat_no=new seat_no();
	
	try{
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, film_id);
		ResultSet rs=pstmt.executeQuery();
		
		rs.next();
		
		_seat_no.setId_film(rs.getInt("id_film"));
		_seat_no.setSeat1(rs.getInt("seat1"));
		_seat_no.setSeat2(rs.getInt("seat2"));
		_seat_no.setSeat3(rs.getInt("seat3"));
		_seat_no.setSeat4(rs.getInt("seat4"));
		_seat_no.setSeat5(rs.getInt("seat5"));
		_seat_no.setSeat6(rs.getInt("seat6"));
		_seat_no.setSeat7(rs.getInt("seat7"));
		_seat_no.setSeat8(rs.getInt("seat8"));
		_seat_no.setSeat9(rs.getInt("seat9"));
		rs.close();
	}catch(SQLException e){
		e.printStackTrace();
	}
	finally{
		disconnect();
	}
	return _seat_no;
}

public ArrayList<seat_no> getDBlist(){
	connect();
	ArrayList<seat_no> datas=new ArrayList<seat_no>();
	String sql="select* from seat_no ";
	try{
		pstmt = conn.prepareStatement(sql);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()){
			seat_no _seat_no=new seat_no();
			_seat_no.setId_seat(rs.getInt("id_seat"));
			_seat_no.setId_film(rs.getInt("id_film"));
			_seat_no.setSeat1(rs.getInt("seat1"));
			_seat_no.setSeat2(rs.getInt("seat2"));
			_seat_no.setSeat3(rs.getInt("seat3"));
			_seat_no.setSeat4(rs.getInt("seat4"));
			_seat_no.setSeat5(rs.getInt("seat5"));
			_seat_no.setSeat6(rs.getInt("seat6"));
			_seat_no.setSeat7(rs.getInt("seat7"));
			_seat_no.setSeat8(rs.getInt("seat8"));
			_seat_no.setSeat9(rs.getInt("seat9"));
			datas.add(_seat_no);
		}
		rs.close();
	}catch(SQLException e){
		e.printStackTrace();
	}
	finally{
		disconnect();
	}
	return datas;
}
}


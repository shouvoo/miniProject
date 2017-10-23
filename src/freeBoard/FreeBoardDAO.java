package freeBoard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.ConnectionPool;

public class FreeBoardDAO 
{
	
	public List<FreeBoardDomain> getFreeBoardList()
	{
		List<FreeBoardDomain> list = new ArrayList<>();
		
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ConnectionPool.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("select * from t_freeboard oder by no desc");
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				FreeBoardDomain fbd = new FreeBoardDomain();
				fbd.setNo(rs.getInt("no"))
				   .setTitle(rs.getString("title"))
				   .setContetns(rs.getString("contents"))
				   .setWriter(rs.getString("writer"))
				   .setRegDate(rs.getDate("reg_date"));
				
				list.add(fbd);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
}

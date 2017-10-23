package qna;

import java.security.KeyStore.ProtectionParameter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import common.ConnectionPool;

public class QnaDAO {
	
	public void insertQna(QnaDomain qna) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			
			sql.append("insert into t_qboard (no, title, content, writer)")
				.append("values(s_qboard_no.nextval, ?, ?, ?)");
			
			pstmt=con.prepareStatement(sql.toString());
			int index=1;
			
			pstmt.setString(index++, qna.getTitle());
			pstmt.setString(index++, qna.getContent());
			pstmt.setString(index++, qna.getWriter());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}finally {
			pstmt.close();
			ConnectionPool.releaseConnection(con);
		}
		
	}
	
	public int listBoardCount() throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select count(*) from t_qboard ");

			pstmt = con.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {

			pstmt.close();
			ConnectionPool.releaseConnection(con);
		}
		return 0;
	}
	
	
	public List<QnaDomain> listQna(Page page) throws Exception{
		List<QnaDomain> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * ")
			.append("from (select rownum rnum , a.* ")
			.append("from (select * from t_qboard order by no desc) a) where rnum between ? and ?");
			
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, page.getBegin());
			pstmt.setInt(2, page.getEnd());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				QnaDomain qna = new QnaDomain();
				qna.setNo(rs.getInt("no"));
				qna.setTitle(rs.getString("title"));
				qna.setWriter(rs.getString("writer"));
				qna.setRegDate(rs.getTimestamp("reg_date"));
				qna.setaComment(rs.getString("a_comment"));
				list.add(qna);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}finally {
			pstmt.close();
			ConnectionPool.releaseConnection(con);
		}
		return list;
	}
	
	public QnaDomain detailQna(int no) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select * from t_qboard where no=?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			
			ResultSet rs =pstmt.executeQuery();
			
			if(rs.next()) {
				QnaDomain qna = new QnaDomain();
				qna.setNo(no);
				qna.setTitle(rs.getString("title"));
				qna.setWriter(rs.getString("writer"));
				qna.setContent(rs.getString("content"));
				qna.setRegDate(rs.getTimestamp("reg_date"));
				qna.setaComment(rs.getString("a_comment"));
				return qna;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}finally {
			pstmt.close();
			ConnectionPool.releaseConnection(con);
		}
		
		
		return null;
		
	}
	
	public void deleteQna(int no) throws Exception {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			con=ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("delete from t_qboard where no =?");
			pstmt= con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}finally {
			pstmt.close();
			ConnectionPool.releaseConnection(con);
		}
		
	}
	
	public void modifyQna(QnaDomain qna) throws Exception {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("update t_qboard set ")
			.append("title = ?, content = ? where no =? ");
			pstmt =con.prepareStatement(sql.toString());
			int index= 1;
			
			pstmt.setString(index++, qna.getTitle());
			pstmt.setString(index++, qna.getContent());
			pstmt.setInt(index, qna.getNo());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}finally {
			pstmt.close();
			ConnectionPool.releaseConnection(con);
		}
		
	}
	
	public void insertAnswerQna(QnaDomain qna) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("update t_qboard set a_comment = ? , a_reg_date = sysdate where no = ?");
			pstmt= con.prepareStatement(sql.toString());
			
			pstmt.setString(1, qna.getaComment());
			pstmt.setInt(2, qna.getNo());
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}finally {
			pstmt.close();
			ConnectionPool.releaseConnection(con);
		}
	}
	
	public QnaDomain detailAnswerQna(int no) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con= ConnectionPool.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select a_comment from t_qboard where no = ?");
			pstmt= con.prepareStatement(sql.toString());
			
			pstmt.setInt(1, no);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				QnaDomain qna = new QnaDomain();
				qna.setNo(no);
				qna.setaComment(rs.getString("a_comment"));
				return qna;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}finally {
			pstmt.close();
			ConnectionPool.releaseConnection(con);
		}
		return null;
		
	}
	
}

package fixBoard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import common.ConnectionPool;

public class FixDAO {
	PreparedStatement ptmt;
	Connection con = null;
	ResultSet rs;
	StringBuffer sb;
	
	//////////////글번호
	
	public int number() throws Exception {
		int a = 0;
		try {
			con = ConnectionPool.getConnection();
			sb = new StringBuffer();
			
			sb.append("select fixboard_NO.Nextval ")
			  .append("from dual");
			
			ptmt = con.prepareStatement(sb.toString());

			rs = ptmt.executeQuery();
			
			if(rs.next())
				a = rs.getInt(1);
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		} finally {
			try {
				//close();
				ptmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			ConnectionPool.releaseConnection(con);
		}
		return a;
	}
	
	///////////////////////////게시물
	
	public void insertFix(FixVO fix) throws Exception {
		try {			
			con = ConnectionPool.getConnection();
			sb = new StringBuffer();
			sb.append("insert into t_fixboard (");
			sb.append("NO, TITLE, CONTENT, WRITER, THUMB, IP, CATE, BRAND, ID)");
			sb.append("values (");
			sb.append("?,?,?,?,?,?,?,?,?) ");
			
			ptmt = con.prepareStatement(sb.toString());
			
			int index = 1;
			
			ptmt.setInt(index++, fix.getNo());
			ptmt.setString(index++, fix.getTitle());
			ptmt.setString(index++, fix.getContent());
			ptmt.setString(index++, fix.getWriter());
			ptmt.setString(index++, fix.getThumb());
			ptmt.setString(index++, fix.getIp());
			ptmt.setString(index++, fix.getCate());
			ptmt.setString(index++, fix.getBrand());
			ptmt.setString(index++, "asdasd");

			ptmt.executeUpdate();
			
		} catch (SQLException e) {
			throw e;
		} finally {
			ConnectionPool.releaseConnection(con);
		}
	}
	
	public void deleteFix(int no) throws Exception {
		try {
			con = ConnectionPool.getConnection();
			sb = new StringBuffer();
			sb.append("delete from t_fixboard where no = ?");
			
			ptmt = con.prepareStatement(sb.toString());
			
			ptmt.setInt(1, no);
			
			ptmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			try {
				ptmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			ConnectionPool.releaseConnection(con);
		}
	}
	
	public void lookUp(int no) throws Exception {
		try {
			con = ConnectionPool.getConnection();
			sb = new StringBuffer();
			sb.append("update t_fixboard set look = look+1 where no = ?");
			
			ptmt = con.prepareStatement(sb.toString());
			
			ptmt.setInt(1, no);
			
			ptmt.executeQuery();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		} finally {
			try {
				ptmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			ConnectionPool.releaseConnection(con);
		}
	}
	
	public FixVO detailFix(int no) throws Exception {
		FixVO fix = new FixVO();
		fix.setNo(no);
		try {
			con = ConnectionPool.getConnection();
			sb = new StringBuffer();
			sb.append("select * from t_fixboard where no = ?");
			
			ptmt = con.prepareStatement(sb.toString());
			
			ptmt.setInt(1, no);
			
			rs = ptmt.executeQuery();
			
			if(rs.next()) {
				fix.setContent(rs.getString("content"));
				fix.setRegDate(rs.getTimestamp("reg_date"));
				fix.setTitle(rs.getString("title"));
				fix.setWriter(rs.getString("writer"));
				fix.setIp(rs.getString("ip"));
				fix.setThumb(rs.getString("thumb"));
				fix.setBrand(rs.getString("brand"));
				fix.setCate(rs.getString("cate"));
				fix.setId(rs.getString("id"));
				fix.setLook(rs.getInt("look"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		} finally {
			try {
				ptmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			ConnectionPool.releaseConnection(con);
		}
		return fix;
	}
	
	public void modifyFix(FixVO vo) throws Exception {
		try {
			con = ConnectionPool.getConnection();
			sb = new StringBuffer();
			sb.append("update t_fixboard set ")
			  .append("title = ?, content = ?, cate = ?, brand = ?");
			  
			if(vo.getThumb() != null)
				sb.append(", thumb = ?");
			
			sb.append(" where no = ?");
			
			ptmt = con.prepareStatement(sb.toString());
			
			int index = 1;

			ptmt.setString(index++, vo.getTitle());
			ptmt.setString(index++, vo.getContent());
			ptmt.setString(index++, vo.getCate());
			ptmt.setString(index++, vo.getBrand());
			if(vo.getThumb() != null)
				ptmt.setString(index++, vo.getThumb());
			ptmt.setInt(index++, vo.getNo());
			
			ptmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		} finally {
			try {
				//close();
				ptmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			ConnectionPool.releaseConnection(con);
		}
	}
	
	public List<FixVO> listFix(String order, int page) throws Exception {
		List<FixVO> list = new ArrayList<FixVO>();
		try {
			con = ConnectionPool.getConnection();
			sb = new StringBuffer();
			sb.append("select * from (select rownum as nnn, aaa.* from (select * from t_fixboard order by ")
			  .append(order+") aaa) ");
			if(page != 0) {
				sb.append("where nnn between ")
				  .append(page+"*8-7 and "+page+"*8");
				
			//8 = 한 페이지에 가져올 갯수
			}
			
			ptmt = con.prepareStatement(sb.toString());

			rs = ptmt.executeQuery();

			while(rs.next()) {
				FixVO fix = new FixVO();
				
				fix.setRegDate(rs.getTimestamp("reg_date"));
				fix.setTitle(rs.getString("title"));
				fix.setWriter(rs.getString("writer"));
				fix.setThumb(rs.getString("thumb"));
				fix.setNo(rs.getInt("no"));
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				long fixTime = Long.parseLong(sdf.format(fix.getRegDate()));
				long nowTime = Long.parseLong(sdf.format(new Date()));
				
				if(nowTime - fixTime <= 10000)
					fix.setChk(true);
				else
					fix.setChk(false);

				list.add(fix);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		} finally {
			try {
				ptmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			ConnectionPool.releaseConnection(con);
		}
		return list;
	}
	
	public List<FixVO> listFix(String order, String title, String cate, String brand, int page) throws Exception {
		List<FixVO> list = new ArrayList<FixVO>();
		try {
			con = ConnectionPool.getConnection();
			sb = new StringBuffer();
			sb.append("select * from (select rownum as nnn, aaa.* from (select * from t_fixboard where ")
			  .append(order+" order by no desc) aaa) ");
			if(page != 0) {
				sb.append("where nnn between ")
				  .append(page+"*8-7 and "+page+"*8");
			}
			
			ptmt = con.prepareStatement(sb.toString());
			
			int index = 1;
			
			if(title.length() > 2) ptmt.setString(index++, title);
			if(cate.length() > 0 && !cate.equals("전체")) ptmt.setString(index++, cate);
			if(brand.length() > 0 && !brand.equals("전체")) ptmt.setString(index++, brand);

			rs = ptmt.executeQuery();

			while(rs.next()) {
				FixVO fix = new FixVO();
				
				fix.setRegDate(rs.getTimestamp("reg_date"));
				fix.setTitle(rs.getString("title"));
				fix.setWriter(rs.getString("writer"));
				fix.setThumb(rs.getString("thumb"));
				fix.setNo(rs.getInt("no"));
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				long fixTime = Long.parseLong(sdf.format(fix.getRegDate()));
				long nowTime = Long.parseLong(sdf.format(new Date()));
				
				if(nowTime - fixTime <= 6000)
					fix.setChk(true);
				else
					fix.setChk(false);
				
				list.add(fix);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		} finally {
			try {
				ptmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			ConnectionPool.releaseConnection(con);
		}
		return list;
	}
	
	///////////////////페이지
	
	public int Page(String order, String title, String cate, String brand) throws Exception {
		int a = 0;
		try {
			con = ConnectionPool.getConnection();
			sb = new StringBuffer();
			sb.append("select count(*) ")
			  .append("from t_fixboard where ")
			  .append(order);
			
			ptmt = con.prepareStatement(sb.toString());
			
			int index = 1; 
			
			if(title.length() > 2) ptmt.setString(index++, title);
			if(cate.length() > 0 && !cate.equals("전체")) ptmt.setString(index++, cate);
			if(brand.length() > 0 && !brand.equals("전체")) ptmt.setString(index++, brand);

			rs = ptmt.executeQuery();

			if(rs.next())
				a = rs.getInt(1);
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		} finally {
			try {
				ptmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			ConnectionPool.releaseConnection(con);
		}
		return a;
	}
	
	public int Page() throws Exception {
		int a = 0;
		try {
			con = ConnectionPool.getConnection();
			sb = new StringBuffer();
			sb.append("select count(*) ")
			  .append("from t_fixboard");
			
			ptmt = con.prepareStatement(sb.toString());

			rs = ptmt.executeQuery();

			if(rs.next())
				a = rs.getInt(1);
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		} finally {
			try {
				ptmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			ConnectionPool.releaseConnection(con);
		}
		return a;
	}
	
	///////////////////////덧글
	
	public void insertDut(FixVO fix) throws Exception {
		try {
			con = ConnectionPool.getConnection();
			sb = new StringBuffer();
			sb.append("insert into t_fixcommentboard (");
			sb.append("comment_no, no, writer, content, id)");
			sb.append("values (");
			sb.append("fixcommentboard_no.nextval,?,?,?,?)");
			
			ptmt = con.prepareStatement(sb.toString());
			
			int index = 1;
			
			ptmt.setInt(index++, fix.getNo());
			ptmt.setString(index++, fix.getWriter());
			ptmt.setString(index++, fix.getContent());
			ptmt.setString(index++, fix.getId());
			
			ptmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}finally {
			ConnectionPool.releaseConnection(con);
		}
	}
	
	public void deleteDut(int no) throws Exception {
		try {
			con = ConnectionPool.getConnection();
			sb = new StringBuffer();
			sb.append("delete from t_fixcommentboard where comment_no = ?");
			
			ptmt = con.prepareStatement(sb.toString());
			
			ptmt.setInt(1, no);
			
			ptmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			try {
				ptmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			ConnectionPool.releaseConnection(con);
		}
	}
	
	public void modifyDut(FixVO dut) throws Exception {
		try {
			con = ConnectionPool.getConnection();
			sb = new StringBuffer();
			sb.append("update t_fixcommentboard set content = ? where comment_no = ?");
			
			ptmt = con.prepareStatement(sb.toString());
			
			int index = 1;
			ptmt.setString(index++, dut.getContent());
			ptmt.setInt(index++, dut.getCommentNo());
			ptmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		} finally {
			try {
				//close();
				ptmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			ConnectionPool.releaseConnection(con);
		}
	}
	
	public List<FixVO> detailDut(int no) throws Exception {
		List<FixVO> list = new ArrayList<>();
		try {
			con = ConnectionPool.getConnection();
			sb = new StringBuffer();
			sb.append("select * ");
			sb.append("from t_fixcommentboard ");
			sb.append("where no = ? order by comment_no desc");
			
			ptmt = con.prepareStatement(sb.toString());
			ptmt.setInt(1, no);
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				FixVO dut = new FixVO();
				
				dut.setId(rs.getString("id"));
				dut.setWriter(rs.getString("writer"));
				dut.setCommentNo(rs.getInt("comment_no"));
				dut.setContent(rs.getString("content"));
				dut.setRegDate(rs.getTimestamp("reg_date"));
				
				list.add(dut);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		} finally {
			try {
				//close();
				ptmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			ConnectionPool.releaseConnection(con);
		}
		return list;
	}
	
	///////////////////////파일
	
	public void insertFile(FixVO fix) throws Exception {
		try {
			con = ConnectionPool.getConnection();
			sb = new StringBuffer();
			sb.append("insert into t_fiximageboard (");
			sb.append("image_no, no, ori_name, sys_name, width) ");
			sb.append("values (");
			sb.append("fiximageboard_no.nextval,?,?,?,?)");
			
			ptmt = con.prepareStatement(sb.toString());
			
			int index = 1;
			ptmt.setInt(index++, fix.getNo());
			ptmt.setString(index++, fix.getOriName());
			ptmt.setString(index++, fix.getSysName());
			ptmt.setInt(index++, fix.getWidth());
			
			ptmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}finally {
			ConnectionPool.releaseConnection(con);
		}
	}
	
	public void modifyFile(FixVO fix) throws Exception {
		try {
			con = ConnectionPool.getConnection();
			sb = new StringBuffer();
			sb.append("update t_fiximageboard set ")
			  .append("ori_name = ?, sys_name = ?, width = ? ")
			  .append("where image_no = ?");
			
			ptmt = con.prepareStatement(sb.toString());
			
			ptmt.setString(1, fix.getOriName());
			ptmt.setString(2, fix.getSysName());
			ptmt.setInt(3, fix.getWidth());
			ptmt.setInt(4, fix.getImageNo());
			
			ptmt.executeUpdate();
		} catch (SQLException e) {
			throw e;
		}finally {
			ConnectionPool.releaseConnection(con);
		}
	}
	
	public List<FixVO> detailFile(int no) throws Exception {
		List<FixVO> list = new ArrayList<>();
		
		try {
			con = ConnectionPool.getConnection();
			sb = new StringBuffer();
			sb.append("select * from t_fiximageboard ")
			  .append("where no = ? order by image_no desc");
			
			ptmt = con.prepareStatement(sb.toString());
			
			ptmt.setInt(1, no);
			
			rs = ptmt.executeQuery();
			
			while(rs.next()) {
				FixVO vo = new FixVO();
				
				vo.setImageNo(rs.getInt("image_no"));
				vo.setSysName(rs.getString("sys_name"));
				vo.setOriName(rs.getString("ori_name"));
				vo.setWidth(rs.getInt("width"));
				
				list.add(vo);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		} finally {
			try {
				//close();
				ptmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			ConnectionPool.releaseConnection(con);
		}
		return list;
	}
	
	////////////////////좋아요
	
	public void insertLike(FixVO fix) throws Exception {
		try {			
			con = ConnectionPool.getConnection();
			sb = new StringBuffer();
			sb.append("insert into t_fixlikeboard (");
			sb.append("NO, IP)");
			sb.append("values (");
			sb.append("?,?)");
			
			ptmt = con.prepareStatement(sb.toString());
			
			int index = 1;
			
			ptmt.setInt(index++, fix.getNo());
			ptmt.setString(index++, fix.getIp());

			ptmt.executeUpdate();
			
		} catch (SQLException e) {
			throw e;
		} finally {
			ConnectionPool.releaseConnection(con);
		}
	}
	
	public void deleteLike(FixVO fix) throws Exception {
		try {
			con = ConnectionPool.getConnection();
			sb = new StringBuffer();
			sb.append("delete from t_fixlikeboard where no = ? and ip = ?");
			
			ptmt = con.prepareStatement(sb.toString());
			
			ptmt.setInt(1, fix.getNo());
			ptmt.setString(2, fix.getIp());
			
			ptmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			try {
				ptmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			ConnectionPool.releaseConnection(con);
		}
	}
	
	public int detailLike(int no) throws Exception {
		int a = 0;
		try {
			con = ConnectionPool.getConnection();
			sb = new StringBuffer();
			sb.append("select count(*) ");
			sb.append("from t_fixlikeboard ");
			sb.append("where no = ?");
			
			ptmt = con.prepareStatement(sb.toString());
			
			ptmt.setInt(1, no);
			
			rs = ptmt.executeQuery();
			
			if(rs.next())
				a = rs.getInt(1);

		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		} finally {
			try {
				ptmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			ConnectionPool.releaseConnection(con);
		}
		return a;
	}
	
	public boolean LikeJungBok(FixVO fix) throws Exception {
		boolean chk = false;
		try {
			con = ConnectionPool.getConnection();
			sb = new StringBuffer();
			sb.append("select no from t_fixlikeboard ")
			  .append("where no = ? and ip = ?");
			
			ptmt = con.prepareStatement(sb.toString());

			ptmt.setInt(1, fix.getNo());
			ptmt.setString(2, fix.getIp());
			
			rs = ptmt.executeQuery();
			
			if(rs.next())
				chk = true;
			else if(!rs.next())
				chk = false;
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		} finally {
			try {
				//close();
				ptmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
			ConnectionPool.releaseConnection(con);
		}
		return chk;
	}
	
}

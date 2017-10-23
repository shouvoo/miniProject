package freeBoard;

import java.util.Date;

public class FreeBoardDomain 
{
	int no;
	String title;
	String contetns;
	String writer;
	Date regDate;
	
	public int getNo() 
	{
		return no;
	}
	public FreeBoardDomain setNo(int no) 
	{
		this.no = no;
		return this;
	}
	public String getTitle() 
	{
		return title;
	}
	public FreeBoardDomain setTitle(String title)
	{
		this.title = title;
		return this;
	}
	public String getContetns() 
	{
		return contetns;
	}
	public FreeBoardDomain setContetns(String contetns) 
	{
		this.contetns = contetns;
		return this;
	}
	public String getWriter()
	{
		return writer;
	}
	public FreeBoardDomain setWriter(String writer) 
	{
		this.writer = writer;
		return this;
	}
	public Date getRegDate()
	{
		return regDate;
	}
	public FreeBoardDomain setRegDate(Date regDate) 
	{
		this.regDate = regDate;
		return this;
	}
	
	
}

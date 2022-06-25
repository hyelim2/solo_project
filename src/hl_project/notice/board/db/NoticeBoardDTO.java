package hl_project.notice.board.db;

import java.sql.Date;

public class NoticeBoardDTO {
	private int num;
	private String name;
	private String pw;
	private String title;
	private String content;
	private int readcount;
	private Date date;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "NoticeBoardDTO [num=" + num + ", name=" + name + ", pw=" + pw + ", title=" + title + ", content="
				+ content + ", readcount=" + readcount + ", date=" + date + "]";
	}

}

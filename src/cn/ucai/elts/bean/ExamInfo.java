package cn.ucai.elts.bean;

import java.io.Serializable;

/*
 *  "course":"android",
 "total":20,
 "time":10,
 "question":"android.json"
 * 
 */
public class ExamInfo implements Serializable {

	private String course;
	private int total;
	private int time;
	private String question;
	private User user;

	public ExamInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public ExamInfo(String course, int total, int time, String question) {
		super();
		this.course = course;
		this.total = total;
		this.time = time;
		this.question = question;
	}

	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		int userId = 1001;
		if(user!=null){
			userId = user.getId();
		}
		return "考试科目："+this.course+"\t 考生编号："
		+userId+"\n考题数量："+this.total+"\t 考试时间："+this.time+"分钟";
	}
}

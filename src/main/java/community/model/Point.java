package community.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import register.model.MemberBean;

@Entity
@Table(name = "Point_Table")
public class Point implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private Discussion discussion;
	@ManyToOne
	private Reply reply;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private MemberBean member;
	private Integer pointVal;
	private Timestamp pointTime;

	public Point() {
		super();
	}

	public Point(Integer id, Discussion discussion, Reply reply, MemberBean member, Integer pointVal,
			Timestamp pointTime) {
		super();
		this.id = id;
		this.discussion = discussion;
		this.reply = reply;
		this.member = member;
		this.pointVal = pointVal;
		this.pointTime = pointTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Discussion getDiscussion() {
		return discussion;
	}

	public void setDiscussion(Discussion discussion) {
		this.discussion = discussion;
	}

	public Reply getReply() {
		return reply;
	}

	public void setReply(Reply reply) {
		this.reply = reply;
	}

	public MemberBean getMember() {
		return member;
	}

	public void setMember(MemberBean member) {
		this.member = member;
	}

	public Integer getPointVal() {
		return pointVal;
	}

	public void setPointVal(Integer pointVal) {
		this.pointVal = pointVal;
	}

	public Timestamp getPointTime() {
		return pointTime;
	}

	public void setPointTime(Timestamp pointTime) {
		this.pointTime = pointTime;
	}

}

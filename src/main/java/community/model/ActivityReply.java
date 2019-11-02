package community.model;

import java.io.Serializable;
import java.sql.Clob;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import register.model.MemberBean;

@Entity
@Table(name = "GroupingReply_Table")
public class ActivityReply implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "Activity_id", nullable = false)
	private Activity activity;
	private Clob content;
	private Timestamp postTime;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private MemberBean author;
//	@OneToMany(mappedBy = "activityReply")
//	private List<Point> points;

	public ActivityReply() {
		super();
	}

	public ActivityReply(Integer id, Activity activity, Clob content, Timestamp postTime, MemberBean author) {
		super();
		this.id = id;
		this.activity = activity;
		this.content = content;
		this.postTime = postTime;
		this.author = author;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Clob getContent() {
		return content;
	}

	public void setContent(Clob content) {
		this.content = content;
	}

	public Timestamp getPostTime() {
		return postTime;
	}

	public void setPostTime(Timestamp postTime) {
		this.postTime = postTime;
	}

	public MemberBean getAuthor() {
		return author;
	}

	public void setAuthor(MemberBean author) {
		this.author = author;
	}

//	public List<Point> getPoints() {
//		return points;
//	}
//
//	public void setPoints(List<Point> points) {
//		this.points = points;
//	}

}

package community.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import register.model.MemberBean;

@Entity
@Table(name = "Activity_Table")
public class Activity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private Clob content;
	private Timestamp postTime;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private MemberBean author;
//  ---------------------------------------
	private Blob coverImage; // 揪團、拍賣
	private Integer price; // 揪團、拍賣
	private String location;// 揪團、拍賣
	private String coverImageName;
	private Timestamp deadline; // 揪團


	//	---------------------------------------
	@OneToMany(mappedBy = "activity")
	private List<ActivityReply> replies;
//	@OneToMany(mappedBy = "discussion")
//	private List<Point> points;

	public Activity() {
		super();
	}

	public Activity(Integer id, String title, Clob content, Timestamp postTime, MemberBean author, Blob coverImage,
			String coverImageName, Timestamp deadline, List<ActivityReply> replies,Integer price,String location ) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.postTime = postTime;
		this.author = author;
		this.coverImage = coverImage;
		this.coverImageName = coverImageName;
		this.deadline = deadline;
		this.replies = replies;
		this.price = price;
		this.location = location;
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Blob getCoverImage() {
		return coverImage;
	}

	public void setCoverImage(Blob coverImage) {
		this.coverImage = coverImage;
	}

	public String getCoverImageName() {
		return coverImageName;
	}

	public void setCoverImageName(String coverImageName) {
		this.coverImageName = coverImageName;
	}

	public Timestamp getDeadline() {
		return deadline;
	}

	public void setDeadline(Timestamp deadline) {
		this.deadline = deadline;
	}

	public List<ActivityReply> getReplies() {
		return replies;
	}

	public void setReplies(List<ActivityReply> replies) {
		this.replies = replies;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}

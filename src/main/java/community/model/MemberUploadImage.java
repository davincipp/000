package community.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;

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
@Table(name = "MemberUploadImage_Table")
public class MemberUploadImage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Timestamp uploadTime;
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "Author_id", nullable = false)
	private MemberBean author;
	private Blob image;
	private String imageName;

	public MemberUploadImage() {
		super();
	}

	public MemberUploadImage(Integer id, Timestamp uploadTime, MemberBean author, Blob image, String imageName) {
		super();
		this.id = id;
		this.uploadTime = uploadTime;
		this.author = author;
		this.image = image;
		this.imageName = imageName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Timestamp uploadTime) {
		this.uploadTime = uploadTime;
	}

	public MemberBean getAuthor() {
		return author;
	}

	public void setAuthor(MemberBean author) {
		this.author = author;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

}

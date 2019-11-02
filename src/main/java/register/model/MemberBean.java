package register.model;

import java.io.Serializable;
import java.sql.Blob;
//import java.sql.Clob;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Member")
public class MemberBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="seqNo")
	Integer pkey;
	String memberId;
	String name;
	String password;
	String address;
	String email;
	String tel;
	String gender;
	Timestamp registerTime;
	Blob memberImage;
	String fileName;

	public MemberBean(Integer pkey, String memberId, String name, String password, String address, String email,
			String tel, String gender, Timestamp registerTime, Blob memberImage, String fileName) {
		super();
		this.pkey = pkey;
		this.memberId = memberId;
		this.name = name;
		this.password = password;
		this.address = address;
		this.email = email;
		this.tel = tel;
		this.gender = gender;
		this.registerTime = registerTime;
		this.memberImage = memberImage;
		this.fileName = fileName;
	}
	

	public MemberBean() {
	}


	public Integer getPkey() {
		return pkey;
	}


	public void setPkey(Integer pkey) {
		this.pkey = pkey;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public Timestamp getRegisterTime() {
		return registerTime;
	}


	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}


	public Blob getMemberImage() {
		return memberImage;
	}


	public void setMemberImage(Blob memberImage) {
		this.memberImage = memberImage;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
}

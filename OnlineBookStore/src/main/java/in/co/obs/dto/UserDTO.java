package in.co.obs.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "b_user")
@Setter
@Getter
public class UserDTO extends BaseDTO {

	@Column(name = "FIRST_NAME", length = 255)
	private String firstName;
	@Column(name = "LAST_NAME", length = 255)
	private String lastName;
	@Column(name = "LOGIN", length = 255)
	private String login;
	@Column(name = "PASSWORD", length = 255)
	private String password;
	@Column(name = "MOBILE_NO", length = 255)
	private String mobileNo;
	@Column(name = "ROLE_ID", length = 255)
	private String roleId;
	@Column(name = "ROLE_NAME", length = 255)
	private String roleName;
	@Transient
	private String cardNumber;
	@Transient
	private String expiration;
	@Transient
	private Long cvv;
	
	@OneToMany(
			mappedBy = "user",
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.EAGER
			)
	private List<BookedDTO> books = new ArrayList<>();

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}

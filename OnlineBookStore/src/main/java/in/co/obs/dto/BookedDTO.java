package in.co.obs.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "b_booked")
@Setter
@Getter
public class BookedDTO extends BaseDTO {
	@ManyToOne
	@JoinColumn(name = "userid")
	private UserDTO user;
	@Column(name = "user_id")
	private Long userId;
	@ManyToOne
	@JoinColumn(name = "bookid")
	private BookDTO book;
	@Column(name = "book_id")
	private Long bookId;
	@Column(name = "username")
	private String userName;
	

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

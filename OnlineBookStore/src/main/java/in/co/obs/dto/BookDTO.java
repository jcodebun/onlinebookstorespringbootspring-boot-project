package in.co.obs.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "b_book")
@Setter
@Getter
public class BookDTO extends BaseDTO {
	
	@Column(name = "BOOK_NAME", length = 255)
	private String bookName;
	@Column(name = "SALE_TYPE", length = 255)
	private String saleType;
	@Column(name = "PRICE")
	private BigDecimal price;
	@Column(name = "BOOK_IMAGE")
	private byte[] bookImage;
	@Column(name = "BOOK_PDF")
	private byte[] bookPdf;
	@Column(name = "BOOK_DESCRIPTION")
	private String bookDescription;
	@OneToMany(
			mappedBy = "book",
			cascade = CascadeType.ALL,
			orphanRemoval = true
			//fetch = FetchType.EAGER
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

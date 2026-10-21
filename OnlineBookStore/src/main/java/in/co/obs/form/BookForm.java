package in.co.obs.form;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.MultipartFile;

import in.co.obs.dto.BaseDTO;
import in.co.obs.dto.BookDTO;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class BookForm extends BaseForm{

	@NotEmpty(message = "Book Name is required")
	private String bookName;
	@NotEmpty(message = "Type is required")
	private String saleType;
	//@NotNull(message = "It can't be empty")
	@Digits(fraction = 3, integer = 10, message = "Price should be Valid")
	private BigDecimal price;
	
	private MultipartFile bookImage;
	
	private MultipartFile bookPdf;
	@NotEmpty(message = "Book description is required")
	private String bookDescription;
	@Override
	public BaseDTO getDTO() {
		// TODO Auto-generated method stub
		BookDTO bean = new BookDTO();
		bean.setId(id);
		bean.setBookName(bookName);
		bean.setSaleType(saleType);
		bean.setPrice(price);
		//bean.setBookImage(bookImage);
		//bean.setBookPdf(bookPdf);
		bean.setBookDescription(bookDescription);
		bean.setCreatedBy(createdBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedBy(modifiedBy);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		// TODO Auto-generated method stub
		BookDTO bean = (BookDTO)bDto;
		id = bean.getId();
		bookName = bean.getBookName();
		saleType = bean.getSaleType();
		price = bean.getPrice();
		//bookImage = bean.getBookImage();
		//bookPdf = bean.getBookPdf();
		bookDescription = bean.getBookDescription();
		createdBy = bean.getCreatedBy();
		createdDateTime = bean.getCreatedDatetime();
		modifiedBy = bean.getModifiedBy();
		modifiedDateTime = bean.getModifiedDatetime();
	}

}

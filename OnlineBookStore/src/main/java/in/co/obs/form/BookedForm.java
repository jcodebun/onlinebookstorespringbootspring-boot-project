package in.co.obs.form;

import in.co.obs.dto.BaseDTO;
import in.co.obs.dto.BookDTO;
import in.co.obs.dto.BookedDTO;
import in.co.obs.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class BookedForm extends BaseForm{

	private String userName;
	
	private Long bookId;
	
	private Long userId;
	
	private UserDTO userDTO;
	
	private BookDTO bookDTO;
	@Override
	public BaseDTO getDTO() {
		BookedDTO bean = new BookedDTO();
		bean.setId(id);
		bean.setBook(bookDTO);
		bean.setUser(userDTO);
		bean.setUserName(userName);
		bean.setUserId(userId);
		bean.setBookId(bookId);
		bean.setCreatedBy(createdBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedBy(modifiedBy);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		// TODO Auto-generated method stub
		BookedDTO bean = (BookedDTO)bDto;
		id = bean.getId();
		userDTO = bean.getUser();
		bookDTO = bean.getBook();
		userId = bean.getUserId();
		bookId = bean.getBookId();
		userName = bean.getUserName();
		createdBy = bean.getCreatedBy();
		createdDateTime = bean.getCreatedDatetime();
		modifiedBy = bean.getModifiedBy();
		modifiedDateTime = bean.getModifiedDatetime();
	}

}

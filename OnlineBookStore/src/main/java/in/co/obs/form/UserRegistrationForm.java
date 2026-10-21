package in.co.obs.form;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import in.co.obs.dto.BaseDTO;
import in.co.obs.dto.UserDTO;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class UserRegistrationForm extends BaseForm {

	@NotEmpty(message = "First Name is required")
	@Pattern(regexp = "(^[A-Za-z ]*)*$",message = "First Name is Invalid")
	private String firstName;
	@NotEmpty(message = "Last Name is required")
	@Pattern(regexp = "(^[A-Za-z ]*)*$",message = "Last Name is Invalid")
	private String lastName;
	@NotEmpty(message = "Login is required")
	@Pattern(regexp="(^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,}))*$",message = "Email id is invalid")
	private String login;
	@NotEmpty(message = "Password is required")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{6,20}$", message = "Incorrect Format")
	private String password;
	@NotEmpty(message ="Mobile no. is required")
	@Pattern(regexp="(^[7-9][0-9]{9})*$",message = "Mobile No is Invalid")
	private String mobileNo;
	
	private String roleId;
	
	private String roleName;
	@NotEmpty(message = "Card Number is required")
	private String cardNumber;
	@NotEmpty(message = "Epiration detail is required")
	private String expiration;	
	@NotNull(message = "CVV detail is required")
	private Long cvv;
	
	
	@Override
	public BaseDTO getDTO() {
		// TODO Auto-generated method stub
		UserDTO bean = new UserDTO();
		bean.setId(id);
		bean.setFirstName(firstName);
		bean.setLastName(lastName);
		bean.setLogin(login);
		bean.setPassword(password);
		bean.setMobileNo(mobileNo);
		bean.setRoleId(roleId);
		bean.setRoleName(roleName);
		bean.setCreatedBy(createdBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedBy(modifiedBy);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		// TODO Auto-generated method stub
		UserDTO bean = (UserDTO)bDto;
		id = bean.getId();
		firstName = bean.getFirstName();
		lastName = bean.getLastName();
		login = bean.getLogin();
		password = bean.getPassword();
		mobileNo = bean.getMobileNo();
		roleId = bean.getRoleId();
		roleName = bean.getRoleName();
		createdBy = bean.getCreatedBy();
		createdDateTime = bean.getCreatedDatetime();
		modifiedBy = bean.getModifiedBy();
		modifiedDateTime = bean.getModifiedDatetime();
	}

}

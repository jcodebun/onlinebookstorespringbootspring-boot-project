package in.co.obs.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import in.co.obs.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PayForm extends BaseForm {
	
	@NotEmpty(message = "Card Number is required")
	private String cardNumber;
	@NotEmpty(message = "Epiration detail is required")
	private String expiration;	
	@NotNull(message = "CVV detail is required")
	private Long cvv;

	@Override
	public BaseDTO getDTO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void populate(BaseDTO bDto) {
		// TODO Auto-generated method stub
		
	}

}

package in.co.obs.service;


import in.co.obs.dto.UserDTO;
import in.co.obs.exception.DuplicateRecordException;

public interface UserServiceInt {

	UserDTO authentication(UserDTO dto);
	
	UserDTO findByPk(Long id);
	
	Long addUser(UserDTO dto) throws DuplicateRecordException;
	
	Long nextPk();
	
	UserDTO findByLogin(String login);

}

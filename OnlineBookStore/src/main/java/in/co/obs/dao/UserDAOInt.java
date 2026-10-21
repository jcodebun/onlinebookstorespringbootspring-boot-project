package in.co.obs.dao;


import in.co.obs.dto.UserDTO;

public interface UserDAOInt {

 UserDTO authentication(UserDTO dto);

 UserDTO findBypk(Long id);
 
 Long addUser(UserDTO dto);
 
 Long nextPk();

UserDTO findByLogin(String login);
	
}

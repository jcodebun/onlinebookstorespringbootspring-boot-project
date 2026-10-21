package in.co.obs.service;

import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.co.obs.dao.UserDAOInt;
import in.co.obs.dto.BookDTO;
import in.co.obs.dto.UserDTO;
import in.co.obs.exception.DuplicateRecordException;

@Service
public class UserServiceImpl implements UserServiceInt {

	private static Logger log = Logger.getLogger(UserServiceImpl.class.getName());

	@Autowired
	private UserDAOInt dao;
	@Override
	public UserDTO authentication(UserDTO dto) {
		// TODO Auto-generated method stub
		log.info("UserServiceImpl authentication started");
		dto = dao.authentication(dto);
		log.info("UserServiceImpl authentication ended");
		return dto;
	}
	@Override
	public UserDTO findByPk(Long id) {
		log.info("UserServiceImpl findBypk method start");
		UserDTO dto=dao.findBypk(id);
		log.info("UserServiceImpl findBypk method end");
		return dto;
	}
	@Override
	public Long addUser(UserDTO dto) throws DuplicateRecordException {
		log.info("UserServiceImpl Add method start");
		UserDTO existdto = dao.findByLogin(dto.getLogin());
		if (existdto != null)
			throw new DuplicateRecordException("Login id Already Exist");
		Long pk=dao.addUser(dto);
		log.info("UserServiceImpl Add method end");
		return pk;
	}
	@Override
	@Transactional
	public Long nextPk() {
		// TODO Auto-generated method stub
		return dao.nextPk();
	}
	@Override
	public UserDTO findByLogin(String login) {
		UserDTO dto=dao.findByLogin(login);
		return dto;
	}

}

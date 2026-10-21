package in.co.obs.dao;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.obs.dto.BookDTO;
import in.co.obs.dto.UserDTO;

@Repository
public class UserDAOImpl implements UserDAOInt{

	private static Logger log = Logger.getLogger(UserDAOImpl.class.getName());
	@Autowired
	private EntityManager entityManager;
	@Override
	public UserDTO authentication(UserDTO dto) {
		// TODO start authentication
		log.info("UserDAOImpl Authentication method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<UserDTO> query = session
				.createQuery("from UserDTO "
						+ "where login=:login "
						+ "and password=:password"
						, UserDTO.class);
		query.setParameter("login",dto.getLogin());
		query.setParameter("password",dto.getPassword());
		dto = null;
		try {
			dto = query.getSingleResult();
		} catch (NoResultException nre) {
			
		}
		log.info("UserDAOImpl Authentication method End");
		return dto;
		
	}
	@Override
	public UserDTO findBypk(Long id) {
		log.info("UserDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		UserDTO dto = (UserDTO) session.get(UserDTO.class, id);
		log.info("UserDAOImpl FindByPk method End");
		return dto;
	}
	@Override
	public Long addUser(UserDTO dto) {
		log.info("UserDAOImpl addBook method started");
		Session session = entityManager.unwrap(Session.class);
		Long pk = (Long) session.save(dto); 
		log.info("UserDAOImpl addBook method ended");
		return pk;
	}
	@Override
	public Long nextPk() {
		UserDTO bean = null;
		Session session = entityManager.unwrap(Session.class);
		//Session session = entityManager.unwrap(Session.class);
		Query<UserDTO> query = session.createQuery("from UserDTO", UserDTO.class);
		List<UserDTO> list = query.getResultList();
		Iterator itr = list.iterator();
		while(itr.hasNext()) {
			bean = (UserDTO)itr.next();
		}
		Long pk = bean.getId();
		System.out.println("pk is "+pk);
		return pk+1;
	}
	
	@Override
	public UserDTO findByLogin(String login) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(UserDTO.class);
		criteria.add(Restrictions.eq("login", login));
		UserDTO dto = (UserDTO) criteria.uniqueResult();
		return dto;
	}

}

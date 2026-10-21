package in.co.obs.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.obs.dto.BookDTO;
import in.co.obs.dto.BookedDTO;
import in.co.obs.dto.UserDTO;



@Repository
public class BookedBookDAOImpl implements BookedBookDAOInt {

	@Autowired
	private EntityManager entityManager;
	@Override
	public List<BookedDTO> findBookedBooksById(Long id) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		Query query = session.createSQLQuery("select b_booked.id,b_book.book_image,b_book.price,b_book.book_name from b_booked inner join b_user on b_booked.user_id=b_user.id inner join b_book on b_booked.book_id=b_book.id where b_booked.userid ="+id+"");
		List<BookedDTO> list =  query.getResultList();
		System.out.println("size "+list.size());
		return list;
	}
	
	@Override
	public UserDTO findBookedBooksById2(Long id) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		//Query query = session.createSQLQuery("select b_booked.id,b_book.book_image,b_book.price,b_book.book_name from b_booked inner join b_user on b_booked.user_id=b_user.id inner join b_book on b_booked.book_id=b_book.id where b_booked.userid ="+id+"");
		UserDTO dto = (UserDTO) session.load(UserDTO.class, id);
		System.out.println("dto "+dto.getBooks().size());
		List l = null;
		//System.out.println("list size "+list.size());
		return dto;
	}
	
	@Override
	public List<BookedDTO> search(BookedDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		//log.info("BookedDTODAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(BookedDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult((int) pageNo);
				criteria.setMaxResults(pageSize);
			}
		}
		//log.info("BookedDAOImpl Search method End");
		return criteria.list();
	}
	
	@Override
	public List<BookedDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		//log.info("DonateMedicineDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<BookedDTO> query = session.createQuery("from BookedDTO", BookedDTO.class);
		List<BookedDTO> list = query.getResultList();
		//log.info("DonateMedicineDAOImpl List method End");
		return list;
	}

	@Override
	public List<BookedDTO> list() {
		// TODO Auto-generated method stub
		return list(0,0);
	}
	
	@Override
	public List<BookedDTO> search(BookedDTO dto) {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	@Override
	public Long add(BookedDTO bookedDTO) {		
		Session session = entityManager.unwrap(Session.class);
		Long pk = (Long) session.save(bookedDTO); 		
		return pk;
	}

	@Override
	public BookedDTO findByPk(Long id) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		BookedDTO dto = (BookedDTO) session.get(BookedDTO.class, id);
		return dto;
	}

	

}

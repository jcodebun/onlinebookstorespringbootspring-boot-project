package in.co.obs.dao;

import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import in.co.obs.dto.BookDTO;


@Repository
public class BookDAOImpl implements BookDAOInt {
	
	private static Logger log = Logger.getLogger(BookDAOImpl.class.getName());


	@Autowired
	private EntityManager entityManager;
	@Override
	public Long addBook(BookDTO dto) {
		log.info("BookDAOImpl addBook method started");
		Session session = entityManager.unwrap(Session.class);
		Long pk = (Long) session.save(dto); 
		log.info("BookDAOImpl addBook method ended");
		return pk;
	}

	@Override
	public BookDTO findByBookName(String bookName) {
		log.info("BookDAOImpl findByBookName method started");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(BookDTO.class);
		criteria.add(Restrictions.eq("bookName", bookName));
		BookDTO dto = (BookDTO) criteria.uniqueResult();
		log.info("BookDAOImpl findByBookName method ended");
		return dto;
	}

	@Override
	public List<BookDTO> list() {
		return list(0, 0);
	}

	@Override
	public List<BookDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("BookDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<BookDTO> query = session.createQuery("from BookDTO", BookDTO.class);
		List<BookDTO> list = query.getResultList();
		System.out.println("lis "+list.size());
		log.info("BookDAOImpl List method End");
		return list;
	}

	@Override
	public List<BookDTO> search(BookDTO dto) {
		
		return search(dto, 0, 0);
	}

	@Override
	public List<BookDTO> search(BookDTO dto, int pageNo, int pageSize) {
		log.info("BookDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(BookDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getSaleType() != null && dto.getSaleType().length() > 0) {
				criteria.add(Restrictions.like("saleType", dto.getSaleType() + "%"));
			}
			if (dto.getBookName() != null && dto.getBookName().length() > 0) {
				criteria.add(Restrictions.like("bookName", dto.getBookName() + "%"));
			}
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult((int) pageNo);
				criteria.setMaxResults(pageSize);
			}
		}
		log.info("BookDAOImpl Search method End");
		
		return criteria.list();
	}

	@Override
	public void delete(BookDTO dto) {
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
	}

	@Override
	public BookDTO findBypk(long pk) {
		log.info("BookDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		BookDTO dto = (BookDTO) session.get(BookDTO.class, pk);
		log.info("BookDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public void update(BookDTO dto) {
		log.info("BookDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("BookDAOImpl update method End");
		
	}

}

package in.co.obs.service;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.co.obs.dao.BookDAOInt;
import in.co.obs.dto.BookDTO;
import in.co.obs.exception.DuplicateRecordException;



@Service
public class BookServiceImpl implements BookServiceInt {
	
	private static Logger log=Logger.getLogger(BookServiceImpl.class.getName());

	@Autowired
	private BookDAOInt dao;

	@Override
	public Long addBook(BookDTO dto) throws DuplicateRecordException {
		// TODO Auto-generated method stub
		log.info("BookServiceImpl Add method start");
		BookDTO existdto=dao.findByBookName(dto.getBookName());
		if(existdto !=null)
			throw new DuplicateRecordException("Book Already Exist");
		Long pk=dao.addBook(dto);
		log.info("BookServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public BookDTO findByBookName(String bookName) {
		// TODO Auto-generated method stub
		log.info("BookServiceImpl findByBookName method start");
		BookDTO dto=dao.findByBookName(bookName);
		log.info("BookServiceImpl findByBookName method end");
		return dto;
	}

	@Override
	public List<BookDTO> list() {
		// TODO Auto-generated method stub
		log.info("BookServiceImpl list method start");
		List<BookDTO> list=dao.list();
		log.info("BookMedicineServiceImpl list method end");
		return list;
	}

	@Override
	public List<BookDTO> list(int pageNo, int pageSize) {
		log.info("BookServiceImpl list method start");
		List<BookDTO> list=dao.list(pageNo, pageSize);
		log.info("BookServiceImpl list method end");
		return list;
	}

	@Override
	public List<BookDTO> search(BookDTO dto) {
		log.info("BookServiceImpl search method start");
		List<BookDTO> list=dao.search(dto);
		log.info("BookServiceImpl search method end");
		return list;
	}

	@Override
	public List<BookDTO> search(BookDTO dto, int pageNo, int pageSize) {
		log.info("BookServiceImpl search method start");
		List<BookDTO> list=dao.search(dto, pageNo, pageSize);
		log.info("BookServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public void delete(BookDTO dto) {
		// TODO Auto-generated method stub
		log.info("BookServiceImpl delete method start");
		dao.delete(dto);
		log.info("BookServiceImpl delete method end");
	}

	@Override
	public BookDTO findByPk(Long id) {
		log.info("BookServiceImpl findBypk method start");
		BookDTO dto=dao.findBypk(id);
		log.info("BookServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(BookDTO dto) {
		log.info("BookServiceImpl update method start");
		dao.update(dto);
		log.info("BookServiceImpl update method end");		
	}

}

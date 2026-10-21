package in.co.obs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.co.obs.dao.BookedBookDAOInt;
import in.co.obs.dto.BookDTO;
import in.co.obs.dto.BookedDTO;
import in.co.obs.dto.UserDTO;

@Service
public class BookedBookServiceImpl implements BookedBookServiceInt {

	@Autowired
	private BookedBookDAOInt bookedBook;
	@Override
	public List<BookedDTO> findBooksById(Long id) {
		// TODO Auto-generated method stub
		return bookedBook.findBookedBooksById(id);
	}
	@Override
	public List<BookedDTO> list() {
		// TODO Auto-generated method stub
		List<BookedDTO> list=bookedBook.list();
		return list;
	}
	@Override
	public List<BookedDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		List<BookedDTO> list=bookedBook.list(pageNo, pageSize);
		return list;
	}
	@Override
	public List<BookedDTO> search(BookedDTO dto) {
		
		List<BookedDTO> list = bookedBook.search(dto);
		
		return list;
	}
	@Override
	public List<BookedDTO> search(BookedDTO dto, int pageNo, int pageSize) {
		
		List<BookedDTO> list = bookedBook.search(dto, pageNo, pageSize);
		
		return list;
	}
	@Override
	public Long add(BookedDTO bookedDTO) {
		Long pk=bookedBook.add(bookedDTO);
		return pk;
	}
	@Override
	public BookedDTO findByPk(Long id) {
		// TODO Auto-generated method st
		BookedDTO dto = bookedBook.findByPk(id);
		return dto;
	}

}

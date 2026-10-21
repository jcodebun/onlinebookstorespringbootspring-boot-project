package in.co.obs.dao;

import java.util.List;

import in.co.obs.dto.BookDTO;



public interface BookDAOInt {

	Long addBook(BookDTO dto);

	BookDTO findByBookName(String bookName);

	List<BookDTO> list();

	List<BookDTO> list(int pageNo, int pageSize);

	List<BookDTO> search(BookDTO dto);

	List<BookDTO> search(BookDTO dto, int pageNo, int pageSize);
	
	void delete(BookDTO dto);
	
	BookDTO findBypk(long pk);
	
	void update(BookDTO dto);
}

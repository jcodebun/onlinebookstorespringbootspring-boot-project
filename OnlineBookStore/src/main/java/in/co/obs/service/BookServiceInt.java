package in.co.obs.service;

import java.util.List;
import in.co.obs.dto.BookDTO;
import in.co.obs.exception.DuplicateRecordException;

public interface BookServiceInt {

	Long addBook(BookDTO dto) throws DuplicateRecordException;
	
	BookDTO findByBookName(String bookName);
	
	List<BookDTO> list();

	List<BookDTO> list(int pageNo, int pageSize);

	List<BookDTO> search(BookDTO dto);

	List<BookDTO> search(BookDTO dto, int pageNo, int pageSize);
	
	void delete(BookDTO dto);

	BookDTO findByPk(Long id);
	
	void update(BookDTO dto);
}

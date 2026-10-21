package in.co.obs.service;

import java.util.List;

import in.co.obs.dto.BookDTO;
import in.co.obs.dto.BookedDTO;
import in.co.obs.dto.UserDTO;

public interface BookedBookServiceInt {
	
	Long add(BookedDTO bookedDTO) ;

	List<BookedDTO> findBooksById(Long id);
	
	List<BookedDTO> list();

	List<BookedDTO> list(int pageNo, int pageSize);

	List<BookedDTO> search(BookedDTO dto);

	List<BookedDTO> search(BookedDTO dto, int pageNo, int pageSize);
	
	BookedDTO findByPk(Long id);
}

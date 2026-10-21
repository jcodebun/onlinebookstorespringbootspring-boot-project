package in.co.obs.dao;

import java.util.List;

import in.co.obs.dto.BookDTO;
import in.co.obs.dto.BookedDTO;
import in.co.obs.dto.UserDTO;

public interface BookedBookDAOInt {
	
	Long add(BookedDTO bookedDTO);

	List<BookedDTO> findBookedBooksById(Long id);

	List<BookedDTO> search(BookedDTO dto, int pageNo, int pageSize);

	List<BookedDTO> list();

	List<BookedDTO> list(int pageNo, int pageSize);

	List<BookedDTO> search(BookedDTO dto);

	UserDTO findBookedBooksById2(Long id);
	
	BookedDTO findByPk(Long id);
}

package in.co.obs.ctl;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import in.co.obs.dto.BookDTO;
import in.co.obs.exception.RecordNotFoundException;
import in.co.obs.form.BookForm;
import in.co.obs.service.BookServiceInt;

@Controller
public class HomeCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(HomeCtl.class.getName());


	@Autowired
	private BookServiceInt bookService;
	
	@RequestMapping(value = "/index", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchBooks(@ModelAttribute("form") BookForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/index";
		}

		int pageNo = form.getPageNo();
		int pageSize = form.getPageSize();

		if (OP_NEXT.equals(operation)) {
			pageNo++;
		} else if (OP_PREVIOUS.equals(operation)) {
			pageNo--;
		} else if (OP_NEW.equals(operation)) {
			return "redirect:/home/login/addBbook";
		}

		pageNo = (pageNo < 1) ? 1 : pageNo;
		pageSize = (pageSize < 1) ? 10 : pageSize;

		if (OP_DELETE.equals(operation)) {
		System.out.println("vid is :"+vid);
		System.out.println("form.getIds(): "+form.getId());
			pageNo = 1;
			if (form.getIds() != null) {
				for (long id : form.getIds()) {
					BookDTO dto = new BookDTO();
					dto.setId(id);
					bookService.delete(dto);
				}
				model.addAttribute("success", "Deleted Successfully!!!");
				
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		
		BookDTO dto = (BookDTO) form.getDTO();
	
		List<BookDTO> list = bookService.search(dto, pageNo, pageSize);
		List<BookDTO> totallist = bookService.search(dto);
		model.addAttribute("list", list);
		System.out.println("list size "+list.size());

		if (list.size() == 0 && !OP_DELETE.equalsIgnoreCase(operation)) {
			model.addAttribute("error", "Record not found");
		}

		int listsize = list.size();
		int total = totallist.size();
		int pageNoPageSize = pageNo * pageSize;

		form.setPageNo(pageNo);
		form.setPageSize(pageSize);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("listsize", listsize);
		model.addAttribute("total", total);
		model.addAttribute("pagenosize", pageNoPageSize);
		model.addAttribute("form", form);
		return "bookListOnHomePage";
	}
	@GetMapping("/home/pdf")
	 public void showPDF(@Param("id") Long id, HttpServletResponse response, BookDTO v)
	   throws ServletException, IOException, RecordNotFoundException {
		v = bookService.findByPk(id);
		//if(v.getSaleType().equalsIgnoreCase("Free")) {
			response.setContentType("application/pdf");
			response.getOutputStream().write(v.getBookPdf());
			response.getOutputStream().close();
		//}
	
		
	 }
}

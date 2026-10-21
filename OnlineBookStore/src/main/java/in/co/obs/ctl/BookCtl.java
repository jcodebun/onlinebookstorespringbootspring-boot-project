package in.co.obs.ctl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import in.co.obs.dto.BookDTO;
import in.co.obs.exception.DuplicateRecordException;
import in.co.obs.exception.RecordNotFoundException;
import in.co.obs.form.BookForm;
import in.co.obs.service.BookServiceInt;





@Controller
public class BookCtl extends BaseCtl {
	
	private static Logger log = Logger.getLogger(BookCtl.class.getName());


	@Autowired
	private BookServiceInt bookService;
	
	@ModelAttribute
	public void preload(Model model) {
		HashMap<String, String> saleType = new HashMap<String, String>();
		saleType.put("Free", "Free");
		saleType.put("Paid", "Paid");
		model.addAttribute("saleType", saleType);
	}
	
	@GetMapping("/home/login/addbook")
	public String display(@RequestParam(required = false) Long id, Long pId,@ModelAttribute("form") BookForm form,
			HttpSession session, Model model) {
		log.info("BookCtl display method start");
		if (form.getId() > 0) {
			BookDTO bean = bookService.findByPk(id);
			form.populate(bean);
		}
		log.info("BookCtl display method end");
		
		return "addBook";
	}
	@PostMapping("/home/login/addbook")
	public String submit(@Valid @ModelAttribute("form") BookForm form, BindingResult bindingResult,
			HttpSession session, Model model,HttpServletRequest request) throws IOException {
		System.out.println("in submit method");
		
		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/home/login/addbook";
		}
		
		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {				
				if (bindingResult.hasErrors()) {
					return "addBook";
				}
				BookDTO bean = (BookDTO) populateDTO(form.getDTO(),request);
				if (bean.getId() > 0) {
					bean.setBookImage(form.getBookImage().getBytes()); 
					bean.setBookPdf(form.getBookPdf().getBytes());
					bookService.update(bean);
					model.addAttribute("success", bean.getBookName()+" Book is updated sucessfully");
					
				} else {
					System.out.println("in here");
					bean.setBookImage(form.getBookImage().getBytes()); 
					bean.setBookPdf(form.getBookPdf().getBytes()); 

					bookService.addBook(bean);
					model.addAttribute("success", bean.getBookName()+" Book is added successfully");
				}
				return "addBook";
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "addBook";
		}
		return "";
	}
	@RequestMapping(value = "/home/login/books/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchBooks(@ModelAttribute("form") BookForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/home/login/books/search";
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
				//return "redirect:/home/login/books/search";
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		
		BookDTO dto = (BookDTO) form.getDTO();
	
		List<BookDTO> list = bookService.search(dto, pageNo, pageSize);
		List<BookDTO> totallist = bookService.search(dto);
		model.addAttribute("list", list);

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
		return "bookList";
	}
	
	@GetMapping("/image")
	 public void showImage(@Param("id") Long id, HttpServletResponse response, BookDTO v)
	   throws ServletException, IOException, RecordNotFoundException {
		v = bookService.findByPk(id);
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(v.getBookImage());
		response.getOutputStream().close();
	 }
	@GetMapping("/pdf")
	 public void showPDF(@Param("id") Long id, HttpServletResponse response, BookDTO v)
	   throws ServletException, IOException, RecordNotFoundException {
		v = bookService.findByPk(id);
		response.setContentType("application/pdf");
		response.getOutputStream().write(v.getBookPdf());
		response.getOutputStream().close();
	 }
	
	@GetMapping("/home/login/books/delete")
	 public String deleteBooks(@Param("id") Long id, HttpServletResponse response, BookDTO v, @ModelAttribute("form") BookForm form, Model model)
	   throws ServletException, IOException, RecordNotFoundException {
		Long bId =  form.getId();
		BookDTO dto = new BookDTO();
		dto.setId(bId);
		bookService.delete(dto);
		//model.addAttribute("success", "Book Deleted Successfully");
		return "redirect:/home/login/books/search";
	 }
	
	@RequestMapping(value = "/home/login/books/buy", method = { RequestMethod.GET, RequestMethod.POST })
	public String buyMoreBooks(@ModelAttribute("form") BookForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/home/login/books/buy";
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
				//return "redirect:/home/login/books/search";
			} else {
				model.addAttribute("error", "Select at least one record");
			}
		}
		
		BookDTO dto = (BookDTO) form.getDTO();
	
		List<BookDTO> list = bookService.search(dto, pageNo, pageSize);
		List<BookDTO> totallist = bookService.search(dto);
		model.addAttribute("list", list);

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
		return "buyBookList";
	}
	
}

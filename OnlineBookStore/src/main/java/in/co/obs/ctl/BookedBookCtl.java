package in.co.obs.ctl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import in.co.obs.dto.BookedDTO;
import in.co.obs.dto.UserDTO;
import in.co.obs.exception.DuplicateRecordException;
import in.co.obs.form.BookForm;
import in.co.obs.form.BookedForm;
import in.co.obs.form.LoginForm;
import in.co.obs.form.PayForm;
import in.co.obs.form.UserRegistrationForm;
import in.co.obs.service.BookServiceInt;
import in.co.obs.service.BookedBookServiceInt;
import in.co.obs.service.UserServiceInt;

@Controller
public class BookedBookCtl extends BaseCtl {

	@Autowired
	private BookedBookServiceInt bookedService;
	
	@Autowired
	private UserServiceInt userService;
	
	@Autowired
	private BookServiceInt bookService;
	
	@ModelAttribute
	public void preload(Model model) {
		HashMap<String, String> mm = new HashMap<String, String>();
		mm.put("01-JAN", "01-January");
		mm.put("02-FEB", "02-February");
		mm.put("03-MAR", "03-March");
		mm.put("04-APR", "04-April");
		mm.put("05-MAY", "05-May");
		mm.put("06-JUN", "06-June");
		mm.put("07-JUL", "07-July");
		mm.put("08-AUG", "08-August");
		mm.put("09-SEP", "09-September");
		mm.put("10-OCT", "10-October");
		mm.put("11-NOV", "11-November");
		mm.put("12-DEC", "12-December");
		model.addAttribute("mm", mm);
		HashMap<String, String> yy = new HashMap<String, String>();
		yy.put("2030", "2030");
		yy.put("2029", "2029");
		yy.put("2028", "2028");
		yy.put("2027", "2027");
		yy.put("2026", "2026");
		yy.put("2025", "2025");
		yy.put("2024", "2024");
		model.addAttribute("yy", yy);
	}
	
	@RequestMapping(value = "/home/login/bookedbook/search", method = { RequestMethod.GET, RequestMethod.POST })
	public String searchBooks(@ModelAttribute("form") BookedForm form,
			@RequestParam(required = false) String operation, Long vid, HttpSession session, Model model) {

		if (OP_RESET.equalsIgnoreCase(operation)) {
			return "redirect:/home/login/booked/search";
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

		
		
		//BookDTO dto = (BookDTO) form.getDTO();
		UserDTO userDTO = (UserDTO) session.getAttribute("user");
		Long id = userDTO.getId();
		System.out.println("session id is "+id);
		List<BookedDTO> list = bookedService.list();
		System.out.println("list "+list.toArray());
		
		model.addAttribute("list", list);
		

		
		  if (list.size() == 0 && !OP_DELETE.equalsIgnoreCase(operation)) {
		  model.addAttribute("error", "Record not found"); }
		  
		  int listsize = list.size();
		 
		//int total = totallist.size();
		int pageNoPageSize = pageNo * pageSize;

		form.setPageNo(pageNo);
		form.setPageSize(pageSize);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		//model.addAttribute("listsize", listsize);
		//model.addAttribute("total", total);
		model.addAttribute("pagenosize", pageNoPageSize);
		model.addAttribute("form", form);
		return "bookedBookList";
	}
	
	@GetMapping("/home/pay")
	public String displayPayment(@RequestParam(required = false) Long id, @ModelAttribute("form") PayForm form, HttpSession session, Model model) {
		System.out.println("bookid "+id);
		session.setAttribute("bookid", id);
		return "payment";
	}
	@PostMapping("/home/pay")
	public String pay(@Valid @ModelAttribute("form") PayForm form, BindingResult bindingResult,
			HttpSession session, Model model,HttpServletRequest request) throws IOException {
		System.out.println("in submit method");
		
		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/home/pay";
		}
		
		if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {				
			if (bindingResult.hasErrors()) {
				System.out.println(bindingResult.getFieldError());
				return "payment";
			}
			
			Long bookId = (long) session.getAttribute("bookid");
			UserDTO userBean = (UserDTO)session.getAttribute("user");
			
				System.out.println("in here"+userBean.getId());
				BookedDTO bookedDTO = new BookedDTO();
				bookedDTO.setBookId(bookId);
				bookedDTO.setUserId(userBean.getId());
				bookedDTO.setUser(userService.findByPk(userBean.getId()));
				bookedDTO.setBook(bookService.findByPk(bookId));
				bookedDTO.setUserName(userBean.getFirstName()+ " "+userBean.getLastName());
				bookedService.add(bookedDTO);
				model.addAttribute("success","Payment Successfull");
			return "payment";
		}
		return "";
	}
	
}

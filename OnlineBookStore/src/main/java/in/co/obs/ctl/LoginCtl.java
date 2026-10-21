package in.co.obs.ctl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

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
import org.springframework.web.bind.annotation.RequestParam;

import in.co.obs.dto.BookDTO;
import in.co.obs.dto.BookedDTO;
import in.co.obs.dto.UserDTO;
import in.co.obs.exception.DuplicateRecordException;
import in.co.obs.form.LoginForm;
import in.co.obs.form.UserRegistrationForm;
import in.co.obs.service.BookServiceInt;
import in.co.obs.service.UserServiceInt;






@Controller
public class LoginCtl extends BaseCtl{

	private Logger log = Logger.getLogger(LoginCtl.class.getName());

	protected static final String OP_SIGNIN = "SignIn";
	protected static final String OP_SIGNUP = "SignUp";
	protected static final String OP_LOGOUT = "Logout";
	protected static final String OP_CONFIRM = "Confirm";
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
	
	@GetMapping("/home/login")
	public String display(@ModelAttribute("form") LoginForm form, HttpSession session, Model model) {
		log.info("LoginCtl login display method start");
		if (session.getAttribute("user") != null) {
			session.invalidate();
			model.addAttribute("success", "You have logout Successfully!!!");
		}
		log.info("LoginCtl login display method End");
		return "login";
	}
	@PostMapping("/home/login")
	public String submit(@RequestParam String operation, HttpSession session,
			@Valid @ModelAttribute("form") LoginForm form, BindingResult result, Model model) {
		log.info("LoginCtl login submit method start");
		System.out.println("In dopost  LoginCtl");

		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/home/login";
		}

		if (OP_SIGNUP.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/home/login";
		}

		if (result.hasErrors()) {
			System.out.println(result);
			return "login";
		}

		UserDTO bean = userService.authentication((UserDTO) form.getDTO());

		if (bean != null) {
			System.out.println(bean.toString());
			session.setAttribute("user", bean);
			return "redirect:/home";
		}
		if (bean == null) {

			model.addAttribute("error", "Login Id & Password Invalid");
		}
		log.info("LoginCtl login submit method End");
		return "login";
	}
	
	@GetMapping("/home/newUser")
	public String display(@RequestParam(required = false) Long id,@ModelAttribute("form") UserRegistrationForm form, Model model, HttpSession session) {
		log.info("LoginCtl signUp display method start");
		
			System.out.println("id is:"+id);
			session.setAttribute("mybookid", id);
			model.addAttribute("bookid", id);
			log.info("LoginCtl signUp display method End");
		return "userRegister";
	}
	@PostMapping("/home/newUser")
	public String submit(@RequestParam String operation, @Valid @ModelAttribute("form") UserRegistrationForm form,
			BindingResult bindingResult, Model model, HttpServletRequest request,HttpSession session) {

		log.info("LoginCtl signUp submit method start");
		
		Long id =(Long)session.getAttribute("mybookid");
		System.out.println("bookid in save "+id);
		if (OP_RESET.equalsIgnoreCase(form.getOperation())) {
			return "redirect:/home/newUser";
		}

		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			return "userRegister";
		}

		try {
			if (OP_SAVE.equalsIgnoreCase(form.getOperation())) {
				UserDTO entity = (UserDTO) populateDTO(form.getDTO(), request);
				//session.setAttribute("user", entity);
				entity.setRoleId("2");
				entity.setRoleName("User");
				BookedDTO bookedEntity = new BookedDTO();
				BookDTO bookDTO = bookService.findByPk(id);
				Long userId = userService.nextPk();
				bookedEntity.setBookId(id);
				bookedEntity.setBook(bookDTO);
				bookedEntity.setUserName(form.getFirstName()+" "+form.getLastName());
				bookedEntity.setUserId(userId);
				bookedEntity.setUser(entity);
				List<BookedDTO> bookedBooks = Arrays.asList(bookedEntity);
				entity.setBooks(bookedBooks);
				userService.nextPk();
				userService.addUser(entity);
				//model.addAttribute("success", "User Registerd Successfully!!!!");
				return "redirect:/pdf?id="+id;
			}
		} catch (DuplicateRecordException e) {
			model.addAttribute("error", e.getMessage());
			return "userRegister";
	}

		log.info("LoginCtl signUp submit method end");
		return "newRegister";
	}
	}

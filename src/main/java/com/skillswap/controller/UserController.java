package com.skillswap.controller;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpSession;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.skillswap.model.Chat;
import com.skillswap.model.Message;
import com.skillswap.model.Notifications;
import com.skillswap.model.Request;
import com.skillswap.model.Request.RequestStatus;
import com.skillswap.model.Skill;
import com.skillswap.model.SkillsOffered;
import com.skillswap.model.SkillsWanted;
import com.skillswap.model.User;
import com.skillswap.model.UserDetails;
import com.skillswap.service.ChatService;
import com.skillswap.service.MessageService;
import com.skillswap.service.NotificationService;
import com.skillswap.service.RequestService;
import com.skillswap.service.SkillService;
import com.skillswap.service.UserService;

// http://localhost:8081/SkillSwap/
@Controller
@RequestMapping("/user")
// http://localhost:8081/SkillSwap/user
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SkillService skillService;
	
	@Autowired
	private RequestService requestService;
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private MessageService messageService;

	
	@GetMapping("/home")
	public String homePage() {
		return "home";
	}
	
	
	@GetMapping("/register")
	// http://localhost:8081/SkillSwap/user/register
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "signUp"; 
    }
	
	@PostMapping("/register")
	// http://localhost:8081/SkillSwap/user/register
	public String registerUser(@ModelAttribute("user") User user, Model model) {
		if(user.getFirstName() == null || user.getFirstName().trim().isEmpty()) {
		    model.addAttribute("errorFN", "First Name is required!");
		    return "signUp"; 
		}
		
		if(user.getLastName() == null || user.getLastName().trim().isEmpty()) {
			model.addAttribute("errorLN","Last Name is required!");
			return"signUp";
		}
		
		if(user.getPassword() == null || user.getPassword().trim().isEmpty()) {
			model.addAttribute("errorP","Password is required!");
			return"signUp";
		}
		
		if(user.getEmail() == null || user.getEmail().trim().isEmpty()) {
			model.addAttribute("errorE","Email is required!");
			return"signUp";
		}

		if(userService.existsByEmail(user.getEmail())) {
			model.addAttribute("errorEE","Email already exists!");
			return "signUp";
		}else {
			userService.registerUser(user);
			return "redirect:/user/login";
		}

	}
	
	
	@GetMapping("/login")
	// http://localhost:8081/SkillSwap/user/login
	public String showLoginForm(Model model) {
		model.addAttribute("user", new User()); 
	    return "signIn"; 
	}
	
	@PostMapping("/login")
	// http://localhost:8081/SkillSwap/user/login
	public String signInUser(@ModelAttribute("user") User user, Model model, HttpSession session) {
		
		if(user.getEmail().isEmpty()) {
			 model.addAttribute("loginError", "Please fill Email");
		        return "signIn";
		 }
		
		if(user.getPassword().isEmpty()) {
			 model.addAttribute("loginError", "Please fill Password");
		        return "signIn";
		 }
		
		if (!userService.existsByEmail(user.getEmail().trim())) {
		        model.addAttribute("loginError", "Email does not exist");
		        return "signIn";
		 }
		 
		String password = userService.getPasswordByEmail(user.getEmail().trim());
		 
		if (!password.equals(user.getPassword().trim())) {
		        model.addAttribute("loginError", "Invalid password");
		        return "signIn";
		 }
			
		session.setAttribute("loggedInUser", user.getEmail());
		
		 return "redirect:/user/dashboard";
}
	
	@GetMapping("/dashboard")
	// http://localhost:8081/SkillSwap/user/dashboard
	public String dashboard(HttpSession session, Model model) {
		
		String email = (String) session.getAttribute("loggedInUser");
		
		if(email == null) {
			return "redirect:/user/login";
		}
		
		User user = userService.findByEmail(email);
		
		int offeredSkills = skillService.getSkillsOfferedById(user.getId());
		int wantedSkills = skillService.getSkillsWantedById(user.getId());
		
		long pendingCount = requestService.findByReceiver(user.getId()).stream()
                .filter(r -> r.getStatus() == RequestStatus.PENDING)
                .count();

		

		
		model.addAttribute("user", user);
		model.addAttribute("offeredSkills",offeredSkills);
		model.addAttribute("wantedSkills",wantedSkills);
		model.addAttribute("pendingCount", pendingCount);
		
		return "dashboard";
	}
	
	@GetMapping("/profile")
	// http://localhost:8081/SkillSwap/user/profile
	public String profile(Model model, HttpSession session) {
	   
		String email = (String) session.getAttribute("loggedInUser");
		
		if(email == null) {
			return "redirect:/user/login";
		}
		
		User user = userService.findByEmail(email);
		
		
		if(user.getUserDetails() == null) {
			user.setUserDetails(new UserDetails());
		}
		
		
		List<Skill> skills = skillService.findAll();
		
		List<SkillsOffered> offeredSkill = new ArrayList<>(user.getSkillsOffereds());
		List<SkillsWanted> wantedSkill = new ArrayList<>(user.getSkillsWanteds());
		
		while (offeredSkill.size() < 5 ) {
			offeredSkill.add(new SkillsOffered());
			
		}
		
		 while (wantedSkill.size() < 5) {
		        SkillsWanted empty = new SkillsWanted();
		        empty.setUser(user);
		        empty.setSkill(null);
		        wantedSkill.add(empty);
		    }

		
		
		
		model.addAttribute("user",user);
		model.addAttribute("skills",skills);
		model.addAttribute("offeredSkills",offeredSkill);
		model.addAttribute("wantedSkills",wantedSkill);
		return "profile";
	}
	
	@PostMapping("/profile/update")
	// http://localhost:8081/SkillSwap/user/profile/update
	public String updateProfile(@ModelAttribute("user") User user,
								@RequestParam("skillIds") List<Integer> skillIds,
								@RequestParam("skillLevels") List<String> skillLevels,
								@RequestParam("offeredSkillRowIds") List<Integer> offeredSkillRowIds,
								@RequestParam(value = "wantedSkillIds", required = false) List<Integer> wantedSkillIds,
						        @RequestParam(value="wantedSkillRowIds", required=false) List<String> wantedSkillRowIds,
						        @RequestParam(value = "photoFile", required = false) MultipartFile file,
	                            HttpSession session) {
	    
		String email = (String) session.getAttribute("loggedInUser"); 
		if(email == null) return "redirect:/user/login";
		
		
		userService.updateUserProfile(user, skillIds, skillLevels,file ,email,  offeredSkillRowIds ,wantedSkillIds, wantedSkillRowIds);
		
		 return "redirect:/user/profile";

	}

	@GetMapping("/photo/{id}")
	// http://localhost:8081/SkillSwap/user/photo/id
	public ResponseEntity<byte[]> getPhoto(@PathVariable int id ){
	
		User user = userService.getUserById(id);
		
		
		
		if (user == null || user.getUserDetails() == null || user.getUserDetails().getPhoto() == null) {
		    return ResponseEntity.notFound().build();
		}

		
		byte[] photo = user.getUserDetails().getPhoto();
		String type = user.getUserDetails().getPhotoType();
		
		
		MediaType mediaType = (type != null)
				? MediaType.parseMediaType(type)
			    : MediaType.IMAGE_JPEG;
		
		
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(mediaType);
	    
	    return new ResponseEntity<>(photo, headers, HttpStatus.OK);
		
		
	}
	
	@GetMapping("/sendRequests")
	// http://localhost:8081/SkillSwap/user/sendRequests
	public String sendRequest(HttpSession session,
							 @RequestParam(value = "skillId", required = false) Integer skillId,
							 Model model) {
		
		String email = (String) session.getAttribute("loggedInUser");
		
		if(email == null) {return "redirect:/user/login";}
		
		User sender = userService.findByEmail(email);
		
		List<SkillsWanted> skillsWanteds = new ArrayList<>(sender.getSkillsWanteds());
		model.addAttribute("wantedSkills",skillsWanteds);
		
		
		if (skillId != null) {
	        Skill skill = skillService.searchById(skillId);
	        List<User> usersWithSkill = userService.findUsersByOfferedSkill(skillId);

	        Map<User, String> usersWithLevel = new HashMap<>();
	        Map<Integer, Boolean> requestStatus = new HashMap<Integer, Boolean>();
	        
	        
	        for (User u : usersWithSkill) {
	        	if(u.getId() == sender.getId()) continue;
	            for (SkillsOffered so : u.getSkillsOffereds()) {
	                if (so.getSkill().getId().equals(skillId)) {
	                    usersWithLevel.put(u, so.getLevel());
	                    
	                    
	                    boolean exists = requestService.existsBySenderAndReceiver(sender.getId(), u.getId());
	                    requestStatus.put(u.getId(), exists);
	                    
	                }
	            }
	        }

	        model.addAttribute("skill", skill);
	        model.addAttribute("usersWithLevel", usersWithLevel);
	        model.addAttribute("reqeustStatus",requestStatus);
	    }
		
		return "sendRequests";
	}
	
	@PostMapping("/sendRequestToUser")
	// http://localhost:8081/SkillSwap/user/sendRequestToUser
	public String sendRequestForUser(
									 @RequestParam("receiverId") int reciverId,
									 @RequestParam(value = "skillId", required = false) Integer skillId,
									 HttpSession session,
									 RedirectAttributes redirectAttributes
									) {
		String email = (String) session.getAttribute("loggedInUser");
		
		if(email == null) return "redirect:/user/login";
		
		User sender = userService.findByEmail(email);
		User reciver = userService.getUserById(reciverId);
		Skill skill =skillService.searchById(skillId);
		
		Notifications notification = new Notifications();
		notification.setSender(sender);
		notification.setReceiver(reciver);
		notification.setSkill(skill);
	
		
		Request request= new Request();
		request.setSender(sender);
		request.setReceiver(reciver);
		request.setStatus(RequestStatus.PENDING);
		request.setNotifications(notification);
		
		
		notification.addRequest(request);
		
		notificationService.saveNotification(notification);
		
		
		redirectAttributes.addFlashAttribute("successMessage","The request has been sent successfully!");	

		return "redirect:/user/sendRequests";
	}
	
	@GetMapping("/myRequests")
	// http://localhost:8081/SkillSwap/user/myRequests
	public String viewRequests(HttpSession session , Model model) {
		
		String email = (String) session.getAttribute("loggedInUser");
		if(email == null) return "redirect:/user/login";
		
		User sender = userService.findByEmail(email);
		
		List<Notifications> notifications = notificationService.findBySender(sender.getId());
		
		List<Map<String, Object>> requestData = new ArrayList<>();
		
		for(Notifications n : notifications) {
			for(Request r : n.getRequests()) {
				Map<String, Object> map = new HashMap<>();
				map.put("recieverName", r.getReceiver().getFirstName()+" " + r.getReceiver().getLastName());
				map.put("skillName", n.getSkill().getSkillName());
				map.put("status", r.getStatus());
				requestData.add(map);
			}
		}
		
		model.addAttribute("requestsData", requestData);
		
		
		return "viewRequests";
	}
	
	@GetMapping("/notifications")
	// http://localhost:8081/SkillSwap/user/motifications
	public String notifications(HttpSession session, Model model) {
		
		String email = (String) session.getAttribute("loggedInUser");
		if(email == null) return "redirect:/user/login";
		
		User user = userService.findByEmail(email);
		
		List<Request> receivedRequests = requestService.findByReceiver(user.getId());
		
		model.addAttribute("receivedRequests",receivedRequests);
		return "notifications";
	}
	
	@PostMapping("/respondRequest")
	// http://localhost:8081/SkillSwap/user/respondRequest
	public String respondRequest(HttpSession session , 
								 @RequestParam("requestId") int requestId,
								 @RequestParam("action") String button) {
		
		
		String email = (String) session.getAttribute("loggedInUser");
		if(email == null) return "redirect:/user/login";
		
		Request request = requestService.getRequestById(requestId);
		if(request == null) {
			return "redirect:/user/notifications";
		}
		
		if("ACCEPT".equalsIgnoreCase(button)) {
			request.setStatus(RequestStatus.ACCEPTED);
			
			User sender = request.getSender();
			User receiver = request.getReceiver(); // iam 
			
			Chat chat = chatService.findByUsers(sender, receiver);
			if(chat == null) {
				chat = new Chat();
				chat.setUserSender(sender);
				chat.setUserReceiver(receiver);
				chatService.saveChat(chat);
			}
			
			
			
		} else if("REJECT".equalsIgnoreCase(button)) {
			request.setStatus(RequestStatus.REJECTED);
		}
		
		requestService.saveRequest(request);
		
		return "redirect:/user/notifications";
	}
	
	
	
	@GetMapping("/chats")
	// http://localhost:8081/SkillSwap/user/chats
	public String myChats(HttpSession session, Model model)
	{
		String email = (String) session.getAttribute("loggedInUser");
		if(email == null) return "redirect:/user/login";
		
		
		User currentUser = userService.findByEmail(email);
		
		List<Chat> chats = chatService.getAllChatsByIdForUser(currentUser.getId());
		
		model.addAttribute("chats",chats);
		model.addAttribute("currentUser",currentUser);
		
		return "chats";
	}
	
	
	
	@GetMapping	("/chats/{id}")
	// http://localhost:8081/SkillSwap/user/chats/id
	public String openChat(
							@PathVariable int id ,
							HttpSession session,
							Model model) {
		String email = (String) session.getAttribute("loggedInUser");
		if(email == null)  return "redirect:/user/login";
		
		Chat chat = chatService.getChatById(id);
		
		if(chat == null) {
			return "redirect:/chats";
		}
		
		model.addAttribute("chat", chat);
		model.addAttribute("messages",chat.getMessages());
		
		User currentUser = userService.findByEmail(email);
		model.addAttribute("currentUser",currentUser);
		
		
		
		return "messages";
	}
	
	
	@PostMapping("/chats/{id}/send")
	// http://localhost:8081/SkillSwap/user/chats/id/send
	public String sendMessage(@PathVariable int id,
							  @RequestParam("content") String content,
							 HttpSession session)
	{
		String email = (String) session.getAttribute("loggedInUser");
		if(email == null)  return "redirect:/user/login";
		
		
		User sender = userService.findByEmail(email);
		
		Chat chat = chatService.getChatById(id);
		if(chat == null) return "redirect:/chats";
		
		
		User receiver = (sender.getId() == chat.getUserSender().getId()) 
						? chat.getUserReceiver()
					    : chat.getUserSender();			
		
		
		Message message = new Message();
		message.setChat(chat);
		message.setSender(sender);
		message.setReceiver(receiver);
		message.setMessageText(content);
		message.setTimestamp(LocalDateTime.now());
		
		
		messageService.saveMessage(message);
		
		return "redirect:/user/chats/" +id;
		
		
	}
	
	
	
	
	
	
	
	
	@GetMapping("/delete")
	// http://localhost:8081/SkillSwap/user/delete
	public String deleteUser(HttpSession session) {
		String email = (String) session.getAttribute("loggedInUser");
		
		
		if(email != null) {
			User user = userService.findByEmail(email);
			if(user != null) {
				userService.removeUserById(user.getId());
				session.invalidate();
			}
			
		}
		return "redirect:/user/home";
	}
	
	
	@GetMapping("/logout")
	// http://localhost:8081/SkillSwap/user/logout
	public String logOut(HttpSession session) {
		session.invalidate();
		return "redirect:/user/home";
	}
	
	
	
	
}

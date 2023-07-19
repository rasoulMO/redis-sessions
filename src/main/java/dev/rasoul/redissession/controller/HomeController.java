package dev.rasoul.redissession.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HomeController {

	private final String HOME_VIEW_COUNT = "homeViewCount";

	@GetMapping("/")
	public String home(Principal principal, HttpSession session) {
		incrementViewCount(session, HOME_VIEW_COUNT);
		return "Hello World! " + principal.getName();
	}

	@GetMapping("/home")
	public String home2(HttpSession session) {
		incrementViewCount(session, HOME_VIEW_COUNT);
		return "homeViewCount " + session.getAttribute(HOME_VIEW_COUNT);
	}

	private void incrementViewCount(HttpSession session, String attr) {
		var viewCount =  session.getAttribute(attr) == null ? 0 : (Integer)session.getAttribute(attr);
		session.setAttribute(attr, ++viewCount);
	}
}

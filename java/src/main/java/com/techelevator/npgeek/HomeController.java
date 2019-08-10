package com.techelevator.npgeek;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.dao.ParkDao;
import com.techelevator.dao.model.Park;

@Controller
@SessionAttributes("temperature")
public class HomeController {
	
	@Autowired
	private ParkDao parkDao;
	
	@RequestMapping(value= {"/", "/homePage"})
	public String displayHomePage(HttpServletRequest request) {
		
		List<Park> parks = getParks(request);
		request.setAttribute("parks", parks);
		
		return "homePage";
	}
	
	private List<Park> getParks(HttpServletRequest request) {
		
		List<Park> parks = parkDao.getAll();
		
		return parks;
	}
	
	

}

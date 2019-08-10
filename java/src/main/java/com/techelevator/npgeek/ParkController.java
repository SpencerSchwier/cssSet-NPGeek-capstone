package com.techelevator.npgeek;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.dao.ParkDao;
import com.techelevator.dao.WeatherDao;
import com.techelevator.dao.model.Park;
import com.techelevator.dao.model.Weather;

@Controller
@SessionAttributes({"temperature","park", "id"})
public class ParkController {
	
	@Autowired
	private ParkDao parkDao;
	
	@Autowired
	private WeatherDao weatherDao;
	
	List<Weather> fiveDayForecast;
	
	@RequestMapping(path="/parkDetail", method=RequestMethod.GET)
	public String getParkDetail(@RequestParam String id, ModelMap mm) {
		
		String parkCode = id;
		Park park = parkDao.getParkByParkCode(parkCode);
		mm.put("id", id);
		mm.put("park", park);
		List<Weather> fiveDayForecast = weatherDao.getWeatherList(parkCode);
		
		if (!mm.containsAttribute("temperature")) {
			mm.put("temperature", "fahrenheit");
		}
		
		
		for (Weather w : fiveDayForecast) {
			if (mm.get("temperature").equals("fahrenheit")) {
				w.setIsFahrenheit(true);
			} else if (mm.get("temperature").equals("celsius")){
				w.setIsFahrenheit(false);
			}
		}
		mm.put("weather", fiveDayForecast);
		
		
		
		
		return "parkDetail";
	}
	
	
	
	
	
	
	
	// Make a Post Method that redirects to the same page
	@RequestMapping(path="/parkDetail", method=RequestMethod.POST)
	public String postParkDetail(@RequestParam String temperature, ModelMap mm) {
		
		// This method will need to check the value of temperature from the session and change the tempType accordingly
		mm.put("temperature", temperature);
		
	
		
		return "redirect:/parkDetail";
		
	}
	
	
	
	
	
	
	
	
	


	
	
	
}

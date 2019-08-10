package com.techelevator.npgeek;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.dao.ParkDao;
import com.techelevator.dao.SurveyDao;
import com.techelevator.dao.model.Park;
import com.techelevator.dao.model.Survey;

@Controller
public class SurveyController {
	
	@Autowired
	private ParkDao parkDao;
	
	@Autowired 
	private SurveyDao surveyDao;
	
	// This method will return the survey page with a modelmap containing parks
	@RequestMapping(path= "/survey", method=RequestMethod.GET)
	public String displaySurveyPage(ModelMap mm) {
		List<Park> parkList = parkDao.getAll();
		mm.put("parks", parkList);
		
		if(mm.containsAttribute("survey") == false) {
			Survey survey = new Survey();
			mm.put("survey", survey);
		}
		return "survey";
	}
	
	// Takes results from survey jsp and saves them to survey object to be stored in database
	// Redirects to the surveyResult page
	@RequestMapping(path="/survey", method=RequestMethod.POST)
	public String displaySurveyResultPage(@Valid @ModelAttribute("survey") Survey survey, BindingResult result, RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("survey", survey);
			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "survey", result);
			return "redirect:/survey";
		}
		
	
		surveyDao.save(survey);
		
		
		return "redirect:/surveyResult";
	}
	
	@RequestMapping(path="/surveyResult", method=RequestMethod.GET)
	public String showSurveyResult(ModelMap mm) {
		List<Park> popularParkList = surveyDao.getParksBySurveys();
		mm.addAttribute("popularParks", popularParkList);
		return "surveyResult";
	}
	
	
}

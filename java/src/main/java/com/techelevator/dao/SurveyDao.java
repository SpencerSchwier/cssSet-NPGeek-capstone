package com.techelevator.dao;

import java.util.List;

import com.techelevator.dao.model.Park;
import com.techelevator.dao.model.Survey;

public interface SurveyDao {
	
	public List<Park> getParksBySurveys();
	public void save(Survey post);
	
}

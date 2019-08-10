package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.dao.model.Park;
import com.techelevator.dao.model.Survey;

@Component
public class JdbcSurveyDao implements SurveyDao {
	
	private JdbcTemplate jdbcTemplate;
		
	@Autowired
	public JdbcSurveyDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Park> getParksBySurveys() {
		List<Park> allSurveys = new ArrayList<>();
		String sqlSelectReviews = "SELECT park.parkcode AS parkcode, park.parkname AS parkname, COUNT(*) AS surveycount\n" + 
				"FROM survey_result\n" + 
				"JOIN park ON park.parkcode = survey_result.parkcode\n" + 
				"GROUP BY survey_result.parkcode, park.parkname, park.parkcode\n" + 
				"HAVING COUNT(*) > 0\n" + 
				"ORDER BY COUNT(*) DESC, park.parkname ASC";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectReviews);
		while (results.next()) {
			Park park = new Park();
			park.setParkCode(results.getString("parkcode"));
			park.setParkName(results.getString("parkname"));
			park.setTotalVotes(results.getInt("surveycount"));
			allSurveys.add(park);
		}
		return allSurveys;
		
	}

	@Override
	public void save(Survey post) {
		// Save the survey to the database
		Long id = getNextId();
		String sqlInsertSurvey = "INSERT INTO survey_result(surveyid, parkcode, emailaddress, state, activitylevel) VALUES (?, ?, ?, ?, ?) ";
		jdbcTemplate.update(sqlInsertSurvey, id, post.getParkCode(), post.getEmailAddress(), post.getState(), post.getActivityLevel());
		post.setSurveyId(id);
	}
	
	private Long getNextId() {
		String sqlSelectNextId = "SELECT NEXTVAL('seq_surveyid')";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectNextId);
		Long id = null;
		if(results.next()) {
			id = results.getLong(1);
		} else {
			throw new RuntimeException("Something strange happened, unable to select next forum post id from sequence");
		}
		return id;
	}
}

package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.dao.model.Park;

@Component
public class JdbcParkDao implements ParkDao {
	
	private static final String SELECT_PARKS_SQL = "SELECT parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies FROM park";
	private static final String SELECT_INDIVIDUAL_PARK_SQL = "SELECT parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies FROM park WHERE parkcode = ?";
    private final JdbcTemplate jdbcTemplate;
    
    @Autowired
    public JdbcParkDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    @Override
    public List<Park> getAll() {
    	
    	List<Park> myParks = new ArrayList<Park>();
    	
        SqlRowSet results = jdbcTemplate.queryForRowSet(SELECT_PARKS_SQL);
        
        Park park;
        
        while (results.next()) {
        	park = mapRowToPark(results);
        	myParks.add(park);
        }

        return myParks;
    }
    
    @Override
    public Park getParkByParkCode(String parkCode) {
    	SqlRowSet result = jdbcTemplate.queryForRowSet(SELECT_INDIVIDUAL_PARK_SQL, parkCode);
    	
    	Park park = null;
    	
    	if (result.next()) {
			park = mapRowToPark(result);
		}
    	return park;
    }
    
    private Park mapRowToPark(SqlRowSet results) {
    	
    	Park park = new Park();
    	
    	park.setParkCode(results.getString("parkcode"));
    	park.setParkName(results.getString("parkname"));
    	park.setState(results.getString("state"));
    	park.setAcreage(results.getInt("acreage"));
    	park.setElevationInFeet(results.getInt("elevationinfeet"));
    	park.setMilesOfTrail(results.getDouble("milesoftrail"));
    	park.setNumberOfCampsites(results.getInt("numberofcampsites"));
    	park.setClimate(results.getString("climate"));
    	park.setYearFounded(results.getInt("yearfounded"));
    	park.setAnnualVisitorCount(results.getInt("annualvisitorcount"));
    	park.setInspirationalQuote(results.getString("inspirationalquote"));
    	park.setInspirationalQuoteSource(results.getString("inspirationalquotesource"));
    	park.setParkDescription(results.getString("parkdescription"));
    	park.setEntryFee(results.getInt("entryfee"));
    	park.setNumberOfAnimalSpecies(results.getInt("numberofanimalspecies"));
    	
    	return park;
    	
    }
}

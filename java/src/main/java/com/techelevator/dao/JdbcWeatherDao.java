package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.dao.model.Weather;

@Component
public class JdbcWeatherDao implements WeatherDao {
	
	private static final String SELECT_WEATHERS_SQL = "SELECT parkcode, fivedayforecastvalue, low, high, forecast FROM weather";

	private static final String SELECT_INDIVIDUAL_WEATHER_SQL = "SELECT parkcode, fivedayforecastvalue, low, high, forecast FROM weather WHERE parkcode = ?";

	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcWeatherDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Weather> getWeatherList(String parkCode) {
		// Return a list of all weather information
		
		List<Weather> myWeathers = new ArrayList<Weather>();
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(SELECT_INDIVIDUAL_WEATHER_SQL, parkCode);
		
		Weather weather;
		
		while (results.next()) {
			weather = mapRowToWeather(results);
			myWeathers.add(weather);
		}
		
		
		return myWeathers;
	}

	@Override
	public Weather getWeatherByParkCode(String parkCode) {
		// Returns a single result of the weather by the park code given
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(SELECT_INDIVIDUAL_WEATHER_SQL, parkCode);
		
		Weather weather = null;
		
		if (result.next()) {
			weather = mapRowToWeather(result);
		}
		
		return weather;
		
	}
	
	private Weather mapRowToWeather(SqlRowSet results) {
		
		Weather weather = new Weather();
		
		weather.setParkCode(results.getString("parkcode"));
		weather.setFiveDayForecastValue(results.getInt("fivedayforecastvalue"));
		weather.setLow(results.getInt("low"));
		weather.setHigh(results.getInt("high"));
		weather.setForecast(results.getString("forecast"));
		
		return weather;
	}
	
	
	
}

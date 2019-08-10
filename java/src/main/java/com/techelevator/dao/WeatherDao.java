package com.techelevator.dao;

import java.util.List;

import com.techelevator.dao.model.Weather;

public interface WeatherDao {
	
	List<Weather> getWeatherList(String parkCode);
	
	Weather getWeatherByParkCode(String parkCode);
	
}

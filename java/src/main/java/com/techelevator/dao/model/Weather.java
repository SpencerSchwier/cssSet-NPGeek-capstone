package com.techelevator.dao.model;

public class Weather {
	
	private String parkCode;
	private int fiveDayForecastValue;
	private double low;
	private double high;
	private String forecast;
	private Boolean isFahrenheit = true;
	private String message;
	private String newMessage;
	
	
	public Boolean getIsFahrenheit() {
		return isFahrenheit;
	}
	public void setIsFahrenheit(Boolean isFahrenheit) {
		this.isFahrenheit = isFahrenheit;
	}
	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public int getFiveDayForecastValue() {
		return fiveDayForecastValue;
	}
	public void setFiveDayForecastValue(int fiveDayForecastValue) {
		this.fiveDayForecastValue = fiveDayForecastValue;
	}
	public double getLow() {
		double temp = low;
		if (isFahrenheit==false) {
			temp = (low - 32) * (double) (5.0 /9.0);
		}
		return temp;
	}
	public void setLow(int low) {
		this.low = low;
	}
	public double getHigh() {
		double temp = high;
		if (isFahrenheit==false) {
			temp = (high - 32) * (5.0 /9.0);
		}
		return temp;
	}
	public void setHigh(int high) {
		this.high = high;
	}
	public String getForecast() {
		if (forecast.contains("partly cloudy")) {
			forecast = "partlyCloudy";
		}
		return forecast;
	}
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}

	public String getMessage() {
		if (forecast.contains("snow")) {
			message = "Pack snowshoes";
		} else if (forecast.contains("rain")) {
			message = "Pack raingear and water-proof shoes";
		} else if (forecast.contains("thunderstorms")) {
			message = "Seek shelter and avoid hiking on exposed ridges";
		} else {
			message = "Nice day for hiking";
		}
		return message;
	}
	
	public String getNewMessage() {
		if (high > 75) {
			newMessage = "Bring an extra gallon of water";
		} else if ((high - low) > 20) {
			newMessage = "Wear breathable layers";
		} else if (low < 20) {
			newMessage = "Warning: frigid temperatures";
		} else {
			newMessage = "";
		}
		return newMessage;
	}

}

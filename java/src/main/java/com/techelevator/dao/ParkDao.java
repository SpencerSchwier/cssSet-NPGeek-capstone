package com.techelevator.dao;

import java.util.List;

import com.techelevator.dao.model.Park;

public interface ParkDao {
	
    List<Park> getAll();
	
    Park getParkByParkCode(String parkCode);
}

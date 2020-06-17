package com.techgig.brillio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techgig.brillio.model.Building;

public interface BuildingRepository extends JpaRepository<Building, Integer> {
	
	public Building findById(int id);

	public Building findByName(String name);
}

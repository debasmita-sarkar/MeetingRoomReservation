package com.techgig.brillio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techgig.brillio.model.Building;
import com.techgig.brillio.model.MeetingRoom;

public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, Integer> {
	
	public MeetingRoom findById(int id);

	public MeetingRoom findByName(String name);	
}

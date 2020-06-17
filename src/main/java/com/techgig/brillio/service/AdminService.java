package com.techgig.brillio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techgig.brillio.Exceptions.DuplicateEntryException;
import com.techgig.brillio.Exceptions.InValidFloorException;
import com.techgig.brillio.model.Building;
import com.techgig.brillio.model.MeetingRoom;
import com.techgig.brillio.model.User;
import com.techgig.brillio.repositories.BuildingRepository;
import com.techgig.brillio.repositories.MeetingRoomRepository;
import com.techgig.brillio.repositories.UserRepository;

@Service
public class AdminService {
	@Autowired
	MeetingRoomRepository roomRepo = null;
	
	@Autowired
	UserRepository userRepo = null;
	
	@Autowired
	BuildingRepository buildingRepo;

	public void addBuilding(Building building) throws com.techgig.brillio.Exceptions.DuplicateEntryException {
		if(building == null) {
			return;
		}
		if(buildingRepo.findByName(building.getName())!=null) {
			throw new com.techgig.brillio.Exceptions.DuplicateEntryException("There is already a building with the same name:"+building.getName()+".");
		}
		//add to db
		building =buildingRepo.save(building);
		System.out.println("inside add building service:"+ building.toString());
		//CityCache.add(city.getId(), city);			
		//System.out.println("all done"+ CityCache.get(city.getId()));		
	}
	public void addUser(User user) throws com.techgig.brillio.Exceptions.DuplicateEntryException {
		if(user == null) {
			return;
		}
		if(userRepo.findByName(user.getName())!=null) {
			throw new com.techgig.brillio.Exceptions.DuplicateEntryException("There is already a user with the same name:"+user.getName()+".");
		}
		//add to db
		user =userRepo.save(user);
		System.out.println("inside add building service:"+ user.toString());
		
	}
	public void addRoom(MeetingRoom room) throws com.techgig.brillio.Exceptions.DuplicateEntryException, InValidFloorException {
		if(room == null) {
			return;
		}
		if(roomRepo.findByName(room.getName())!=null) {
			throw new com.techgig.brillio.Exceptions.DuplicateEntryException("There is already a room with the same name:"+room.getName()+".");
		}
		
		if(room.getBuildingName()!=null) {
			Building building = buildingRepo.findByName(room.getBuildingName());
			System.out.println("inside add floor service building:"+ room.getBuildingName()+":"+building.toString());
			room.setBuilding(building);
			if(room.getFloor()> building.getNoOfFloors()) {
				
				throw new InValidFloorException("Floor number specified for this room is not present in the building.");
			}
		}
		//add to db
		room =roomRepo.save(room);
		System.out.println("inside add room service:"+ room.toString());
		
	}
	
	public void deleteBuilding(int buildingId) {
		buildingRepo.deleteById(buildingId);
		
	}
	
	public void deleteRoom(int roomId) {
		roomRepo.deleteById(roomId);
		
	}

	public void addMultiUsers(List<User> users) throws DuplicateEntryException {
		users.forEach(user->{
			try {
				addUser(user);
			} catch (DuplicateEntryException e) {
				System.out.println("addMultiUsers duplicte entry excetion:"+e.getMessage());
			}
		});		
	}

	public void addMultiRooms(List<MeetingRoom> rooms) {
		rooms.forEach(room->{
			try {
				try {
					addRoom(room);
				} catch (InValidFloorException e) {					
				}
			} catch (DuplicateEntryException e) {
				System.out.println("addMultiRooms duplicte entry excetion:"+e.getMessage());
			}
		});
		
	}

	public void deleteUser(int userId) {
		userRepo.deleteById(userId);
		
	}

}

package com.techgig.brillio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techgig.brillio.Exceptions.DuplicateEntryException;
import com.techgig.brillio.Exceptions.InValidFloorException;
import com.techgig.brillio.model.Building;
import com.techgig.brillio.model.MeetingRoom;
import com.techgig.brillio.model.User;
import com.techgig.brillio.service.AdminService;

@RestController
@RequestMapping(com.techgig.brillio.utility.URLConstants.ADMIN)
public class AdminController {
	@Autowired
	AdminService adminService;
	
	@RequestMapping(method=RequestMethod.POST,value=com.techgig.brillio.utility.URLConstants.BUILDING)
	@CrossOrigin(origins = "*")	
	public void addBuilding(@RequestBody Building building) throws DuplicateEntryException  {
		System.out.println("in add building controller:"+building.toString());
		adminService.addBuilding(building);
	}
	
	@RequestMapping(method=RequestMethod.POST,value=com.techgig.brillio.utility.URLConstants.USER)
	@CrossOrigin(origins = "*")	
	public void addUser(@RequestBody User user) throws DuplicateEntryException  {
		System.out.println("in add user controller:"+user.toString());
		adminService.addUser(user);
	}
	
	@RequestMapping(method=RequestMethod.POST,value=com.techgig.brillio.utility.URLConstants.ROOM)
	@CrossOrigin(origins = "*")	
	public void addRoom(@RequestBody MeetingRoom room) throws DuplicateEntryException, InValidFloorException  {
		System.out.println("in add room controller:"+room.toString());
		adminService.addRoom(room);
	}
	//Delete
	
	@RequestMapping(method=RequestMethod.DELETE, value=com.techgig.brillio.utility.URLConstants.BUILDING)
	public void deleteBuilding(@RequestParam int buildingId) {
		adminService.deleteBuilding(buildingId);		
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value=com.techgig.brillio.utility.URLConstants.ROOM)
	public void deleteRoom(@RequestParam int roomId) {
		adminService.deleteRoom(roomId);		
	}
	@RequestMapping(method=RequestMethod.DELETE, value=com.techgig.brillio.utility.URLConstants.USER)
	public void deleteUser(@RequestParam int userId) {
		adminService.deleteUser(userId);		
	}
	
	//Post multiple 
	//Not tested
	@RequestMapping(method=RequestMethod.POST,value=com.techgig.brillio.utility.URLConstants.USERS)
	@CrossOrigin(origins = "*")	
	public void addUsers(@RequestBody List<User> users) throws DuplicateEntryException  {
		System.out.println("in add user controller:"+users);
		adminService.addMultiUsers(users);
	}
	
	//Not tested
	@RequestMapping(method=RequestMethod.POST,value=com.techgig.brillio.utility.URLConstants.ROOMS)
	@CrossOrigin(origins = "*")	
	public void addRooms(@RequestBody List<MeetingRoom> rooms)  {
		System.out.println("in add building controller:"+rooms);
		adminService.addMultiRooms(rooms);
	}

}

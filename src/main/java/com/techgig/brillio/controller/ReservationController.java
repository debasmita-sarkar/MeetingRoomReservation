package com.techgig.brillio.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.techgig.brillio.Exceptions.NoRoomSelectedException;
import com.techgig.brillio.Exceptions.RoomNotAvailableException;
import com.techgig.brillio.model.Reservation;
import com.techgig.brillio.service.ReservationService;
import com.techgig.brillio.utility.Capacity;

@RestController
@RequestMapping(com.techgig.brillio.utility.URLConstants.RESERVATION)
public class ReservationController {
	
	@Autowired
	ReservationService reservationService;
	
	@RequestMapping(method=RequestMethod.POST,value="")
	@CrossOrigin(origins = "*")	
	public void bookMeeting(@RequestBody Reservation reservation) throws NoRoomSelectedException, RoomNotAvailableException {
		System.out.println("In bookMeeting:"+reservation.toString());
		reservationService.createBooking(reservation);
	}
	@RequestMapping(method=RequestMethod.DELETE, value="")
	public void deleteBuilding(@RequestParam int reservationId) {
		reservationService.cancelBooking(reservationId);		
	}
	
	@RequestMapping(com.techgig.brillio.utility.URLConstants.AVAILABLEROOMS)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public List<String> getAvailableRooms(@RequestParam ("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime, @RequestParam ("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) throws RoomNotAvailableException {
		System.out.println("getAvailableRooms:"+startTime+":"+ endTime);
		return reservationService.getAvailableRooms(startTime, endTime);
		 
	}
	
	@RequestMapping(com.techgig.brillio.utility.URLConstants.AVAILABLEROOMSBYBUILDING)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public List<String> getAvailableRoomsByBuilding(@RequestParam ("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime, @RequestParam ("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime,String buildingName) throws RoomNotAvailableException {
		System.out.println("getAvailableRoomsByFloor:"+startTime+":"+ endTime+":"+buildingName);
		return reservationService.getAvailableRoomsByBuilding(startTime, endTime,buildingName);
		
	}
	
	@RequestMapping(com.techgig.brillio.utility.URLConstants.AVAILABLEROOMSBYBUILDINGANDFLOOR)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public List<String> getAvailableRoomsByBuildingAndFloor(@RequestParam ("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime, @RequestParam ("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime,String buildingName,int floor) throws RoomNotAvailableException {
		System.out.println("getAvailableRoomsByFloor:"+startTime+":"+ endTime+":"+buildingName);
		return reservationService.getAvailableRoomsByBuildingAndFloor(startTime, endTime,buildingName,floor);
		
	}
	
	@RequestMapping(com.techgig.brillio.utility.URLConstants.AVAILABLEROOMSBYBUILDINGCAPANDFLOOR)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public List<String> getAvailableRoomsByBuildingCapAndFloor(@RequestParam ("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime, @RequestParam ("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime,String buildingName,int floor,Capacity capacity) throws RoomNotAvailableException {
		System.out.println("getAvailableRoomsByBuildingCapAndFloor:"+startTime+":"+ endTime+":"+buildingName+":"+capacity.name());
		return reservationService.getAvailableRoomsByBuildingCapAndFloor(startTime, endTime,buildingName,floor,capacity);
		
	}
	
	@RequestMapping(com.techgig.brillio.utility.URLConstants.AVAILABLEROOMSBYBUILDINGANDCAPACITY)
	@CrossOrigin(origins = "*")
	@ResponseBody
	public List<String> getAvailableRoomsByBuildingANDCAPACITY(@RequestParam ("startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime, @RequestParam ("endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime,String buildingName,Capacity capacity) throws RoomNotAvailableException {
		System.out.println("getAvailableRoomsByBuildingANDCAPACITY:"+startTime+":"+ endTime+":"+buildingName+capacity.name());
		return reservationService.getAvailableRoomsByBuildingAndCap(startTime, endTime,buildingName,capacity);
		
	}
	
}

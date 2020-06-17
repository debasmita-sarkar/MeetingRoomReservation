package com.techgig.brillio.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.annotations.Synchronize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.techgig.brillio.Exceptions.NoRoomSelectedException;
import com.techgig.brillio.Exceptions.RoomNotAvailableException;
import com.techgig.brillio.model.Building;
import com.techgig.brillio.model.MeetingRoom;
import com.techgig.brillio.model.Reservation;
import com.techgig.brillio.repositories.BuildingRepository;
import com.techgig.brillio.repositories.MeetingRoomRepository;
import com.techgig.brillio.repositories.ReservationRepository;
import com.techgig.brillio.repositories.UserRepository;
import com.techgig.brillio.utility.Capacity;

@Service
public class ReservationService {
	
	@Autowired
	ReservationRepository reservationRepo;
	@Autowired
	private MeetingRoomRepository roomRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private BuildingRepository buildingRepo;
	
 
	
	public synchronized void createBooking(Reservation reservation) throws NoRoomSelectedException, RoomNotAvailableException {
		if(reservation == null) {
			return;
		}
		MeetingRoom room=null;
		if(reservation.getRoomName()!=null) {
			room=roomRepo.findByName(reservation.getRoomName());
			reservation.setRoom(room);
		}
		if(reservation.getUserEmail()!=null) {
			reservation.setUser(userRepo.findByEmail(reservation.getUserEmail()));
		}
		if(room == null) {
			throw new NoRoomSelectedException("Not able to see a room in the reservation details.Please try to reserve again.");
		}
		boolean isRoomFree = isRoomAvailable(reservation);
		if(isRoomFree)
		reservationRepo.save(reservation);
		else {
			throw new RoomNotAvailableException("oops !! The room is not available right now.Please try another room");
		}
		
	}
	public void cancelBooking(int reservationId) {
		
		reservationRepo.deleteById(reservationId);
		
	}
	public List<String> getAvailableRooms(LocalDateTime startTime,LocalDateTime endTime) throws RoomNotAvailableException {
		
		List<String> availableRooms = reservationRepo.findMeetingRoomNamesByTimeStamps(startTime,endTime);
		System.out.println("availableRooms"+availableRooms);
		if(availableRooms == null || availableRooms.isEmpty()) {
			throw new RoomNotAvailableException("Oops !!No rooms are available at this moment.Please try booking rooms in other timeslots.");
		}else {
			List<String> availableRoomsImproved = availableRooms.stream().map(roomName->{String[]roomStr =roomName.split(",");return ("Room:"+roomStr[0]+",Capacity:"+roomStr[1]);}).collect(Collectors.toList());
			System.out.println("availableRoomsImproved"+availableRoomsImproved);
			return availableRoomsImproved;
		}	
	}
	
	public List<String> getAvailableRoomsByBuilding(LocalDateTime startTime,LocalDateTime endTime,String buildingName) throws RoomNotAvailableException {
		Building building = buildingRepo.findByName(buildingName);
		int buildingId =0;
		if(building != null) {
			buildingId = building.getId();
		}
		List<String> availableRooms = reservationRepo.findMeetingRoomsByTimeStampsAndBuildingId(startTime, endTime, buildingId);		
		System.out.println("availableRooms by floor:"+availableRooms);
		if(availableRooms == null || availableRooms.isEmpty()) {
			throw new RoomNotAvailableException("Oops !!No rooms are available in "+buildingName+" building at this moment.Please try booking rooms in other buildings.");
		}else {
			List<String> availableRoomsImproved = availableRooms.stream().map(roomName->{String[]roomStr =roomName.split(",");return ("Room:"+roomStr[0]+",Capacity:"+roomStr[1]);}).collect(Collectors.toList());
			System.out.println("availableRoomsImproved"+availableRoomsImproved);
			return availableRoomsImproved;
		}
	}
	
	public List<String> getAvailableRoomsByBuildingAndFloor(LocalDateTime startTime,LocalDateTime endTime,String buildingName,int floor) throws RoomNotAvailableException {
		Building building = buildingRepo.findByName(buildingName);
		int buildingId =0;
		if(building != null) {
			buildingId = building.getId();
		}
		List<String> availableRooms = reservationRepo.findMeetingRoomsByTimeStampsAndBuildingIdAndFloor(startTime, endTime, buildingId,floor);		
		System.out.println("availableRooms by floor:"+availableRooms);
		if(availableRooms == null || availableRooms.isEmpty()) {
			throw new RoomNotAvailableException("Oops !!No rooms are available in "+buildingName+" at floor "+floor+" at this moment.Please try booking rooms in other floors/buildings.");
		}else {
			List<String> availableRoomsImproved = availableRooms.stream().map(roomName->{String[]roomStr =roomName.split(",");return ("Room:"+roomStr[0]+",Capacity:"+roomStr[1]);}).collect(Collectors.toList());
			System.out.println("availableRoomsImproved"+availableRoomsImproved);
			return availableRoomsImproved;
		}
	}
	
	public boolean isRoomAvailable(Reservation reservation) throws NoRoomSelectedException {
		if(reservation == null) {
			return false;
		}
		if(reservation.getRoom() == null) {
			throw new NoRoomSelectedException("Not able to see a room in the reservation details.Please try to reserve again.");
		}
		int meetingRoomId = reservation.getRoom().getId();
		LocalDateTime endTime = reservation.getEndTime();
		LocalDateTime startTime = reservation.getStartTime();
		System.out.println("meetingRoomId:"+meetingRoomId+":endTime:"+endTime+":startTime:"+startTime);
		int reservationId =  reservationRepo.findReservationByTimeStampsAndRoomId(startTime,endTime,meetingRoomId);
		if(reservationId == 0) {
			return true;
		}else {
			return false;
		}
	}
	public List<String> getAvailableRoomsByBuildingCapAndFloor(LocalDateTime startTime, LocalDateTime endTime,
			String buildingName, int floor, Capacity cap) throws RoomNotAvailableException {
		Building building = buildingRepo.findByName(buildingName);
		int buildingId =0;
		if(building != null) {
			buildingId = building.getId();
		}
		List<String> availableRooms = reservationRepo.findMeetingRoomsByTimeStampsAndBuildingIdFloorCapacity(startTime, endTime, buildingId, floor, cap.name());		
		
		if(availableRooms == null || availableRooms.isEmpty()) {
			throw new RoomNotAvailableException("Oops !!No "+cap +" rooms are available in "+buildingName+" building, floor - " +floor+" at this moment.Please try booking rooms in other floors/buildings.");
		}else {	
			System.out.println("availableRooms by all:"+availableRooms);
			return availableRooms;
		}
	}
	
	public List<String> getAvailableRoomsByBuildingAndCap(LocalDateTime startTime, LocalDateTime endTime,
			String buildingName, Capacity cap) throws RoomNotAvailableException {
		{
			Building building = buildingRepo.findByName(buildingName);
			int buildingId =0;
			if(building != null) {
				buildingId = building.getId();
			}
			List<String> availableRooms = reservationRepo.findMeetingRoomsByTimeStampsAndBuildingIdAndCapacity(startTime, endTime, buildingId, cap.name());		
			
			if(availableRooms == null || availableRooms.isEmpty()) {
				throw new RoomNotAvailableException("Oops !!No "+cap +" rooms are available in "+buildingName+" building at this moment.Please try booking rooms in other buildings.");
			}else {	
				System.out.println("availableRooms by all:"+availableRooms);
				return availableRooms;
			}
		}
	}

}

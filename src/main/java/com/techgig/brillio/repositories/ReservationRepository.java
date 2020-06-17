package com.techgig.brillio.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.techgig.brillio.model.MeetingRoom;
import com.techgig.brillio.model.Reservation;
import com.techgig.brillio.model.User;
import com.techgig.brillio.utility.Capacity;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	
	public Reservation findById(int id);
	public Set<Reservation> findByUser(User user);
	public Set<Reservation> findByRoom(MeetingRoom room);
	@Query(value = "select name,capacity from meetingroom where id not in(select meetingroom_id from reservation where (:startTime BETWEEN start_time AND end_time) OR (:endTime BETWEEN start_time AND end_time))", 
			  nativeQuery = true)
			List<String> findMeetingRoomNamesByTimeStamps(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
	@Query(value = "select name,capacity from meetingroom where id not in(select meetingroom_id from reservation where (:startTime BETWEEN start_time AND end_time) OR (:endTime BETWEEN start_time AND end_time)) AND building_id=:buildingId", 
			  nativeQuery = true)
	public List<String> findMeetingRoomsByTimeStampsAndBuildingId(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime,int buildingId);
	
	@Query(value = "select name from meetingroom where id not in(select meetingroom_id from reservation where (:startTime BETWEEN start_time AND end_time) OR (:endTime BETWEEN start_time AND end_time)) AND building_id=:buildingId AND capacity=:capacity", 
			  nativeQuery = true)
	public List<String> findMeetingRoomsByTimeStampsAndBuildingIdAndCapacity(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime,int buildingId,String capacity);
	
	@Query(value = "select name,capacity from meetingroom where id not in(select meetingroom_id from reservation where (:startTime BETWEEN start_time AND end_time) OR (:endTime BETWEEN start_time AND end_time)) AND building_id=:buildingId AND floor=:floor", 
			  nativeQuery = true)
	public List<String> findMeetingRoomsByTimeStampsAndBuildingIdAndFloor(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime,int buildingId,int floor);
	@Query(value = "select name from meetingroom where id not in(select meetingroom_id from reservation where (:startTime BETWEEN start_time AND end_time) OR (:endTime BETWEEN start_time AND end_time)) AND building_id=:buildingId AND floor=:floor AND capacity=:capacity", 
			  nativeQuery = true)
	public List<String> findMeetingRoomsByTimeStampsAndBuildingIdFloorCapacity(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime,int buildingId,int floor,String capacity);
	
	@Query(value = "select count(*) from reservation where ((:startTime BETWEEN start_time AND end_time) OR (:endTime BETWEEN start_time AND end_time)) AND meetingroom_id=:roomId", 
			  nativeQuery = true)
	public int findReservationByTimeStampsAndRoomId(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime,int roomId);
	
}

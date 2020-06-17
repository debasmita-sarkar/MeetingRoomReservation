package com.techgig.brillio.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="reservation")
public class Reservation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;	
	
	@ManyToOne
    @JoinColumn(name = "meetingroom_id")    
	private MeetingRoom room;
	
	@Column
	//@DateTimeFormat(iso = DateTimeFormatter.ISO_LOCAL_DATE_TIME.)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	LocalDateTime startTime;
	
	@Column
	//@DateTimeFormat(iso = DateTimeFormatter.ISO_LOCAL_DATE_TIME)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	LocalDateTime endTime;
	
	@ManyToOne
    @JoinColumn(name = "user_id")    
	private User user;
	
	@Transient
	String roomName;
	
	@Transient
	String userEmail;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MeetingRoom getRoom() {
		return room;
	}

	public void setRoom(MeetingRoom room) {
		this.room = room;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Override
	public String toString() {
		StringBuffer desc= new StringBuffer("id=" + id);
		if(room !=null) {
			desc.append(room.getName());
		}
		if(startTime !=null) {
			desc.append(startTime);
		}
		if(endTime !=null) {
			desc.append(endTime);
		}
		if(user !=null) {
			desc.append(user.getEmail());
		}
		if(userEmail !=null) {
			desc.append(userEmail);
		}
		return desc.toString();
	}
	
	
	
	

}

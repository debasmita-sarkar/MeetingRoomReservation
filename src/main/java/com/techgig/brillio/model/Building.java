package com.techgig.brillio.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="building")
public class Building {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	
	@Column
	String name;
	
	@Column
	int noOfFloors;
	
	@OneToMany(mappedBy = "building")
	private Set<MeetingRoom> meetingRooms;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getNoOfFloors() {
		return noOfFloors;
	}

	public void setNoOfFloors(int noOfFloors) {
		this.noOfFloors = noOfFloors;
	}

	public Set<MeetingRoom> getMeetingRooms() {
		return meetingRooms;
	}

	public void setMeetingRooms(Set<MeetingRoom> meetingRooms) {
		this.meetingRooms = meetingRooms;
	}

	//@Override
	/*
	 * public String toString() { StringBuffer desc = new
	 * StringBuffer("Building [id=" + id + "name"+name ) ;
	 * 
	 * desc.append("]"); return desc.toString(); }
	 */
}

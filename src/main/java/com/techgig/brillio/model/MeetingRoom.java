package com.techgig.brillio.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.techgig.brillio.utility.Capacity;

@Entity
@Table(name="meetingroom")
public class MeetingRoom {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
	int id;
	
    @Enumerated(EnumType.STRING )
	Capacity capacity;
    
    @Column
    String name;
    
    @Column
	int floor;
    
    @Transient
   	String buildingName;	
	
	
	@ManyToOne
    @JoinColumn(name = "building_id")    
	private Building building;
	
	@OneToMany(mappedBy = "room")
	private Set<Reservation> reservations;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Capacity getCapacity() {
		return capacity;
	}

	public void setCapacity(Capacity capacity) {
		this.capacity = capacity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}
	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

	@Override
	public String toString() {
		StringBuffer desc = new StringBuffer("MeetingRoom [id=" + id + ", capacity=" + capacity+",name="+name+"floor:"+floor) ;		
		if (reservations != null) {
			desc.append(reservations);
		}
		desc.append("]");
		return desc.toString();		
	}
	
	
}

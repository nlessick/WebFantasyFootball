package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author {Nathaniel Essick} - nlessick
 * CIS175 - Spring 2021
 * {2/10/2021}
 */

@Entity
@Table(name="roster")
public class ListRoster {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	@Column(name="POSITION")
	private String position;
	@Column(name="Name")
	private String name;
	
	public ListRoster() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ListRoster(String position, String name) {
		this.position = position;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String returnRosterDetails() {
		return position + ": " + name;
	}

}

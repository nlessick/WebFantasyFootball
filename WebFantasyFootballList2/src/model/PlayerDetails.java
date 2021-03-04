package model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author {Nathaniel Essick} - nlessick
 * CIS175 - Spring 2021
 * {3/3/2021}
 */
@Entity
@Table(name="player_details")
public class PlayerDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ROSTER_ID")
	private int id;
	@Column(name="OWNER_NAME")
	private String ownerName;
	@Column(name="START_YEAR")
	private LocalDate startDate;
	@ManyToOne (cascade=CascadeType.PERSIST)
	@JoinColumn(name="TEAM_ID")
	private Team team;
	@OneToMany(cascade=CascadeType.MERGE, fetch=FetchType.EAGER)
	@JoinTable(name="details_on_list", joinColumns= {@JoinColumn(name="ROSTER_ID", referencedColumnName="ROSTER_ID")}, inverseJoinColumns= {@JoinColumn(name="DETAILS_ID", referencedColumnName="ID", unique=true)})
	private List<ListRoster> listOfTeams;
	
	public PlayerDetails() {
		super();
	}

	public PlayerDetails(int id, String ownerName, LocalDate startDate, Team team, List<ListRoster> listOfTeams) {
		super();
		this.id = id;
		this.ownerName = ownerName;
		this.startDate = startDate;
		this.team = team;
		this.listOfTeams = listOfTeams;
	}

	public PlayerDetails(String ownerName, LocalDate startDate, Team team, List<ListRoster> listOfTeams) {
		super();
		this.ownerName = ownerName;
		this.startDate = startDate;
		this.team = team;
		this.listOfTeams = listOfTeams;
	}

	public PlayerDetails(String ownerName, LocalDate startDate, Team team) {
		super();
		this.ownerName = ownerName;
		this.startDate = startDate;
		this.team = team;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public List<ListRoster> getListOfTeams() {
		return listOfTeams;
	}

	public void setListOfTeams(List<ListRoster> listOfTeams) {
		this.listOfTeams = listOfTeams;
	}

	@Override
	public String toString() {
		return "PlayerDetails [id=" + id + ", ownerName=" + ownerName + ", startDate=" + startDate + ", team=" + team
				+ ", listOfTeams=" + listOfTeams + "]";
	}
	
	
	
}

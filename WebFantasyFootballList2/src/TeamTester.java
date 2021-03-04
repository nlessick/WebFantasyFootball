import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controller.PlayerDetailsHelper;
import controller.TeamHelper;
import model.ListRoster;
import model.PlayerDetails;
import model.Team;

/**
 * @author {Nathaniel Essick} - nlessick
 * CIS175 - Spring 2021
 * {2/10/2021}
 */
public class TeamTester {
	
	public static void main(String[] args) {
		
		Team joe = new Team("Joe");
		
		PlayerDetailsHelper pdh = new PlayerDetailsHelper();
		
		ListRoster kirk = new ListRoster("Wide Receiver", "Christian Kirk");
		ListRoster newton = new ListRoster("QB", "Cam Newton");
		
		List<ListRoster> joesRoster = new ArrayList<ListRoster>();
		joesRoster.add(kirk);
		joesRoster.add(newton);
		
		PlayerDetails joesTeam = new PlayerDetails("Joe's Team", LocalDate.now(), joe);
		joesTeam.setListOfTeams(joesRoster);
		
		pdh.insertNewPlayerDetails(joesTeam);
		
		List<PlayerDetails> allLists = pdh.getLists();
		
		for(PlayerDetails a : allLists) {
			System.out.println(a.toString());
		}
	}

}

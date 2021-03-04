package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ListRoster;
import model.PlayerDetails;
import model.Team;

/**
 * Servlet implementation class createDetailsServlet
 */
@WebServlet("/createDetailsServlet")
public class createDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public createDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ListRosterHelper lrh = new ListRosterHelper();
		String ownerName = request.getParameter("ownerName");
		System.out.println("Owner Name: " + ownerName);
		
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String year = request.getParameter("year");
		String teamName = request.getParameter("teamName");
		LocalDate ld;
		
		try {
			ld = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		} catch(NumberFormatException ex) {
			ld = LocalDate.now();
		}
		
		String[] selectedPlayers = request.getParameterValues("allDetailsToAdd");
		List<ListRoster> selectedPlayersInList = new ArrayList<ListRoster>();
		if(selectedPlayers != null && selectedPlayers.length > 0) {
			for(int i = 0; i < selectedPlayers.length; i++) {
				System.out.println(selectedPlayers[i]);
				ListRoster c = lrh.searchForRosterById(Integer.parseInt(selectedPlayers[i]));
				selectedPlayersInList.add(c);
			}
		}
		
		Team team = new Team(teamName);
		PlayerDetails sld = new PlayerDetails(ownerName, ld, team);
		sld.setListOfTeams(selectedPlayersInList);
		PlayerDetailsHelper slh = new PlayerDetailsHelper();
		slh.insertNewPlayerDetails(sld);
		
		System.out.println("Success");
		System.out.println(sld.toString());
		
		getServletContext().getRequestDispatcher("/viewAllDetailsServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

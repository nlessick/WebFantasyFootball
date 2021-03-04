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
 * Servlet implementation class editDetailsServlet
 */
@WebServlet("/editDetailsServlet")
public class editDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public editDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PlayerDetailsHelper dao = new PlayerDetailsHelper();
		ListRosterHelper lih = new ListRosterHelper();
		TeamHelper th = new TeamHelper();
		
		Integer tempId = Integer.parseInt(request.getParameter("id"));
		PlayerDetails listToUpdate = dao.searchForTeamDetailsById(tempId);
		
		String newOwnerName = request.getParameter("ownerName");
		
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String year = request.getParameter("year");
		
		String teamName = request.getParameter("teamName");
		Team newTeam = th.findTeam(teamName);
		
		LocalDate ld;
		try {
			ld = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		} catch (NumberFormatException ex) {
			ld = LocalDate.now();
		}
		try {
			String[] selectedItems = request.getParameterValues("allDetailsToAdd");
			List<ListRoster> selectedPlayersInList = new ArrayList<ListRoster>();
			
			for(int i = 0; i < selectedItems.length; i++) {
				System.out.println(selectedItems[i]);
				ListRoster c = lih.searchForRosterById(Integer.parseInt(selectedItems[i]));
				selectedPlayersInList.add(c);
			}
			listToUpdate.setListOfTeams(selectedPlayersInList);
		} catch (NullPointerException ex) {
			List<ListRoster> selectedPlayersInList = new ArrayList<ListRoster>();
			listToUpdate.setListOfTeams(selectedPlayersInList);
		}
		
		listToUpdate.setOwnerName(newOwnerName);
		listToUpdate.setStartDate(ld);
		listToUpdate.setTeam(newTeam);
		
		dao.updateList(listToUpdate);
		
		getServletContext().getRequestDispatcher("/viewAllDetailsServlet").forward(request, response);
	}

}

package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PlayerDetails;

/**
 * Servlet implementation class detailsnavigationServlet
 */
@WebServlet("/detailsnavigationServlet")
public class detailsnavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public detailsnavigationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PlayerDetailsHelper dao = new PlayerDetailsHelper();
		String act = request.getParameter("doThisToList");
		
		if(act == null) {
			getServletContext().getRequestDispatcher("/viewAllDetailsServlet").forward(request, response);
		} else if (act.contentEquals("delete")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				PlayerDetails playerToDelete = dao.searchForTeamDetailsById(tempId);
				dao.deletePlayer(playerToDelete);
			} catch (NumberFormatException e) {
				System.out.println("Forgot to click a button");
			} finally {
				getServletContext().getRequestDispatcher("/viewAllDetailsServlet").forward(request, response);
			}
		} else if(act.contentEquals("edit")) {
				try {
					Integer tempId = Integer.parseInt(request.getParameter("id"));
					PlayerDetails listToEdit = dao.searchForTeamDetailsById(tempId);
					
					request.setAttribute("listToEdit", listToEdit);
					
					request.setAttribute("month",  listToEdit.getStartDate().getMonthValue());
					request.setAttribute("date",  listToEdit.getStartDate().getDayOfMonth());
					request.setAttribute("year", listToEdit.getStartDate().getYear());
					
					ListRosterHelper daoForPlayers = new ListRosterHelper();
					request.setAttribute("allPlayers",  daoForPlayers.showAllPlayers());
					
					if(daoForPlayers.showAllPlayers().isEmpty()) {
						request.setAttribute("allPlayers",  " ");
					}
					
					getServletContext().getRequestDispatcher("/edit-team.jsp").forward(request, response);
				} catch (NumberFormatException e) {
					getServletContext().getRequestDispatcher("/viewAllDetailsServlet").forward(request, response);
				} 
		}else if(act.contentEquals("add")) {
					getServletContext().getRequestDispatcher("/new-details.jsp").forward(request, response);
				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

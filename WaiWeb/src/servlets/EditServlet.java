package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.UserNotFoundExecption;
import Dao.UserDaoImpl;
import model.User;


public class EditServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	final UserDaoImpl daoImp = new UserDaoImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("jakd");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		User user = new User();

		int id = 0 ;
 		if (request.getParameter("id") != null) {
			id = Integer.valueOf(request.getParameter("id"));
 		}
					
		if(action.equals("editUser")){
			try {
				user = daoImp.getUserFromDatabase(id);
			} catch (UserNotFoundExecption e) {
				e.printStackTrace();
			}
			
			request.setAttribute("user", user);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/Edit.jsp");
			dispatcher.forward(request, response);	
			
 		} else if(action.equals("deleteUser")){
 			//Löscht den ausgewählten Nutzer in der Datenbank und kehrt zur User Liste zurück:
 			daoImp.deleteUserInDatabase(id);
 			
 			System.out.println("User mit der ID: " + id + " erfolgreich gelöscht!");
 			backToAuswahl(request, response);
 			
 		} else if(action.equals("saveUser")){
 			//Löscht den ausgewählten Nutzer in der Datenbank und kehrt zur User Liste zurück:
 			
 			System.out.println("User mit der ID: " + id + " erfolgreich geupdatet!");
 			backToAuswahl(request, response);
 		}
	}
	
	//Funktion um auf die User Liste zurückzukehren:
	void backToAuswahl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/Auswahlm�glichkeiten.jsp");
		dispatcher.forward(request, response);
	}
}

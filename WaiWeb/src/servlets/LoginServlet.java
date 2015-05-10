package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import utils.Tool_Security;
import Dao.UserDaoImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     * Servlet zur Verarbeitung des Logins:
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    final UserDaoImpl daoImp = new UserDaoImpl();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {				
		//Der Parameter action beinhaltet das Attribut das wir in den JSP über name=? angeben:
		String action = request.getParameter("action");
		
		//Falls auf den Button "Return" geklickt wird, rückleiten auf Login:
		if (action.equals("Return")) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/html/login.html");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//daoImp.createUserInDatabase(new User("niat", new String(Tool_Security.hashFromString("passwort")), 1, "27.4.1989", "hallo"));
		//daoImp.createUserInDatabase(new User("luam", new String(Tool_Security.hashFromString("passwort")), 1, "27.4.1989", "hallo"));

		//Der Parameter action beinhaltet das Attribut das wir in den JSP über name=? angeben:
		String action = request.getParameter("action");
		String tempUser, tempPw;
		
		//Parameter aus Eingabefeldern holen:
		tempUser = request.getParameter("username");
		tempPw = request.getParameter("password");	
		
		//Falls auf den Button "Submit" geklickt wird:
		if (action.equals("Submit")) {
				
				//Testen ob Logindaten richtig sind, falls ja auf Auswahlseite (todo: check if admin):
				if (daoImp.isUserLoginValid(tempUser,new String(Tool_Security.hashFromString(tempPw))) == true ) {
					
					List<User> collection = daoImp.getAllUsers();
					request.setAttribute("users", collection);
					
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/Auswahl.jsp");
					dispatcher.forward(request, response);
					
				//Falls Logindaten falsch, auf Error-Seite weiterleiten:
				} else {
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/html/LoginError.html");
					dispatcher.forward(request, response);
				}
		}
	}
}

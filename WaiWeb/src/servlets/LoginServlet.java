package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.UserDaoImpl;
import Dao.Interface.UserInterface;

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
    final UserInterface userDao = daoImp;

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
		//Der Parameter action beinhaltet das Attribut das wir in den JSP über name=? angeben:
		String action = request.getParameter("action");
		String tempUser, tempPw = null;
		
		//Parameter aus Eingabefeldern holen:
		tempUser = request.getParameter("username");
		tempPw = request.getParameter("password");	
		
		//Falls auf den Button "Submit" geklickt wird:
		if (action.equals("Submit")) {
			
			//Testweise um auf Error weiterzuleiten falls keine Eingabe gemacht wurde (später nur DB Abfrage):
			if (tempUser != "" && tempPw != "" ) {
				
				//Testen ob Logindaten richtig sind, falls ja auf Auswahlseite (todo: check if admin):
				if (userDao.isUserLoginValid(tempUser, tempPw) == true ) {
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/Auswahl.jsp");
					dispatcher.forward(request, response);
					
				//Falls Logindaten falsch, auf Error-Seite weiterleiten:
				} else {
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/html/LoginError.html");
					dispatcher.forward(request, response);
				}
			
			//Später löschen, nur testweise:
			} else {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/html/LoginError.html");
				dispatcher.forward(request, response);
			}
		}
	}
}

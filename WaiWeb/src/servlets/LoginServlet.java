package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exception.UserNotFoundExecption;
import model.User;
import utils.Tool_Security;
import Dao.CamDaoImpl;
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
    final CamDaoImpl camdaoImp= new CamDaoImpl();
    private User user;
    private Long tempID;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {				
		//Der Parameter action beinhaltet das Attribut das wir in den JSP über name=? angeben:
		String action = request.getParameter("action");
		if (action == null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/html/login.html");
			dispatcher.forward(request, response);
		}
		
		//Falls auf den Button "Return" geklickt wird, rückleiten auf Login:
		else if (action.equals("Return")) {
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
		String tempUser, tempPw;
		
		//Parameter aus Eingabefeldern holen:
		tempUser = request.getParameter("username");
		tempPw = request.getParameter("password");	
		
		//Falls auf den Button "Submit" geklickt wird:
		if (action.equals("Submit")) {
			
			//Testen ob Logindaten richtig sind, falls ja auf Auswahlseite (todo: check if admin):
			if (daoImp.isUserLoginValid(tempUser,new String(Tool_Security.hashFromString(tempPw))) == true ) {
				
				//Rechte des Users abfragen und in die Session übertragen:
				try {
					tempID = daoImp.getUserIdFromDatabaseByName(tempUser);
					user = daoImp.getUserFromDatabase(tempID);
				} catch (UserNotFoundExecption e) {
					e.printStackTrace();
				}
				
				//Neue Session anlegen:
	            HttpSession session = request.getSession();
	            session.setAttribute("username", tempUser);
	            session.setAttribute("rechte", user.getRechte());
	            session.setMaxInactiveInterval(30*60);
	            Cookie userCookie = new Cookie("username", tempUser);
	            userCookie.setMaxAge(30*60);
	            
	            response.addCookie(userCookie);
	            response.sendRedirect(request.getContextPath() + "/auswahl");
				//RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/Auswahlmoeglichkeiten.jsp");
				//dispatcher.forward(request, response);	
				
			//Falls Logindaten falsch, auf Error-Seite weiterleiten:
			} else {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/html/LoginError.html");
				dispatcher.forward(request, response);
			}
		}
		
		//Für Logout zuständig, SessionID ausgeben falls vorhanden dann Session beenden:
		else if (action.equals("Logout")) {
	        Cookie[] cookies = request.getCookies();
	        if(cookies != null){
		        for(Cookie cookie : cookies){
		            if(cookie.getName().equals("JSESSIONID")){
		                System.out.println("Verwendete JSessionID = "+cookie.getValue());
		                break;
		            }
		        }
	        }
	        //Session beenden falls existierend:
	        HttpSession session = request.getSession(false);
	        if(session != null){
	        	System.out.println("Session mit User = "+session.getAttribute("username") + " wird beendet!");
	            session.invalidate();
	            //session = null;
	        }
			
	        //response.sendRedirect(request.getContextPath() + "/html/LoginError.html");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/html/login.html");
			dispatcher.forward(request, response);		
		}
	}
}

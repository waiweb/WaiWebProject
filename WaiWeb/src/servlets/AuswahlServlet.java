package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.CamDaoImpl;
import Dao.UserDaoImpl;
import model.Cam;
import model.User;

public class AuswahlServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    final UserDaoImpl daoImp = new UserDaoImpl();
    final CamDaoImpl camdaoImp= new CamDaoImpl();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action.equals("user")){
			List<User> collection = daoImp.getAllUsers();
			request.setAttribute("users", collection);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/User.jsp");
			dispatcher.forward(request, response);		
			
				
		}
		
		if(action.equals("cam")){
			List<Cam> collection=camdaoImp.getAllCams();
			request.setAttribute("cams", collection);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/Cam.jsp");
			dispatcher.forward(request, response);		
		}
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	}


}

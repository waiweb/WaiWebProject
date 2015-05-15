package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.UserNotFoundExecption;
import Dao.UserDaoImpl;
import model.User;


public class EditServlet extends HttpServlet{
	
	final UserDaoImpl daoImp = new UserDaoImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("jakd");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post");
        
		String action = request.getParameter("action");
		
		
         int id = 0 ;
 		if (request.getParameter("id") != null) {
			id = Integer.valueOf(request.getParameter("id"));
			System.out.println("geschafft");
 		}
			System.out.println(id);
		
			
			if(action.equals("Submit")){
 		      User user = null;
 		      try {
				user = daoImp.getUserFromDatabase(1);
 		      } catch (UserNotFoundExecption e) {
				e.printStackTrace();
			}
			request.setAttribute("user", user);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("//jsp/Edit.jsp");
			dispatcher.forward(request, response);		
 		}else if(action.equals("delete")){
 			
 			System.out.println("delete");
 		}
		
	}
	
 		
 	
 		
				
		
		
	
	


}

package servlets;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.Tool_ImageProcessing;
import Dao.DatabaseControllerImpl;

/**
 * Servlet implementation class MasterServlet
 */
@WebServlet("/MasterServlet")
public class MasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MasterServlet() {
        super();
        
		DatabaseControllerImpl db = new DatabaseControllerImpl();
		db.createDatabase();
		
		

    }
    
    // ToDo    Servlet um Liste f�r cams und benutzer an Auswahl.jsp zu senden
    
    
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

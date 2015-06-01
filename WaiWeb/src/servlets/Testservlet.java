package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ImageItem;
import Dao.ImageDaoImpl;
import utils.Tool_PathEdit;

/**
 * Servlet implementation class Testservlet
 */
@WebServlet("/Testservlet")
public class Testservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Testservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Enter doGet of TestServlet");
		
		ImageDaoImpl imageDato = new ImageDaoImpl();
		
		//
		ArrayList<ImageItem> list = (ArrayList<ImageItem>) Tool_PathEdit.editImageListToOriginalImagePath(imageDato.getAllImageItems());
	
	
		for(ImageItem item : list){
			System.out.println("Start priting originalImagePath");
			System.out.println("Item id: "+item.getId_Image()+ " path: "+item.getPath());
		}
		
		list = (ArrayList<ImageItem>) Tool_PathEdit.editImageListToThumbnailImagePath(imageDato.getAllImageItems());
		
		
		for(ImageItem item : list){
			System.out.println("Start priting ThumbnailImagePath");
			System.out.println("Item id: "+item.getId_Image()+ " path: "+item.getPath());
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}

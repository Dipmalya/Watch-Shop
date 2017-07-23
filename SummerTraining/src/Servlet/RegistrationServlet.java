package Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Client;
import DAO.DataAccessObject;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
@MultipartConfig
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Client c = new Client();
		c.setEmail(request.getParameter("cid"));
		c.setPassword(request.getParameter("pwd"));
		c.setFirstName(request.getParameter("fn"));
		c.setLastName(request.getParameter("ln"));
		
		DAO.DataAccessObject dao = DAO.DataAccessObject.getInstance();
		
		if (dao.insert(c))
			System.out.println("Client inserted successfully");
		else
			System.out.println("Client insertion unsuccessful");
//		System.out.println(c.getPassword());
	}

}

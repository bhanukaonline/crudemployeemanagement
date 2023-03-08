package net.javaguides.usermanagement.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import net.javaguides.usermanagement.dao.UserDAO;
import net.javaguides.usermanagement.model.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
    	this.userDAO = new UserDAO();
        
    }
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		this.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String action = getServletPath();
		
		switch (action) {
		case "/new":
			showNewForm(request, response);
			
			break;
		case "/insert":
			
			try {
				insertUser(request,response);
				
			}
			catch(SQLException e) {
				// 1000 Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
			
		case"/delete":
			try {
				deleteUser(request,response);
				
			}
			catch(SQLException e) {
				// 1000 Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		case "/edit":
			try {
				showEditForm(request,response);
			}
			catch(SQLException e) {
				// 1000 Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		case "/update":
			try {
				updateUser(request,response);
			}
			catch(SQLException e) {
				// 1000 Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			// handle list
			try {
			listUser(request,response);
		}
		catch(SQLException e) {
			// 1000 Auto-generated catch block
			e.printStackTrace();
		}
			
			
			break;
		}
		
	}
	private String getServletPath() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void listUser(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException, ServletException {
		List<User> listUser = userDAO.selectAllUser();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list-jsp");
		dispatcher.forward(request, response);
	}
	
	private void updateUser(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException{
		int NIC = Integer.parseInt(request.getParameter("NIC"));
		String name = request.getParameter("name");
		String Department = request.getParameter("Department");
		String Designation = request.getParameter("Designation");
		String date = request.getParameter("date");
		
		
		User navin = new User (NIC, name, Department, Designation, date);
		userDAO.updateUser(navin);
		response.sendRedirect("list");
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException {
		int NIC = Integer.parseInt(request.getParameter("NIC"));
		userDAO.deleteUser(NIC);
		response.sendRedirect("list");
	}
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
	 	throws SQLException, ServletException, IOException{
		int NIC = Integer.parseInt(request.getParameter("NIC"));
		User exitingUser = userDAO.selectUser(NIC);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form-jsp");
		Object existingUser = null;
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);
		
	}
	
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form-jsp");
		dispatcher.forward(request, response);
		
	}
	
	private void insertUser(HttpServletRequest request, HttpServletResponse response)
		throws SQLException, IOException{
		String name = request.getParameter("name");
		String Department = request.getParameter("Department");
		String Designation = request.getParameter("Designation");
		String date = request.getParameter("date");
		User newUser = new User(name,Department,Designation,date);

		userDAO.insertUser(newUser);
		//HttpServletResponse response;
		response.sendRedirect("list");
	}
}

package user.management.system.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.management.system.dao.DatabaseConnector;
import user.management.system.model.User;

/**
 * Servlet implementation class RegistrationPageServlet
 */
@WebServlet("/RegistrationPageServlet")
public class RegistrationPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DatabaseConnector connector;
	private Connection conn;
	
	@Override
	public void destroy() {
		connector.close();
	}

	@Override
	public void init() throws ServletException {
		connector = DatabaseConnector.getInstance();
		conn = connector.getConnection();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("registration.html");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User.getLastUserIdFromDB(conn);
		User user = new User();
		int id = user.getId();
		user.setId(id);
		
		if(request.getParameter("username").trim() == "" ||
				request.getParameter("first_name").trim() == "" ||
				request.getParameter("last_name").trim() == "" ||
				request.getParameter("email").trim() == "" ||
				request.getParameter("password").trim() == "" ||
				!request.getParameter("password").equals(request.getParameter("confirm_password"))) {
			response.sendRedirect("loginpage.html");
		} else {
			if(User.getAllUserName(conn) == null || !User.getAllUserName(conn).contains(request.getParameter("username")))
				user.setUserName(request.getParameter("username"));
			
			user.setFirstName(request.getParameter("first_name"));
			user.setLastName(request.getParameter("last_name"));
			user.setEmail(request.getParameter("email"));
			user.setAddress(request.getParameter("address"));
			user.setPhoneNumber(request.getParameter("phone_number"));
			user.setBirthDate(request.getParameter("birth_date"));
			user.setPassword(request.getParameter("password"));
		
			try {
				Statement stmt = conn.createStatement();
				stmt.execute(user.getInsertStatement());
				response.sendRedirect("index.html");
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}	
	}
}

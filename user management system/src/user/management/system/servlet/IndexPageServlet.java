package user.management.system.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.management.system.dao.DatabaseConnector;
import user.management.system.model.User;

/**
 * Servlet implementation class IndexPageServlet
 */
@WebServlet("/IndexPageServlet")
public class IndexPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<User> users;
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
		RequestDispatcher rd = request.getRequestDispatcher("index.html");
		rd.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		users = User.getAllUsers(conn);
		String firstName = User.getUserFirstName(userName, conn);
		
		request.setAttribute("users", users);
		request.setAttribute("firstName", firstName);

		if(User.getAllUserName(conn).contains(userName) && User.getUserPassword(userName, conn).equals(password)) {
			RequestDispatcher rd = request.getRequestDispatcher("loginpage.jsp");
			rd.forward(request, response);
		} else {
			response.sendRedirect("login_error_page.html");
		}
	}
}

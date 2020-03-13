package user.management.system.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.management.system.dao.DatabaseConnector;
import user.management.system.model.User;

/**
 * Servlet implementation class LoginPageSevlet
 */
@WebServlet("/LoginPageSevlet")
public class LoginPageSevlet extends HttpServlet {
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
		
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}

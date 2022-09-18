package salagiochi;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Home
 */
//@WebServlet("/Home")
public class Home extends HttpServlet implements MyWebpage{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println("<html>");
		MyWebpage.printHeader(out, new String("Home"));
		
		out.println("<body>");
		out.println("<h1>HomePage!!!</h1>");
		
		out.println("<ul>");
		out.println("<li><a href=\"./Prenotazioni\">Prenotazioni</a></li>");
		out.println("<li><a href=\"./Contatti\">Contatti</a></li>");
		//out.println("<li><a href=\"./Admin\">Admin</a></li>");
		out.println("</ul>");
		
		
		
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

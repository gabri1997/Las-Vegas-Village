package salagiochi;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class RegistrazioneUtente
 */
@WebServlet("/LoginUtente")
public class LoginUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String JDBCDriverSQLite = "org.sqlite.JDBC";
	public static final String JDBCURLSQLite = "jdbc:sqlite:C:\\Users\\Gabriele Saitama\\eclipse-workspace\\DB\\Vegas.db";
	protected DBManager2 db = new DBManager2(DBManager2.JDBCDriverSQLite, DBManager2.JDBCURLSQLite);
	protected Statement statement;
	protected Connection connection;
       
    /**
     * @throws ClassNotFoundException 
     * @throws SQLException 
     * @see HttpServlet#HttpServlet()
     */
    public LoginUtente() throws ClassNotFoundException, SQLException {
        super();
        // TODO Auto-generated constructor stub
        Class.forName(JDBCDriverSQLite);
        connection = DriverManager.getConnection(JDBCURLSQLite);
		statement = connection.createStatement();
		statement.setQueryTimeout(30); 	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		String url = request.getRequestURL().toString();
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String go = request.getParameter("go");
	
		
		out.println("<html>");
		MyWebpage.printHeader(out, new String("Login Utente"));
		out.println("<head>");
		
		out.println("<!DOCTYPE html>");
		
		  out.println("<meta charset=\"utf-8\">"); 
		  
		  out.println("<style>");     
		 // out.println("<link href=\"C:\\Users\\Gabriele Saitama\\eclipse-workspace\\LasVegasVillage\\WebContent\\html\\style.css\" rel=\"stylesheet\" type=\"text/css\">");
		  out.println("body {");
		  	//out.println("background: #3b3b35;");
		  out.println("background-image: url(file:///C:/Users/Gabriele%20Saitama/eclipse-workspace/LasVegasVillage/WebContent/html/salagiochi.jpg);");
		  	out.println("font-family : San Serif;");
		  	out.println("font-color: #3f104c");
		  out.println("}");
		  
		  out.println(".h1 {");       
		  out.println("position: absolute;");
		  out.println("top: 20%;");
		  out.println("left: 30%;");
		  out.println("color: black;");
		  out.println("background-color:rgba(255,255,255,0.7);");
		  out.println("width: 500px;");
		  out.println("border: 1px solid black;");
		  out.println ("border-radius : 5px;");
		  out.println("padding: 6px;");
		  out.println("font-family : San Serif;");
		  out.println("text-align: center;");
		  out.println("}");    
		  
		  out.println("input {");
	

		  	out.println("width: 30%;");
		  	out.println("padding: 6px;");
		  	out.println ("border: 2px #2a2927;");
		  	out.println ("border-radius: 5px;");
		  	out.println ("margin-top: 2px;");
		  	out.println ("margin-bottom: 2px;");
		  	out.println ("resize: vertical;");
		  	out.println ("COLOR : black; ");
		    out.println ("font : amaranth;");
		  
		  out.println("}");
		  
		  //BOTTONE SUBMIT
		  out.print("input[type=submit]{");
		  	out.print ("background-color: black;");
		  	out.print ("color: white;");
		  	out.print ("padding: 12px 20px;");
		  	out.print ("border: none;");
		  out.print ("border-radius : 5px;");
		  out.println ("font : amaranth;");
			  
		  out.print ("}");

			/*COLORE DI SUBMIT*/
		  out.print ("input[type=submit]:hover {");
		  out.print ("background-color: #14b206;");
		  	out.print ("border-radius = 5px;");
		  out.print ("}");
		  
		  
		  out.println("</style>");
		 
		
		out.println("</head>");
		out.println("<body>");
		
		out.println("<div class=\"h1\">");
		out.println("<h1><em>Login Utente</em></h1>");

		
		PrintForm (out,url,name,password,email);
		
		
		if (go != null) {
			
			System.out.println("Questo è il valore di go dopo:");
			System.out.println(go);
			
	
		int n = eseguiControlloStringa(name,password,email); 
		
		if (n == 1 && email != "" && password != "" && name !="" ) {
		
			System.out.println("Corrispondenza trovata");
			System.out.println("Apriamo la nuova pagina di conferma");
			response.sendRedirect("http://localhost:8080/LasVegasVillage/html/HomeUtente.html");
			
		}
		else {
			response.sendRedirect("http://localhost:8080/LasVegasVillage/html/ErroreLoginUtente.html");
		}
		}
		out.println("</body></html>");
		
		
	}
	
	private int eseguiControlloStringa(String name,String password, String email) {
		// TODO Auto-generated method stub
		
		int corrispondenze=0;
		String controllo=String.format("SELECT * FROM Utenti WHERE name = '%s' AND password = '%s' AND email = '%s';",name,password,email);
		System.out.println(controllo);
		ResultSet tmp1 = null;
		try {
			tmp1 = statement.executeQuery(controllo);
			System.out.println("Questo è il result set");
			System.out.println(tmp1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (tmp1.next()) {
			String str1=String.format("%s",tmp1.getString("name"));
			System.out.println(name);
			String str2=String.format("%s",tmp1.getString("password"));
			System.out.println(password);
			String str3=String.format("%s",tmp1.getString("email"));
			System.out.println(email);
			if (str1.equals(name) && str2.equals(password) && str3.equals(email)) {
				corrispondenze = 1;
			}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Questo è il valore della corrispondenza");
		System.out.println(corrispondenze);
		return corrispondenze;
		
	}

	
protected void PrintForm (PrintWriter out, String url, String name, String password, String email) {
		
		out.println("<form method=GET action=\""+url+"\">");
		
		
		out.print("<label for=\"Nome Utente\">Nome Utente</label>");
		out.println("</br>");
        out.print("<input type=text name=name");
        if (name != null){out.print(" value=\""+name+"\"");}
        out.println(" />");
        out.println("</br>");

        out.print("<label for=\"Password\">Password</label>");
		out.println("</br>");
        out.print("<input type=text name=password");
        if (password != null){out.print(" value=\""+password+"\"");}
        out.println(" />");
        out.println("</br>");


        out.print("<label for=\"Email\">Email</label>");
		out.println("</br>");
        out.print("<input type=text name=email");
        if (email != null){out.print(" value=\""+email+"\"");}
        out.println(" />");
        out.println("</br>");
        
        out.println("<input type=submit name=go value=\"Login\"></input>"); 
        
        out.println("</form>"); 
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

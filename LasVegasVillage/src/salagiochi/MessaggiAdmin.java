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
 * Servlet implementation class MessaggiAdmin
 */
@WebServlet("/MessaggiAdmin")
public class MessaggiAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String JDBCDriverSQLite = "org.sqlite.JDBC";
	public static final String JDBCURLSQLite = "jdbc:sqlite:C:\\Users\\Gabriele Saitama\\eclipse-workspace\\DB\\Vegas.db";
	protected DBManager2 db = new DBManager2(DBManager2.JDBCDriverSQLite, DBManager2.JDBCURLSQLite);
	protected Statement statement;
	protected Connection connection;
	int numero_messaggi =0;
    /**
     * @throws ClassNotFoundException 
     * @throws SQLException 
     * @see HttpServlet#HttpServlet()
     */
    public MessaggiAdmin() throws ClassNotFoundException, SQLException {
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
		String rispondi = request.getParameter("Rispondi");
		
	
		
		out.println("<html>");
		MyWebpage.printHeader(out, new String("Messaggi"));
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
		  	out.println("color:#190516;");
		  	//out.println("background-color: #926e9c;");
		  	out.println("background-color:rgba(255,255,255,0.7);");
		  	out.println("border: 1px solid black;");
		  	out.println ("border-radius : 5px;");
		  	out.println("padding: 6px;");
		  	out.println("font-family : San Serif;");
		  	out.println("text-align: center;");
		  out.println("}");    
		  
		  out.println("input {");
	
		  	out.println("width: 18%;");
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
		  	out.print ("background-color: #227f1b;");
		  	out.print ("border-radius = 5px;");
		  out.print ("}");
		  
		  
		  out.println("</style>");
		 
		
		out.println("</head>");
		out.println("<body>");
		
		out.println("<div class=\"h1\">");
		out.println("<h1><em>Messaggi</em></h1>");
		//out.println("</div>");
				
			printMessaggi(out, url);
			System.out.println("Dovrei stampare i messaggi");	
//		
//		if(numero_messaggi!=0) {
//			out.println("<input type=submit name=Rispondi value=Rispondi></input>"); 
//			System.out.print("Il valore di rispondi ? %s:");
//			System.out.print(rispondi);
//			}
//		
		if(rispondi != null && numero_messaggi != 0) {
			System.out.println("rispondi ? stato premuto");
			response.sendRedirect("http://localhost:8080/LasVegasVillage/ScriviMessaggio");
	
		}
			
		out.println("</body></html>");
		
	}
	private void printMessaggi(PrintWriter out, String url) {
		// TODO Auto-generated method stub
		String query=String.format("SELECT M.MailMittente, M.NomeMittente, M.Data, M.Tipo, M.Testo  FROM MessaggiAdmin M  ORDER BY M.MailMittente;");


		ResultSet tmp = null;

		String last_name = "";
		try {
			tmp = statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		try {
			while (tmp.next()) {
				numero_messaggi=1;
				String name = tmp.getString("MailMittente");
				System.out.println(name);
				if(!last_name.equals(name)) {
					if(!last_name.equals(""))
					out.println("</table>");
					System.out.println("<h3>"+name+"</h3>");
					out.println("<table>");
					
					out.println("<div class=\"h1\">");
					out.println("<h4><em>"+name+"</em></h4>");
					
					out.println("<tr><td><em>Mail Mittente&nbsp;&nbsp;&nbsp;</em></td><td><em>Nome Mittente&nbsp;&nbsp;&nbsp;</em></td><td><em>Data&nbsp;&nbsp;&nbsp;</em></td><td><em>Tipologia&nbsp;</td></em><td><em>Testo&nbsp;</td></em></tr>");
					out.println("</div");
					out.println("/br");
					
				}
				
				last_name=name;
				String str=String.format("<tr> <td>%s</td> <td>%s</td> <td>%s</td> <td>%s</td> <td>%s</td> </tr>", tmp.getString("MailMittente"),tmp.getString("NomeMittente"),tmp.getString("Data"),tmp.getString("Tipo"),tmp.getString("Testo"));
				System.out.println(str);
				//stampa nuova riga
				out.println(str);
			}
		
		}
		
			
			
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			out.println("</table>");
		}
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

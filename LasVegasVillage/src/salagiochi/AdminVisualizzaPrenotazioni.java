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
 * Servlet implementation class AdminVisualizzaPrenotazioni
 */
@WebServlet("/AdminVisualizzaPrenotazioni")
public class AdminVisualizzaPrenotazioni extends HttpServlet {
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
    public AdminVisualizzaPrenotazioni() throws ClassNotFoundException, SQLException {
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
		String Email = request.getParameter("Email");
		String orario = request.getParameter("orario");
		String persone = request.getParameter("persone");
		String data = request.getParameter("data");
		String pista = request.getParameter("pista");
		String tipo = request.getParameter("tipo");
		String tavolo = request.getParameter("tavolo");
		String go = request.getParameter("go");

		
		out.println("<html>");
		MyWebpage.printHeader(out, new String("Gestione Prenotazioni"));
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
		  
		 /*OPTION per il tipo*/
		 out.print("option:hover{");
		 out.print("background-color:#FFFF99;");
		 out.print("}");
		 
		 out.print("select{");
		 out.print("width:100px");
		 out.print("}");
		 
		 out.print("option:checked {");
		 out.print("background-color:#FF0000;");
		 out.print("}");
		  

		 out.println("</style>");
		 
		
		out.println("</head>");
		out.println("<body>");
		
		out.println("<div class=\"h1\">");
		out.println("<h1><em>Gestisci prenotazioni</em></h1>");
		
		PrintForm (out,url,persone,name,Email, data,pista,tipo,orario,tavolo);
		
		if (go != null) {
			
			int val = eliminaPrenotazione(out,url,persone,name,Email,data,pista,tipo,orario,tavolo);
			System.out.println("Questo è il valore di val : ");
			System.out.print(val);
			if (val == 1) {
				System.out.println("cancellazione andata a buon fine");
				System.out.println("Apriamo la nuova pagina di conferma");
				response.sendRedirect("http://localhost:8080/LasVegasVillage/html/ConfermaCancellazioneP.html");
			}
			
		}
		
		printDataGiochi(out, url);
		out.println("</body></html>");
	}

	private int eliminaPrenotazione(PrintWriter out, String url, String persone,String name, String email, String data, String pista, String tipo, String orario, String tavolo) {
		// TODO Auto-generated method stub
		int tmp = 0;
		if (tipo.contentEquals("bowling")) {
			System.out.println("Dovrei cancellare");
			String query = String.format("DELETE FROM Prenotazioni WHERE People = '%s' AND Nome = '%s' AND Email = '%s' AND Data = '%s' AND Tipo = '%s' AND Orario = '%s' AND Pista = '%s';",persone,name,email,data,tipo,orario,pista);
		
			try {
				tmp = statement.executeUpdate(query);
				System.out.print(tmp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (tipo.contentEquals("biliardo") || tipo.contentEquals("biliardino") || tipo.equals("ping-pong")) {
			String query = String.format("DELETE FROM Prenotazioni WHERE People = '%s' AND Nome = '%s' AND Email = '%s' AND Data = '%s' AND Tipo = '%s' AND Orario = '%s' AND Tavolo = '%s';",persone,name,email,data,tipo,orario,tavolo);
			
			
				try {
					tmp = statement.executeUpdate(query);
					System.out.print(tmp);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		if (tipo.contentEquals("lasergame")) {
			
			String query = String.format("DELETE FROM Prenotazioni WHERE People = '%s' AND Nome = '%s' AND Email = '%s' AND Data = '%s' AND Tipo = '%s' AND Orario = '%s';",persone,name,email,data,tipo,orario);
			
				try {
					tmp = statement.executeUpdate(query);
					System.out.print(tmp);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return tmp;
	}

	private void printDataGiochi(PrintWriter out, String url) {		
		String query = "SELECT G.Nome as Nome_Gioco, P.ID, P.People, P.Email, P.Nome, P.Orario, P.Data, P.Pista, P.Tavolo, P.Tipo, P.Disponibile " + 
				"FROM Prenotazioni P " + 
				"JOIN Giochi G " + 
				"ON P.ID = G.ID " + 
				"ORDER BY G.Nome;" +
				"ORDER BY P.Data;";
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
				String name = tmp.getString("Nome_Gioco");
				System.out.println("QUESTO NOME:");
				System.out.println(name);
				if(!last_name.equals(name)) {
					if(!last_name.equals(""))
					out.println("</table>");
					System.out.println("<h3>"+name+"</h3>");
					out.println("<table>");
					
					out.println("<div class=\"h1\">");
					out.println("<h3><em>"+name+"</em></h3>");
					
				
					if(name.contentEquals("Laser Game")){
						out.println("<tr><td><em>PERSONE&nbsp;&nbsp;&nbsp;</em></td><td><em>EMAIL&nbsp;&nbsp;&nbsp;</em></td><td><em>NOME&nbsp;&nbsp;&nbsp;</em></td><td><em>ORARIO&nbsp;&nbsp;&nbsp;</em></td><td><em>DATA&nbsp;&nbsp;&nbsp;</em></td><td><em>ATTRAZIONE&nbsp;&nbsp;&nbsp;</em></td><td><em>DISPONIBILITA'&nbsp;&nbsp;&nbsp;</em></td></tr>");
						out.println("<div style=\"padding:6px; margin:10px; border-radius:8px; background-color:#ffd5c6\"><em>Per data e ora, max 10 persone</em></div>");
					}
					if (name.contentEquals("Bowling")) {
						System.out.println("Sono il Bowling!!!!");
						out.println("<tr><td><em>PERSONE&nbsp;&nbsp;&nbsp;</em></td><td><em>EMAIL&nbsp;&nbsp;&nbsp;</em></td><td><em>NOME&nbsp;&nbsp;&nbsp;</em></td><td><em>ORARIO&nbsp;&nbsp;&nbsp;</em></td><td><em>DATA&nbsp;&nbsp;&nbsp;</em></td><td><em>ATTRAZIONE&nbsp;&nbsp;&nbsp;</em></td><td><em>PISTA&nbsp;&nbsp;&nbsp;</em></td><td><em>DISPONIBILITA'&nbsp;&nbsp;&nbsp;</em></td></tr>");
						out.println("<div style=\"padding:6px; margin:10px; border-radius:8px; background-color:#ffd5c6\"><em>Per data e ora, max 4 persone a pista</em></div>");
					}
					if (name.contentEquals("Biliardo") || name.contentEquals("Ping Pong") || name.contentEquals("Biliardino")) {
						out.println("<tr><td><em>PERSONE&nbsp;&nbsp;&nbsp;</em></td><td><em>EMAIL&nbsp;&nbsp;&nbsp;</em></td><td><em>NOME&nbsp;&nbsp;&nbsp;</em></td><td><em>ORARIO&nbsp;&nbsp;&nbsp;</em></td><td><em>DATA&nbsp;&nbsp;&nbsp;</em></td><td><em>ATTRAZIONE&nbsp;&nbsp;&nbsp;</em></td><td><em>TAVOLO&nbsp;&nbsp;&nbsp;</em></td><td><em>DISPONIBILITA'&nbsp;&nbsp;&nbsp;</em></td></tr>");
						out.println("<div style=\"padding:6px; margin:10px; border-radius:8px; background-color:#ffd5c6\"><em>Per data e ora, max 4 persone per ogni tavolo</em></div>");
						}
					out.println("</div");
					
					
				}
				last_name=name;
				if (name.equals("Bowling")) {
					String str=String.format("<tr> <td>%d</td> <td>%s</td> <td>%s</td> <td>%s</td> <td>%s</td> <td>%s</td> <td>%d</td> <td>%s</td> </tr>", tmp.getInt("People"),tmp.getString("Email"),tmp.getString("Nome"),tmp.getString("Orario"),tmp.getString("Data"),tmp.getString("Tipo"),tmp.getInt("Pista"),tmp.getString("Disponibile") );
					System.out.println(str);
					//stampa nuova riga
					out.println(str);
				}
				if (name.equals("Laser Game")) {
					String strL=String.format("<tr> <td>%d</td> <td>%s</td> <td>%s</td> <td>%s</td> <td>%s</td> <td>%s</td> <td>%s</td> </tr>", tmp.getInt("People"),tmp.getString("Email"),tmp.getString("Nome"),tmp.getString("Orario"),tmp.getString("Data"),tmp.getString("Tipo"),tmp.getString("Disponibile"));
					out.println(strL);
				}
		
				if (name.contentEquals("Biliardo") || name.contentEquals("Ping Pong") || name.contentEquals("Biliardino")) {
					
					String strA=String.format("<tr> <td>%d</td> <td>%s</td> <td>%s</td> <td>%s</td> <td>%s</td> <td>%s</td> <td>%d</td> <td>%s</td> </tr>", tmp.getInt("People"),tmp.getString("Email"),tmp.getString("Nome"),tmp.getString("Orario"),tmp.getString("Data"),tmp.getString("Tipo"),tmp.getInt("Tavolo"),tmp.getString("Disponibile"));
					out.println(strA);
					}
				}	
			
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			out.println("</table>");
		}
	}
protected void PrintForm (PrintWriter out, String url, String persone,String name, String Email, String orario,String data,String tipo, String pista,String tavolo) {
		
		out.println("<form method=GET action=\""+url+"\">");
		
		
		out.print("<label for=\"lname\">Numero persone</label>");
		out.println("</br>");
        out.print("<input type=text name=persone");
        if (persone != null){out.print(" value=\""+persone+"\"");}
        out.println(" />");
        out.println("</br>");

        out.print("<label for=\"lname\">Nome prenotante</label>");
		out.println("</br>");
        out.print("<input type=text name=name");
        if (name != null){out.print(" value=\""+name+"\"");}
        out.println(" />");
        out.println("</br>");
        
        out.print("<label for=\"lname\">Contatto Email</label>");
        out.println("</br>");
 		out.print("<input type=text name=Email");
        if (Email != null){out.print(" value=\""+Email+"\"");}
        out.println(" />");
        out.println("</br>");
       
        out.print("<label for=\"lname\">Orario</label>");
        out.println("</br>");
        out.print("<select name=orario style=width:100px\">\r\n" + 
        		 
				"    <option value=\"19.00\">19.00</option>\r\n" +
        		"    <option value=\"20.00\">20.00</option>\r\n" + 
        		"    <option value=\"21.00\">21.00</option>\r\n" + 
        		"    <option value=\"22.00\">22.00</option>\r\n" + 
        		"    <option value=\"23.00\">23.00</option>\r\n" +       		
        		"</select>");
        if (orario != null){out.print(" value=\""+orario+"\"");}
        out.println("</br>");
       
        out.print("<label for=\"lname\">Giorno</label>");
        out.println("</br>");
        out.print("<select name=data style=width:100px\">\r\n" + 
        		 
				"    <option value=\"Lunedi\">Lunedi</option>\r\n" +
        		"    <option value=\"Martedi\">Martedi</option>\r\n" + 
        		"    <option value=\"Mercoledi\">Mercoledi</option>\r\n" + 
        		"    <option value=\"Giovedi\">Giovedi</option>\r\n" + 
        		"    <option value=\"Venerdi\">Venerdi</option>\r\n" +  
        		"    <option value=\"Sabato\">Sabato</option>\r\n" + 
        		
        		"</select>");
        if (data != null){out.print(" value=\""+data+"\"");}
        out.println("</br>");
        
        /*select attrazioni*/
        out.print("<label for=\"lname\">Attrazione</label>");
        out.println("</br>");
        out.print("<select name=tipo style=width:100px\">\r\n" + 
        		 
        		"    <option value=\"bowling\">Bowling</option>\r\n" + 
        		"    <option value=\"ping-pong\">Ping Pong</option>\r\n" + 
        		"    <option value=\"biliardino\">Biliardino</option>\r\n" + 
        		"    <option value=\"biliardo\">Biliardo</option>\r\n" + 
        		"    <option value=\"lasergame\">Laser Game</option>\r\n" + 
        		
        		"</select>");
        if (tipo != null){out.print(" value=\""+tipo+"\"");}
        out.println("</br>");
        
    
        out.print("<label for=\"lname\">Pista</label>");
        out.println("</br>");
        out.print("<select name=pista style=width:100px\">\r\n" + 
        		 
		
        		"    <option value=\"1\">1</option>\r\n" + 
        		"    <option value=\"2\">2</option>\r\n" + 
        		"    <option value=\"3\">3</option>\r\n" + 
        		"    <option value=\"4\">4</option>\r\n" + 
        		
        		"</select>");
        if (pista != null){out.print(" value=\""+pista+"\"");}
        out.println("</br>");
     
        /*select tavolo*/
        out.print("<label for=\"lname\">Tavolo</label>");
        out.println("</br>");
        out.print("<select name=tavolo style=width:100px\">\r\n" + 
        		 
				"    <option value=\"1\">1</option>\r\n" +
        		"    <option value=\"2\">2</option>\r\n" + 
        		"    <option value=\"3\">3</option>\r\n" + 
        		"    <option value=\"4\">4</option>\r\n" + 	
        		"</select>");
        if (data != null){out.print(" value=\""+data+"\"");}
        out.println("</br>");
        
        
        out.println("<input type=submit name=go value=\"Elimina\"></input>"); 
        
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

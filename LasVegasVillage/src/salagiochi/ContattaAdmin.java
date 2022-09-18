package salagiochi;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContattaAdmin
 */
@WebServlet("/ContattaAdmin")
public class ContattaAdmin extends HttpServlet {
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
    public ContattaAdmin() throws ClassNotFoundException, SQLException {
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
		String MailMittente = request.getParameter("MailMittente");
		String NomeMittente = request.getParameter("NomeMittente");
		String Data = request.getParameter("Data");
		String Testo = request.getParameter("Testo");
		String Tipo = request.getParameter("Tipo");
		String go = request.getParameter("go");
		
		
		
		
		
		out.println("<html>");
		MyWebpage.printHeader(out, new String("Scrivi Messaggio"));
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
		  out.println("background-color:rgba(185,185,151,0.7);");
		  out.println("width: 500px;");
		
		  out.println("border: 1px solid black;");
		  out.println ("border-radius : 5px;");
		  out.println("padding: 6px;");
		  out.println("font-family : San Serif;");
		  out.println("text-align: center;");
		  out.println("}");    
		  
		  out.println("input {");
	

		out.println(" width: 80%;");
		
		out.println(" padding: 12px;");
		out.println(" border: 4px #2a2927;");
		out.println(" border-radius: 5px;");
		out.println(" margin-top: 6px;");
		out.println(" margin-bottom: 16px;");
		out.println("resize: vertical;");
		out.println("COLOR : black; ");
		out.println(" font : amaranth;");
		  
		  
		  out.println("}");
		  
		  out.println("textarea{");
			

			out.println(" width: 80%;");
			out.println("height:100px");
			out.println(" padding: 28px;");
			out.println(" border: 4px #2a2927;");
			out.println(" border-radius: 5px;");
			out.println(" margin-top: 6px;");
			out.println(" margin-bottom: 16px;");
			out.println("resize: vertical;");
			out.println("COLOR : black; ");
			out.println(" font : amaranth;");
			  
			  
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
		out.println("<h1><em>Contatta Admin</em></h1>");
		
		
		PrintForm (out,url,MailMittente,NomeMittente,Data,Tipo,Testo);
		
		
		if (go != null) {
			
			System.out.println("Questo è il valore di go dopo:");
			System.out.println(go);
			
		String insert = inserimentoDatabase(MailMittente, NomeMittente, Data, Tipo, Testo);
		System.out.println("Questa è la stringa da inserire nel database");
		System.out.println(insert);
		
		int n = eseguiControlloStringa(MailMittente,NomeMittente, Data, Tipo, Testo); 
		if (n == 1) {
			System.out.println("NON CI SONO PROBLEMI, PUOI CONTATTARE L'ADMIN");
		try {
				db.executeUpdate(insert);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Inserimento andato a buon fine");
			System.out.println("Apriamo la nuova pagina di conferma");
			response.sendRedirect("http://localhost:8080/LasVegasVillage/html/MessaggioVersoAdminCorr.html");
			
		}
		else {
			response.sendRedirect("http://localhost:8080/LasVegasVillage/html/MessaggioNonCorr.html");
		}
		}
		out.println("</body></html>");
		
		
		
	}

	private int eseguiControlloStringa(String MailMittente, String NomeMittente, String Data, String Tipo, String Testo) {
		// TODO Auto-generated method stub
		int valore=0;
		if (MailMittente != "" && Data!="" && NomeMittente!="" && Testo!="" && Tipo != "") {
			valore=1;
		}
		return valore;
	}

	private String inserimentoDatabase(String MailMittente, String NomeMittente, String Data, String Tipo, String Testo) {
		// TODO Auto-generated method stub
	System.out.println("Eccomi sono entrato!");
		
	    //FACCIO LA INSERT
		String datastring=String.format("INSERT INTO MessaggiAdmin (MailMittente,NomeMittente,Data,Tipo,Testo) VALUES" + 
		"('%s','%s','%s','%s','%s');",MailMittente,NomeMittente,Data,Tipo,Testo);
		return datastring;
	
	}

	private void PrintForm(PrintWriter out, String url, String MailMittente, String NomeMittente, String Data,
			String Tipo,String Testo) {
		// TODO Auto-generated method stub
		out.println("<form method=GET action=\""+url+"\">");
		
		out.println("<div style=\"padding:10px; margin:30px; border-radius:8px; background-color:#ffd5c6\"><em>Per cancellare una prenotazione o per qualsiasi problema invia un messaggio all'Admin compilando tutti i campi sottostanti</em></div>");
		
		
		   out.print("<label for=\"lname\">Tipologia Messaggio</label>");
	        out.println("</br>");
	        out.print("<select name=Tipo style=width:100px\">\r\n" + 
	        		 
					"    <option value=\"Richiesta Cancellazione\">Richiesta Cancellazione</option>\r\n" +
	        		"    <option value=\"Domanda per informazioni\">Domanda per informazioni</option>\r\n" + 
	        		"    <option value=\"Altro\">Altro</option>\r\n" + 
	        		"</select>");
	        if (Tipo != null){out.print(" value=\""+Tipo+"\"");}
	        out.println("</br>");
		
		
		out.print("<label for=\"MailMittente\">Mail Mittente</label>");
		out.println("</br>");
        out.print("<input type=text placeholder=\"GabrieleRosati@gmail.com\" name=MailMittente");
        if (MailMittente != null){out.print(" value=\""+MailMittente+"\"");}
        out.println(" />");
        out.println("</br>");
        
        out.print("<label for=\"NomeMittente\">Nome Mittente</label>");
		out.println("</br>");
        out.print("<input type=text placeholder=\"Mario\" name=NomeMittente");
        if (NomeMittente != null){out.print(" value=\""+NomeMittente+"\"");}
        out.println(" />");
        out.println("</br>");
        
        out.print("<label for=\"Data\">Data</label>");
		out.println("</br>");
        out.print("<input type=text placeholder=\"--\\--\\--\" name=Data");
        if (Data != null){out.print(" value=\""+Data+"\"");}
        out.println(" />");
        out.println("</br>");
           
        out.print("<label for=\"Testo\">Inserisci testo</label>");
        out.println("</br>");
        
        out.print ("<textarea  name=Testo placeholder=Write something.. style=height:100px>");
        out.print("</textarea>");
        
        if (Testo != null){out.print(" value=\""+Testo+"\"");}
 
        out.println("</br>");
        
        out.println("<input type=submit name=go value=\"Invia\"></input>"); 
        
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

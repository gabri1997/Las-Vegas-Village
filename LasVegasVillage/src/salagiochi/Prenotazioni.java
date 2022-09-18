package salagiochi;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.catalina.connector.Response;

/**
 * Servlet implementation class Prenotazioni
 */
//@WebServlet("/Prenotazioni")
public class Prenotazioni extends HttpServlet implements MyWebpage{
	private static final long serialVersionUID = 1L;
	public static final String JDBCDriverSQLite = "org.sqlite.JDBC";
	public static final String JDBCURLSQLite = "jdbc:sqlite:C:\\Users\\Gabriele Saitama\\eclipse-workspace\\DB\\Vegas.db";
	protected DBManager2 db = new DBManager2(DBManager2.JDBCDriverSQLite, DBManager2.JDBCURLSQLite);
	protected Statement statement;
	protected Connection connection;
	int ID;
	int num;
    /**
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @see HttpServlet#HttpServlet()
     */
    public Prenotazioni() throws SQLException, ClassNotFoundException {
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
		String disponibile = "disponibile";
		String go = request.getParameter("go");
		System.out.println("Questo è il valore di go prima:");
		System.out.println(go);
		System.out.println("Numero pista:");
		System.out.println(pista);
		
		out.println("<html>");
		MyWebpage.printHeader(out, new String("Prenotazioni"));
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
		out.println("<h1><em>Prenotazioni</em></h1>");
		//out.println("<h4><em>Se sei disponibile ad essere contattato lascia la tua Email</em></h4>");
		out.println("<hr color=black size=\"0.2\" />");
		out.println("<div style=\"padding:10px; margin:30px; border-radius:8px; background-color:#ffd5c6\"><em>Per cancellare una prenotazione scrivi all'admin, lascia la tua mail se sei disponibile ad essere contattato da altri utenti</em></div>");
		
		PrintForm (out,url,persone,name,Email, data,pista,tipo,orario,tavolo);
		
		if (go != null) {
			
			/*PER PRIMA COSA SISTEMO L'ID*/
			
			if (tipo.contentEquals("bowling")) {
				ID=1;
			}
			if (tipo.contentEquals("lasergame")) {
				ID=2;
			}
			if (tipo.contentEquals("biliardo")) {
				ID=3;
				}
			if (tipo.contentEquals("ping-pong")) {
				ID=4;
				}
			if (tipo.contentEquals("biliardino")) {
				ID=5;
			}
			
		
			int interoPista = Integer.parseInt(pista);
			int interoTavolo = Integer.parseInt(tavolo);
			int interoPersone = Integer.parseInt(persone);
			System.out.println("Questo è il valore di go dopo:");
			System.out.println(go);
			
//		String insert = inserimentoDatabase(ID,interoPersone,Email,name,orario,data,tipo,interoPista,interoTavolo,disponibile);
//		System.out.println("Questa è la stringa da inserire nel database");
//		System.out.println(insert);
		
		//ESEGUO IL CONTROLLO SULLA VALIDITA DELLA INSERT
		
		int ControlloAttrazione = eseguiControllo(data,interoPista,tipo,orario,interoTavolo);
		
		
		//SE IL NUMERO DI PERSONE CALCOLATE SOMMANDO IL NUMERO DI PERSONE PER LA PISTA E LA DATA SPECIFICATA SONO INFERIORI A QUELLE CHE 
		//ISERISCO IO ALLORA POSSO ESEGUIRE LA INSERT E SONO INFERIORI AL NUMERO MASSIMO DI PERSONE CHE PUO OSPITARE UNA PISTA SINGOLA 
		if (tipo.contentEquals("bowling")) {
			/*SISTEMO l'ID*/
			
		if ((interoPersone + ControlloAttrazione) <= 4 && interoPersone <= 4) {
			
			if ((interoPersone + ControlloAttrazione)==4) {
				disponibile="esaurito";
			}else {
				disponibile="disponibile";
			}
			String insert = inserimentoDatabase(ID,interoPersone,Email,name,orario,data,tipo,interoPista,interoTavolo,disponibile);
			System.out.println("Questa è la stringa da inserire nel database");
			System.out.println(insert);
			
			
			System.out.println("Posso eseguire la insert!");
		try {
			db.executeUpdate(insert);
			System.out.println("inserimento per bowling andato a buon fine");
			System.out.println("Apriamo la nuova pagina di conferma");
			response.sendRedirect("http://localhost:8080/LasVegasVillage/html/ConfermaPrenotazione.html");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else {
			System.out.println("Non posso eseguire la insert per bowling, non ci sono più posti disponibili)");
			
		}
		}
		
		
		/////////////////LASER GAME///////////
		
		if (tipo.contentEquals("lasergame")) {
		
			
			if ((interoPersone + ControlloAttrazione) <= 10 && interoPersone <= 10) {
				
				if ((interoPersone + ControlloAttrazione)==10) {
					disponibile="esaurito";
				}else {
					disponibile="disponibile";
				}
				if (Email.contentEquals("")) {
					disponibile="non prenotabile";
				}
				
				String insert = inserimentoDatabase(ID,interoPersone,Email,name,orario,data,tipo,interoPista,interoTavolo,disponibile);
				System.out.println("Questa è la stringa da inserire nel database");
				System.out.println(insert);
				
				System.out.println("Posso eseguire la insert!");
			try {
				db.executeUpdate(insert);
				System.out.println("inserimento per lasergame andato a buon fine");
				System.out.println("Apriamo la nuova pagina di conferma");
				response.sendRedirect("http://localhost:8080/LasVegasVillage/html/ConfermaPrenotazione.html");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}else {
				System.out.println("Non posso eseguire la insert per il lasergame, non ci sono più posti disponibili)");
			}
			}
		//////////////////////PING-PONG BILIARDINO BILIARDO///////////////////
		if (tipo.contentEquals("biliardino") || tipo.contentEquals("ping-pong") || tipo.contentEquals("biliardo")) {
		
			/*devono essere al massimo 4 per tavolo*/
			if ((interoPersone + ControlloAttrazione) <= 4 && interoPersone <= 4) {
				
				if ((interoPersone + ControlloAttrazione)==4) {
					disponibile="esaurito";
				}else {
					disponibile="disponibile";
				}
				
				String insert = inserimentoDatabase(ID,interoPersone,Email,name,orario,data,tipo,interoPista,interoTavolo,disponibile);
				System.out.println("Questa è la stringa da inserire nel database");
				System.out.println(insert);
				
				
				System.out.println("Posso eseguire la insert!");
			try {
				db.executeUpdate(insert);
				System.out.println("inserimento per lasergame andato a buon fine");
				System.out.println("Apriamo la nuova pagina di conferma");
				response.sendRedirect("http://localhost:8080/LasVegasVillage/html/ConfermaPrenotazione.html");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}else {
				System.out.printf("Non posso eseguire la insert per id = %d, non ci sono più posti disponibili",ID);
			
			}
			}
		
		//////////////////////FINE CONTROLLI INSERT PRENOTAZIONI////////////////
		}
		printDataGiochi(out, url);
		
		out.println("<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js\"></script>\n");
		out.println("<script>\n");
        out.println("$('#tipo_form').change(function() {\n");
        out.println("if ($(this).val() == \"bowling\") {\n");
        out.println("$('#pista_form').show();\n");
        out.println("$('#tavolo_form').hide();\n");
        out.println(")}\n");
        out.println("else if ($(this).val() == \"lasergame\"){\n");
        out.println("$('#pista_form').hide();\n");
        out.println("$('#tavolo_form').hide();\n");
        out.println(")}\n");
        out.println("else {\n");
        out.println("$('#pista_form').hide();\n");
        out.println(" $('#tavolo_form').s();\n");
        out.println(")}\n");
        out.println(")});\n");
		out.println("</script>\n");
		out.println("</body></html>\n");
	}
	
	private int eseguiControllo(String data, int interoPista, String tipo, String orario, int interoTavolo) {
		// TODO Auto-generated method stub
		
		//PRIMA DI FARE LA INSERT DEVO FARE IL CONTROLLO SE LA PRENOTAZIONE RISULTA VALIDA
		//PER DATA ORA E CAPIENZA DI PISTA
		
		System.out.println("Eseguo controllo");
		int numeroDiPersoneRSet=0;

		//////////////////////////////////////////////////BOWLING/////////////////////////////////////
		if (tipo.contentEquals("bowling")) {
			System.out.println("Ecco il tipo.content:---->");
			System.out.print(tipo);
			System.out.print("   ");
	
		int varTmp;
		
		String controllo=String.format("SELECT People FROM Prenotazioni WHERE Data = '%s' AND Pista = %d AND Orario = '%s' AND Tipo = '%s';",data,interoPista,orario,tipo);
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
				String str1=String.format("%d",tmp1.getInt("People"));
				varTmp=Integer.parseInt(str1);
				numeroDiPersoneRSet= numeroDiPersoneRSet + varTmp;
				//STAMPO 
				System.out.println("Questo è il result set del numero di persone");
				System.out.println(str1);
			}
		    //STAMPO IL TOTALE
			System.out.println("Questo è il totale:");
			System.out.println(numeroDiPersoneRSet);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
///////////////////////////////////////LASER GAME//////////////////////////////////////////////
		if (tipo.contentEquals("lasergame")) {
			System.out.println("Ecco il tipo.content:---->");
			System.out.print(tipo);
			System.out.print("   ");
	
		int varTmp;
		
		String controllo=String.format("SELECT People FROM Prenotazioni WHERE Data = '%s' AND Orario = '%s' AND Tipo = '%s';",data,orario,tipo);
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
				String str1=String.format("%d",tmp1.getInt("People"));
				varTmp=Integer.parseInt(str1);
				numeroDiPersoneRSet= numeroDiPersoneRSet + varTmp;
				//STAMPO 
				System.out.println("Questo è il result set del numero di persone");
				System.out.println(str1);
			}
		    //STAMPO IL TOTALE
			System.out.println("Questo è il totale:");
			System.out.println(numeroDiPersoneRSet);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
	////////////////////////////////////PING PONG -BILIARDINO-BILIARDO//////////////////////////////////////////////////	
		if (tipo.contentEquals("biliardo") || tipo.contentEquals("ping-pong") ||tipo.contentEquals("biliardino")) {
			System.out.println("Ecco il tipo.content:---->");
			System.out.print(tipo);
			System.out.print("   ");
	
		int varTmp;
		
		String controllo=String.format("SELECT People FROM Prenotazioni WHERE Data = '%s' AND Orario = '%s' AND Tipo = '%s' AND Tavolo = %d;",data,orario,tipo,interoTavolo);
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
				String str1=String.format("%d",tmp1.getInt("People"));
				varTmp=Integer.parseInt(str1);
				numeroDiPersoneRSet= numeroDiPersoneRSet + varTmp;
				//STAMPO 
				System.out.println("Questo è il result set del numero di persone");
				System.out.println(str1);
			}
		    //STAMPO IL TOTALE
			System.out.println("Questo è il totale:");
			System.out.println(numeroDiPersoneRSet);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		return numeroDiPersoneRSet;
		
	}

	private String inserimentoDatabase(int ID,int persone, String Email,String name,String orario, String data,String tipo, int pista, int interoTavolo, String disponibile) {
		// TODO Auto-generated method stub
		
			System.out.println("Eccomi sono entrato!");
			
		    //FACCIO LA INSERT
			String datastring=String.format("INSERT INTO Prenotazioni (ID,People,Email,Nome,Orario,Data,Tipo,Pista,Tavolo) VALUES" + 
			"(%d, %d, '%s','%s','%s','%s','%s', %d, %d);", ID, persone,Email,name, orario,data,tipo,pista,interoTavolo);
			return datastring;
		    }

//////////////////////
//	private void PrintTable(PrintWriter out, String url) {
//		printDataGiochi(out,url);
//	}
///////////////////////
	private void printDataGiochi(PrintWriter out, String url) {		
		String query = "SELECT G.Nome as Nome_Gioco, P.ID, P.People, P.Email, P.Nome, P.Orario, P.Data, P.Pista, P.Tavolo, P.Tipo " + 
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
					String str=String.format("<tr> <td>%d</td> <td>%s</td> <td>%s</td> <td>%s</td> <td>%s</td> <td>%s</td> <td>%d</td>  </tr>", tmp.getInt("People"),tmp.getString("Email"),tmp.getString("Nome"),tmp.getString("Orario"),tmp.getString("Data"),tmp.getString("Tipo"),tmp.getInt("Pista") );
					System.out.println(str);
					//stampa nuova riga
					out.println(str);
				}
				if (name.equals("Laser Game")) {
					String strL=String.format("<tr> <td>%d</td> <td>%s</td> <td>%s</td> <td>%s</td> <td>%s</td> <td>%s</td> </tr>", tmp.getInt("People"),tmp.getString("Email"),tmp.getString("Nome"),tmp.getString("Orario"),tmp.getString("Data"),tmp.getString("Tipo"));
					out.println(strL);
				}
		
				if (name.contentEquals("Biliardo") || name.contentEquals("Ping Pong") || name.contentEquals("Biliardino")) {
					
					String strA=String.format("<tr> <td>%d</td> <td>%s</td> <td>%s</td> <td>%s</td> <td>%s</td> <td>%s</td> <td>%d</td> </tr>", tmp.getInt("People"),tmp.getString("Email"),tmp.getString("Nome"),tmp.getString("Orario"),tmp.getString("Data"),tmp.getString("Tipo"),tmp.getInt("Tavolo"));
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
        
//        out.print("<label for=\"lname\">Orario</label>");
//        out.println("</br>");
// 		out.print("<input type=text name=orario");
//        if (orario != null){out.print(" value=\""+orario+"\"");}
//        out.println(" />");
//        out.println("</br>");
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
//        out.print("<label for=\"lname\">Data</label>");
//        out.println("</br>");
//        out.print("<input type=text name=data");
//        if (data != null){out.print(" value=\""+data+"\"");}
//        out.println(" />");
//        out.println("</br>");
//       
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
        out.println("<div id=\"tipo_form\">");
        out.print("<select name=tipo style=width:100px\">\r\n" + 
        		 
        		"    <option value=\"bowling\">Bowling</option>\r\n" + 
        		"    <option value=\"ping-pong\">Ping Pong</option>\r\n" + 
        		"    <option value=\"biliardino\">Biliardino</option>\r\n" + 
        		"    <option value=\"biliardo\">Biliardo</option>\r\n" + 
        		"    <option value=\"lasergame\">Laser Game</option>\r\n" + 
        		
        		"</select>");
        if (tipo != null){out.print(" value=\""+tipo+"\"");}
        out.println("</div></br>");
        
       
        
//        out.print("<label for=\"lname\">Numero Pista</label>");
//        out.println("</br>");
//        out.print("<input type=text name=pista");
//        
//        if (pista != null){out.print(" value=\""+pista+"\"");}
//        out.println(" />");
//        out.println("</br>");
//      
        out.print("<label for=\"lname\">Pista</label>");
        out.println("</br>");
        out.println("<div id=\"pista_form\">");
        out.print("<select name=pista style=width:100px\">\r\n" + 
        		 
		
        		"    <option value=\"1\">1</option>\r\n" + 
        		"    <option value=\"2\">2</option>\r\n" + 
        		"    <option value=\"3\">3</option>\r\n" + 
        		"    <option value=\"4\">4</option>\r\n" + 
        		
        		"</select>");
        if (pista != null){out.print(" value=\""+pista+"\"");}
        out.println("</div></br>");
        
        /*select tavolo*/
        out.print("<label for=\"lname\">Tavolo</label>");
        out.println("</br>");
        out.println("<div id=\"tavolo_form\">");
        out.print("<select name=tavolo style=width:100px\">\r\n" + 
        		 
				"    <option value=\"1\">1</option>\r\n" +
        		"    <option value=\"2\">2</option>\r\n" + 
        		"    <option value=\"3\">3</option>\r\n" + 
        		"    <option value=\"4\">4</option>\r\n" + 	
        		"</select>");
        if (data != null){out.print(" value=\""+data+"\"");}
        out.println("</div></br>");
        
        out.println("<input type=submit name=go value=\"Prenota\"></input>"); 
    
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

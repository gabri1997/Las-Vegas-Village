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
 * Servlet implementation class Statistiche
 */
@WebServlet("/Statistiche")
public class Statistiche extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String JDBCDriverSQLite = "org.sqlite.JDBC";
	public static final String JDBCURLSQLite = "jdbc:sqlite:C:\\Users\\Gabriele Saitama\\eclipse-workspace\\DB\\Vegas.db";
	protected DBManager2 db = new DBManager2(DBManager2.JDBCDriverSQLite, DBManager2.JDBCURLSQLite);
	protected Statement statement;
	protected Connection connection;
	int ID;
	int num;
       
    /**
     * @throws ClassNotFoundException 
     * @throws SQLException 
     * @see HttpServlet#HttpServlet()
     */
    public Statistiche() throws ClassNotFoundException, SQLException {
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
		 
		// out.print("<script src=\"Chart.js\"></script>");
		out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js\"></script>");

		out.println("</head>");
		out.println("<body>");
		
		out.println("<div class=\"h1\">");
		out.println("<h1><em>Statistiche</em></h1>");
		out.println("</div>");
		out.println("<canvas id=\"myChart\" width=\"400\" height=\"400\"></canvas>");
		out.println("<script>");
		out.println("var ctx = document.getElementById(\"myChart\").getContext(\"2d\");");
		out.println("var data = {labels: [\"Africa\", \"Asia\", \"Europe\", \"Latin America\", \"North America\"], datasets: [{label: \"Population (millions)\",backgroundColor: [\"#3e95cd\", \"#8e5ea2\",\"#3cba9f\",\"#e8c3b9\",\"#c45850\"],data: [2478,5267,734,784,433]}]};"); 
		out.println("var options = {title: {display: true,text: 'Predicted world population (millions) in 2050'}};");
		out.println("var myNewChart = new Chart(ctx).Pie(data, options);");
		out.println("</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

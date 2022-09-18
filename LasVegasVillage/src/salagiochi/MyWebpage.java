package salagiochi;

import java.io.PrintWriter;

public interface MyWebpage {
	public static void printHeader(PrintWriter out, String title) {
		out.println("<head>");
		out.println("<title>".concat(title).concat("</title>"));
		out.println("</head>");
	}
}

package com.undertow;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import business.RProgram;
import jpa.EntityManagerHelper;

public class LoadSpecificR extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RProgram prog =  EntityManagerHelper.getEntityManager().
				find(RProgram.class, Long.parseLong(request.getPathInfo().replaceAll("/", "")));
		
		System.err.println(request.getPathInfo());
		
		
		PrintWriter out = response.getWriter();

		out.println("<HTML>\n<BODY>\n");
		
		out.println("<FORM Method=\"POST\" Action=\"/myapp/rservlet\">"+
		"R Program Name : <INPUT type=text size=20 name=\"name\"  value=\""+ prog.getName()  +" \"><BR>"+
		"Program : <textarea name=\"program\" cols=\"40\" rows=\"5\">" + prog.getProgram()+  "</textarea>"+		
		"<BR>"+ 
		"<INPUT type=\"submit\""+
		"value=\"Run\"></input>"+
		"</FORM>");
		
		out.println("</BODY></HTML>");
		out.close();
	}
}
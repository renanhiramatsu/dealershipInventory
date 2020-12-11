// package servlet;

// import java.io.*;
// import java.util.ArrayList;

// import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

// import client.*;


// @WebServlet("/GetAutomobileList")
// public class GetAutomobileList extends HttpServlet {
//     // An ArrayList of names of existing automobiles 
// 	private ArrayList<String> automobileNames;

// 	/**
// 	 * Handle GET request, return a form to let user pick an existing automobile on server
// 	 */
// 	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException{
// 		Connectable client = new DefaultSocketClient("localhost", 4040);
// 		response.setContentType("text/html");
// 		PrintWriter out = null;
// 		try {
// 			out = response.getWriter();
// 		} catch (IOException e) {
// 			System.err.printf("Error writing response to client ... \n");
// 			return;
// 		}
// 		// Use client socket to get existing automobiles from server
// 		automobileNames = new ArrayList<String>();
// 		client.getAvailableAutomobiles(automobileNames);
		
//         out.println("<html>");
//         out.println("<head>");
//         // Bootstrap 4
//         out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
//         out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">");
//         out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js\"></script>");
//         out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"></script>");
//         out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"></script>");
//         out.println("<title>Java Auto</title>");
//         out.println("</head>");
        
//         out.println("<body>");
        
//         out.println("<div class=\"jumbotron text-center\">");
//         out.println("<h1 style=\"font-size:60px;\">Welcome to Java Car ^_^</h1>");
//         out.printf("<p style=\"font-size:20px;\">Please choose a automobile you want to configure from the list</p>");
//         out.println("</div>");
        
//         out.println("<form action=\"ConfigureAutomobile\" method=\"get\">");
//         out.println("<div style=\"margin-left: auto; margin-right: auto;\" class=\"form-group col-md-4\">");
//         out.println("<label style=\"font-size:20px;\">&nbsp;Automobile</label>");
//         out.println("<select name=\"car\" class=\"form-control\">");
//         out.println("<option selected>Select an Automobile</option>");
//         // Generate a drop down for available automobiles
//         for (int i = 2; i < automobileNames.size(); ++i) 
//         	out.printf("<option value=\"%d\">%s</option>\n", i - 1, automobileNames.get(i));
//         out.println("</select>");
//         out.println("<br/><button type=\"submit\" class=\"btn btn-primary\">Submit</button>");
//         out.println("</form>");
//         out.println("</div>");
//         out.println("</body>");
//         out.println("</html>");
//         out.close();
// 	}
	
// 	/**
// 	 * Redirect POST request to GET request
// 	 */
// 	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException{
// 		doGet(request, response);
// 	}
// }

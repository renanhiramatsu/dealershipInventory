// package servlet;

// import java.io.IOException;
// import java.io.PrintWriter;

// import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;

// import client.Connectable;
// import client.DefaultSocketClient;
// import model.*;

// @WebServlet("/ConfigureAutomobile")
// public class ConfigureAutomobile extends HttpServlet {

// // An automobile to be configured (will get the real automobile later)
// private Automobile auto = new Automobile("");

// /**
// * Handle GET request, return a form to let user choose options in the chosen
// automobile
// */
// public void doGet(HttpServletRequest request, HttpServletResponse response)
// throws ServletException {
// Connectable client = new DefaultSocketClient("localhost", 4040);
// response.setContentType("text/html");
// PrintWriter out = null;
// // Use client socket to get the Automobile from server
// client.getAutomobile(Integer.parseInt(request.getParameter("car")), auto);
// try {
// out = response.getWriter();
// } catch (IOException e) {
// System.err.printf("Error writing response to client ... \n");
// return;
// }

// out.println("<html>");
// out.println("<head>");
// // Bootstrap 4
// out.println("<meta name=\"viewport\" content=\"width=device-width,
// initial-scale=1\">");
// out.println("<link rel=\"stylesheet\"
// href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">");
// out.println("<script
// src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js\"></script>");
// out.println("<script
// src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"></script>");
// out.println("<script
// src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"></script>");
// out.println("<title>Java Auto</title>");
// out.println("</head>");

// out.println("<body>");
// out.println("<div class=\"jumbotron text-center\">");
// out.printf("<h1 style=\"font-size:60px;\">%s</h1>\n", auto.getName());
// out.printf("<p style=\"font-size:20px;\">Base price : %.0f US$</p>\n",
// auto.getBasePrice());
// out.println("</div>");

// out.println("<form action=\"result.jsp\" method=\"post\">");
// out.printf("<input type=\"hidden\" name=\"carindex\" value=\"%d\">\n",
// Integer.parseInt(request.getParameter("car")));
// out.printf("<input type=\"hidden\" name=\"car\" value=\"%s\">\n",
// auto.getName());
// out.printf("<input type=\"hidden\" name=\"baseprice\" value=\"%.0f\">\n",
// auto.getBasePrice());
// // Generate drop down for each OptionSet
// for (int i = 0; i < auto.getNumberOfOptionSets(); ++i) {
// out.println("<div style=\"margin-left: auto; margin-right: auto;\"
// class=\"form-group col-md-4\">");
// out.printf("<label style=\"font-size:15px;\"
// for=\"inputOption%d\">%s</label>\n", i, auto.getOptionSetName(i));
// out.printf("<input type=\"hidden\" name=\"optionsets\" value=\"%s\">\n",
// auto.getOptionSetName(i));
// out.printf("<select name=\"optionandprices\" id=\"inputOption%d\"
// class=\"form-control\">\n", i);
// out.println("<option selected>Choose an Option</option>");
// // Generate drop down for each Option
// for (int j = 0; j < auto.getNumberOfOptionInAnOptionSet(i); ++j)
// out.printf("<option value=\"%s\">%s \t\t(Price : %.0f US$)</option>\n",
// auto.getOptionName(auto.getOptionSetName(i), j) + "|" +
// (int)auto.getOptionPrice(i, j),
// auto.getOptionName(auto.getOptionSetName(i), j), auto.getOptionPrice(i, j));
// out.println("</select>");
// if (i != auto.getNumberOfOptionSets() - 1)
// out.println("</div>");
// }
// out.println("<br/><button style=\"position: absolute; left: 40%;\"
// type=\"submit\" class=\"btn btn-primary\">Submit</button>");
// out.println("</div>");
// out.println("</form> <br/><br/>");
// out.println("</body>");
// out.println("</html>");
// out.close();
// }

// /**
// * Redirect POST request to GET request
// */
// public void doPost(HttpServletRequest request, HttpServletResponse response)
// throws ServletException{
// doGet(request, response);
// }

// }

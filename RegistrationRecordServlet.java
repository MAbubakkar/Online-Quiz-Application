/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import org.apache.taglibs.standard.functions.Functions;
/**
 *
 * @author 140689
 */
public class RegistrationRecordServlet extends HttpServlet {
    
    //Step-1: JDBC driver name and database URL
 static final String JDBC_DRIVER ="org.apache.derby.jdbc.ClientDriver";
 static final String DB_URL ="jdbc:derby://localhost:1527/OnlineQuiz";
 
 //Step-2: Database credentials
 static final String USER = "user1";
 static final String PASS = "1234";

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            
            
            out.println("<html>\n" +
"    <head>\n" +
"        <meta charset=\"UTF-8\">\n" +
"    <title>Registration</title>\n" +
"    \n" +
"    \n" +
"    \n" +
"    \n" +
"        <link rel=\"stylesheet\" href=\"css/style.css\">\n" +
"        \n" +
"        <style>\n" +
"            \n" +
"                /* ---------- GENERAL ---------- */\n" +
"                \n" +
"  box-sizing: border-box\n" +
"  }\n" +
"*:before, *:after {\n" +
"  box-sizing: border-box;\n" +
"}\n" +
"\n" +
"body {\n" +
"  background: #eaeaea;\n" +
"  color: #999;\n" +
"  font: 400 16px/1.5em sans-serif;\n" +
"  margin: 0;\n" +
"}\n" +
"\n" +
"h3 {\n" +
"  margin: 0;\n" +
"}\n" +
"\n" +
"a {\n" +
"  color: #999;\n" +
"  text-decoration: none;\n" +
"}\n" +
"\n" +
"a:hover {\n" +
"  color: #1dabb8;\n" +
"}\n" +
"\n" +
"fieldset {\n" +
"  border: none;\n" +
"  margin: 0;\n" +
"}\n" +
"\n" +
"input {\n" +
"  border: none;\n" +
"  font-family: inherit;\n" +
"  font-size: inherit;\n" +
"  margin: 0;\n" +
"  -webkit-appearance: none;\n" +
"}\n" +
"\n" +
"input:focus {\n" +
"  outline: none;\n" +
"}\n" +
"\n" +
"input[type=\"submit\"] {\n" +
"  cursor: pointer;\n" +
"}\n" +
"\n" +
".clearfix {\n" +
"  *zoom: 1;\n" +
"}\n" +
".clearfix:before, .clearfix:after {\n" +
"  content: ' ';\n" +
"  display: table;\n" +
"}\n" +
".clearfix:after {\n" +
"  clear: both;\n" +
"}\n" +
"\n" +
".container {\n" +
"  left: 50%;\n" +
"  position: fixed;\n" +
"  top: 50%;\n" +
"  -webkit-transform: translate(-50%, -50%);\n" +
"          transform: translate(-50%, -50%);\n" +
"}\n" +
"\n" +
"/* ---------- LOGIN-FORM ---------- */\n" +
"#login-form {\n" +
"  width: 300px;\n" +
"}\n" +
"\n" +
"#login-form h3 {\n" +
"  background-color: #282830;\n" +
"  border-radius: 5px 5px 0 0;\n" +
"  color: #fff;\n" +
"  font-size: 14px;\n" +
"  padding: 20px;\n" +
"  text-align: center;\n" +
"  text-transform: uppercase;\n" +
"}\n" +
"\n" +
"#login-form fieldset {\n" +
"	background: #fff;\n" +
"	border-radius: 0 0 5px 5px;\n" +
"	padding: 20px;\n" +
"	position: relative;\n" +
"	font-size: 16px;\n" +
"	color: #391314;\n" +
"}\n" +
"\n" +
"#login-form fieldset:before {\n" +
"  background-color: #fff;\n" +
"  content: \"\";\n" +
"  height: 8px;\n" +
"  left: 50%;\n" +
"  margin: -4px 0 0 -4px;\n" +
"  position: absolute;\n" +
"  top: 0;\n" +
"  -webkit-transform: rotate(45deg);\n" +
"  transform: rotate(45deg);\n" +
"  width: 8px;\n" +
"}\n" +
"\n" +
"#login-form input {\n" +
"  font-size: 14px;\n" +
"}\n" +
"\n" +
"#login-form input[type=\"email\"],\n" +
"#login-form input[type=\"password\"],\n" +
"#login-form input[type=\"text\"]\n" +
"\n" +
" {\n" +
"  border: 1px solid #dcdcdc;\n" +
"  padding: 12px 10px;\n" +
"  width: 100%;\n" +
"}\n" +
"\n" +
"\n" +
"#login-form input[type=\"email\"] {\n" +
"  border-radius: 3px 3px 0 0;\n" +
"}\n" +
"\n" +
"#login-form input[type=\"password\"] {\n" +
"  border-top: none;\n" +
"  border-radius: 0px 0px 3px 3px;\n" +
"}\n" +
"\n" +
"#login-form input[type=\"submit\"] {\n" +
"  background: #1dabb8;\n" +
"  border-radius: 3px;\n" +
"  color: #fff;\n" +
"  float: right;\n" +
"  font-weight: bold;\n" +
"  margin-top: 20px;\n" +
"  padding: 12px 20px;\n" +
"}\n" +
"\n" +
"#login-form input[type=\"submit\"]:hover {\n" +
"  background: #198d98;\n" +
"}\n" +
"\n" +
"#login-form footer {\n" +
"  font-size: 12px;\n" +
"  margin-top: 16px;\n" +
"}\n" +
"\n" +
".info {\n" +
"  background: #e5e5e5;\n" +
"  border-radius: 50%;\n" +
"  display: inline-block;\n" +
"  height: 20px;\n" +
"  line-height: 20px;\n" +
"  margin: 0 10px 0 0;\n" +
"  text-align: center;\n" +
"  width: 20px;\n" +
"}\n" +
"\n" +
"    \n" +
"        </style>\n" +
"        \n" +
"    </head>\n" +
"<body align=\"center\">\n" +
" \n" +
"    \n" +
"            <div class=\"container\">\n" +
"\n" +
"  <div id=\"login-form\">\n" +
"\n" +
"    <h3>Registration </h3>\n" +
"\n" +
"    <fieldset>\n" +
"\n" +
"      <form action=\"RegistrationRecordServlet\" method=\"POST\">\n" +
"      \n" +
"     \n" +
"     \n" +
"          <input type=\"text\" required name=\"fname\" placeholder=\"First Name\"/>\n" +
"\n" +
"        <input type=\"text\" required name=\"lname\" placeholder=\"Last Name\"/>\n" +
"\n" +
"        <input type=\"email\" required name=\"username\" placeholder=\"Email\"/> \n" +
"        \n" +
"        <input type=\"password\" required name=\"password\" placeholder=\"Password\"/> \n" +
"        \n" +
"        <input type=\"text\" required name=\"contact\" placeholder=\"Contact No\"/>\n" +
"        \n" +
"         \n" +
"\n" +
"        <input type=\"submit\" value=\"Register\">\n" +
"\n" +
"        <footer class=\"clearfix\">\n" +
"\n" +
"        </footer>\n" +
"\n" +
"      </form>\n" +
"        \n" +
"      <h2><a href=\"HomePage.html\">Already A Member <br>Login Here</a></h2>\n" +
"    </fieldset>\n" +
"    \n" +
"\n" +
"  </div> <!-- end login-form -->\n" +
"\n" +
"</div>\n" +
"    \n" +
" \n" +
"\n" +
"    \n" +
"\n" +
"</body>\n" +
"</html>");
            
            
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet RegistrationRecordServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
            String fname = request.getParameter("fname");
            String lname = request.getParameter("lname");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String contact = request.getParameter("contact");
            
            
            Connection conn = null;
           Statement stmt = null;
           try {
            //Step-3: Register JDBC driver
           Class.forName(JDBC_DRIVER);
           
           //Step-4: Open a connection
           conn = DriverManager.getConnection(DB_URL, USER, PASS);
           
           //Step-5 Execute a query. Creating statement
           stmt = conn.createStatement();
           String sql1;
           
               // sql1 = "SELECT * FROM USERINFO WHERE USERNAME = '"+username+"' ";
                sql1 = "SELECT * FROM USERINFO  "; 
                 ResultSet rs = stmt.executeQuery(sql1);
                 boolean isAlreadyRegistered;
                    isAlreadyRegistered = false;
                    //STEP 5: Extract data from result set
                    while(rs.next()){
                        String usernameStr =  rs.getString("username");
                        if(usernameStr.trim().equals( username.trim())){
                            isAlreadyRegistered= true;
                            break;
                        }
                        
                        
//               //Retrieve by column name
//               int id  = rs.getInt("id");
//               int age = rs.getInt("age");
//               String first = rs.getString("first");
//               String last = rs.getString("last");
//
//               //Display values
//               System.out.print("ID: " + id);
//               System.out.print(", Age: " + age);
//               System.out.print(", First: " + first);
//               System.out.println(", Last: " + last);
                    }  
                    
           if(isAlreadyRegistered == false) //new
           {
             String  sql2 = "INSERT INTO USERINFO (FIRSTNAME,LASTNAME,USERNAME, USERPASSWORD,USERCONTACT) VALUES('"+
            fname.trim()+"' ,' "+lname.trim()+" ',' "+username.trim()+" ',' "+password.trim()+" ',' "+contact.trim()+" ')";

            int r=stmt.executeUpdate(sql2);
            
            response.sendRedirect("QuizList.html");
            out.println("<h1 align=center>successfully registered</h1>");
           }
           else if (isAlreadyRegistered == true)
           {
               //error msg
                // out.println("User Already Exists ");

                        out.println("<script>\n" +
                        "      \n" +
                        "      alert(\"User Already Exists\");\n" +
                        "        \n" +
                        "        </script>");
                
               
               
              
               
             // String message = "User Already Exists";
             //response.sendRedirect("http://localhost:8080/QuizProject/faces/RegistrationValidate.html?message=" + URLEncoder.encode(message, "UTF-8"));
               
                //response.sendRedirect("http://localhost:8080/QuizProject/faces/RegistrationValidate.html");

                //  out.println("User Already Exists ");
               
                
           }
           
            stmt.close();
            conn.close();
             } catch (SQLException se) {
             out.println("SQLException:" + se);
             } 
//                        out.println("<h1>Servlet RegistrationRecordServlet at " + request.getContextPath() + "</h1>");
//                        out.println("</body>");
//                        out.println("</html>");
                    } finally {            
                        out.close();
                    }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     try {
         processRequest(request, response);
     } catch (ClassNotFoundException ex) {
         Logger.getLogger(RegistrationRecordServlet.class.getName()).log(Level.SEVERE, null, ex);
     }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     try {
         processRequest(request, response);
     } catch (ClassNotFoundException ex) {
         Logger.getLogger(RegistrationRecordServlet.class.getName()).log(Level.SEVERE, null, ex);
     }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void sendRedirect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void alert(String user_Already_Exists) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

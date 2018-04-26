/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
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
import javax.servlet.http.HttpSession;
/**
 *
 * @author 140689
 */
public class LoginRecordServlet extends HttpServlet {
    
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
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginRecordServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            
            
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
           
            sql1 = "SELECT * FROM USERINFO  "; 
                 ResultSet rs = stmt.executeQuery(sql1);
                 
                 while(rs.next()){
                        String usernameStr =  rs.getString("username");
                        String passwordStr =  rs.getString("userpassword");
                        
                        if(usernameStr.trim().equals( username.trim())){
                            if(passwordStr.trim().equals( password.trim())){
                                
                                HttpSession session=request.getSession();  
                                session.setAttribute("username",username);
                                
                                response.sendRedirect("http://localhost:8080/QuizProject/faces/QuizList.html");
                            }
                            
                            else{
                                
                                response.sendRedirect("http://localhost:8080/QuizProject/faces/LoginValidate.html");
                                
                            }
                            
                           // break;
                        }
                 
           
               // String  sql2 = "INSERT INTO LOGININFO (USERNAME, USERPASSWORD) VALUES(' "+username.trim()+" ',' "+password.trim()+" ')";
               //  int r=stmt.executeUpdate(sql2);
            
                 }
             stmt.close();
            conn.close();
             } catch (SQLException se) {
             out.println("SQLException:" + se);
             } 
                        out.println("<h1>Servlet RegistrationRecordServlet at " + request.getContextPath() + "</h1>");
                        out.println("</body>");
                        out.println("</html>");
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
     } catch (SQLException ex) {
         Logger.getLogger(LoginRecordServlet.class.getName()).log(Level.SEVERE, null, ex);
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
     } catch (SQLException ex) {
         Logger.getLogger(LoginRecordServlet.class.getName()).log(Level.SEVERE, null, ex);
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

    private static class userpassword {

        private static Object trim() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public userpassword() {
        }
    }
}

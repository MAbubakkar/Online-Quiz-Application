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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
/**
 *
 * @author 140689
 */
public class QuizServlet extends HttpServlet {
    
    //Step-1: JDBC driver name and database URL
 static final String JDBC_DRIVER ="org.apache.derby.jdbc.ClientDriver";
 static final String DB_URL ="jdbc:derby://localhost:1527/OnlineQuiz";
 
    ArrayList<QuestionItem> questionsArrayList;
 
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
            
            
            String action;
            
            
            
            Connection conn = null;
           Statement stmt = null;
           try {
            //Step-3: Register JDBC driver
           Class.forName(JDBC_DRIVER);
           
           //Step-4: Open a connection
           conn = DriverManager.getConnection(DB_URL, USER, PASS);
           
           //Step-5 Execute a query. Creating statement
           stmt = conn.createStatement();
           
           
           
 
           action = request.getParameter("id");
     
          if(action.equals("math")){      
               String sql1;
           
            sql1 = "SELECT * FROM Question"; 
                 ResultSet rs = stmt.executeQuery(sql1);
               
              // response.sendRedirect("http://localhost:8080/QuizProject/StartQuiz");
               
               out.println("<form><table>");
             //out.println("<tr><th>QUESTIONNO</th><th>QUESTION</th><th>OPTION1</th><th>OPTION2</th><th>OPTION3</th><th>ANSWER</th><tr>");
             ArrayList<String> list = new ArrayList<String>();
             while (rs.next()) {
                 
                 
                 if(questionsArrayList== null){
                     questionsArrayList= new ArrayList<QuestionItem>();
                 }
                 
                 QuestionItem questionItem= new QuestionItem();
               
                 
                 questionItem.setQuestionNo(Integer.parseInt(rs.getString("QUESTIONNO")));
                 out.println("helloworld");
                 questionItem.setQuestion(rs.getString("QUESTION"));
                  
                 list.clear();
                 list.add(rs.getString("OPTION1"));
                 list.add(rs.getString("OPTION2"));
                 list.add(rs.getString("OPTION3"));
                 questionItem.setOptions(list);
                 questionItem.setAnswer(rs.getString("ANSWER"));
             
                  
                 int qno = questionItem.getQuestionNo();
                 String q = questionItem.getQuestion();
                 ArrayList<String> mylist = questionItem.getOptions(); 
                 String o4 = questionItem.getAnswer();
                 out.println("<tr>");
                    out.println("<td>" + qno + "</td>");
                    out.println("<td>" + q + "</td>");
                 out.println("</tr>");
                 out.println("<tr>");
                    out.println("<td>");
                        Iterator<String> itr = mylist.iterator();
                        int i = 1;
                        while (itr.hasNext()) {
                            //System.out.println(itr.next());
                            out.println("i= " +i+"<br/>");
                            out.println("<input type='radio' name='answer' value='o"+i+"' checked/>" + itr.next());
                            i++;
                        }    
                    out.println("</td>");
                 out.println("</tr>");
                 //<tr><td><input type='radio' name='answer' value='o2'/>" + o2 + "</td></tr><tr><td><input type='radio' name='answer' value='o3/>"+ o3 +"</td></tr>
                 out.println("<tr>");
                    out.println("<td>");
                        out.println("<input type='radio' name='answer' value='o"+i+"'/>"+ o4);
                    out.println("</td>");
                 out.println("</tr>"); 
             }
             
             out.println("</table></form>");
             out.println("</html></body>");
            
           }
           else if(action.equals("physics")){
               response.sendRedirect("http://localhost:8080/QuizProject/StartQuiz");
           }
           else if(action.equals("chemistry")){
               response.sendRedirect("http://localhost:8080/QuizProject/StartQuiz");
           }
           else if(action.equals("biology")){
               response.sendRedirect("http://localhost:8080/QuizProject/StartQuiz");
           }
           else if(action.equals("english")){
               response.sendRedirect("http://localhost:8080/QuizProject/StartQuiz");
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

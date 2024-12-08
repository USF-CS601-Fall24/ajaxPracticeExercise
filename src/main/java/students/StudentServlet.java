package students;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/** Javascript will call this servlet to get a certain number of students from the students
 * table in the database. It will internally update the "offset", so that every time
 * we call this servlet, it will get us two new students from the table.
 * Returns students in a json file
 */
public class StudentServlet extends HttpServlet  {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int offset = 0;
        int limit = 2; // show two students at a time
        SimpleDatabaseHandler dbHandler = new SimpleDatabaseHandler();
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        Object res = session.getAttribute("offset");
        if (res == null) { // loading for the first time
            offset = 0;
        }
        else {
            offset = (int)res;
        }
        // FILL IN CODE: update the "offset" attribute in the session by adding
        // a limit to the current offset.
        // FILL IN CODE: call getStudentInfo method in the dbHandler
        // Handle the case when the list is empty (reached the end of the students rows).

       // FILL IN CODE: create a json array of students and write it to the print writer of the response
    }
}

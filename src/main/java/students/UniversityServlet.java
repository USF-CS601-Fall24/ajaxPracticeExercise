package students;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class UniversityServlet extends HttpServlet  {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = response.getWriter();

        // Get the "name" parameter from the GET request
        // The request may look like this: /?name=Dakota
        String name = request.getParameter("name");
        if (name == null)
            name = "";
        name = StringEscapeUtils.escapeHtml4(name);

        VelocityEngine ve = (VelocityEngine) getServletContext().getAttribute("templateEngine");
        Template template = ve.getTemplate("templates/universityInfo.html");

        VelocityContext context = new VelocityContext();
        context.put("name", name);
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        out.println(writer);
    }
}

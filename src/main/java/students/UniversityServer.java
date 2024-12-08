package students;

import org.apache.velocity.app.VelocityEngine;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class UniversityServer {

	public static void main(String[] args) {
		Server server = new Server(8080);
		ServletContextHandler servletHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		servletHandler.addServlet(UniversityServlet.class, "/");
		servletHandler.addServlet(StudentServlet.class, "/students"); // do not call directly, call via Ajax

		VelocityEngine velocity = new VelocityEngine();
		velocity.init();
		servletHandler.setAttribute("templateEngine", velocity);
		server.setHandler(servletHandler);

		try {
			server.start();
			server.join();
		} catch (Exception e) {
			System.out.println("Exception occurred while running the server: " + e);
		}

	}

}

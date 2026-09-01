package com.rays.ctl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;


@Transactional
@RestController
@RequestMapping("/jasper")
public class JasperCtl {

	@PersistenceContext
	private EntityManager entityManager;

	private SessionFactory sessionFactory;

	@GetMapping(value = "/report", produces = MediaType.APPLICATION_PDF_VALUE)
	public void generateReport(HttpServletResponse response) throws JRException, IOException, SQLException {

		System.out.println("******** Jasper Report Start ********");

		Connection con = null;

		try {
			// Load JRXML file from resources
			InputStream input = getClass().getResourceAsStream("/reports/Project10.jrxml");

			if (input == null) {
				throw new RuntimeException("JRXML file not found in resources/reports");
			}

			// Compile JRXML to JasperReport
			JasperReport jasperReport = JasperCompileManager.compileReport(input);

			// Set parameters for report
			Map<String, Object> params = new HashMap<>();
			params.put("createdBy", "Admin");

			// Get DB connection from Hibernate
			sessionFactory = entityManager.getEntityManagerFactory().unwrap(SessionFactory.class);

			con = sessionFactory.getSessionFactoryOptions().getServiceRegistry().getService(ConnectionProvider.class)
					.getConnection();

			// Fill report with data
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, con);

			// Export report to PDF
			byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);

			// Set response headers
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "inline; filename=marksheet.pdf");

			// Write PDF to response
			response.getOutputStream().write(pdf);
			response.getOutputStream().flush();

			System.out.println("******** Report Generated ********");

		} catch (Exception e) {
		    e.printStackTrace();
		    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		    response.setContentType("text/plain");
		    response.getWriter().write(e.getMessage());
		} finally {
			// Close DB connection
			if (con != null) {
				con.close();
			}
		}
	}
}
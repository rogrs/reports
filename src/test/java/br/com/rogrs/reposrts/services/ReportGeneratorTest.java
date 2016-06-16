package br.com.rogrs.reposrts.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.rogrs.reports.Application;
import br.com.rogrs.reports.domain.Reports;
import br.com.rogrs.reports.services.ReportGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ReportGeneratorTest {

    @Test
    public void generate() {

        Connection connection = getConnection();

        HashMap params = new HashMap();
        params.put("ID", "123");

        Reports report = new Reports();

        report.setJasperFilename("");
        report.setJrxmlFilename("");
        report.setPdfFilename("");

        ReportGenerator rg = new ReportGenerator();

        rg.generate(report, connection, params);

    }

    private Connection getConnection() {

        String dbUrl = "jdbc:oracle:thin:@localhost:1521:mydbname";

        String dbDriver = "oracle.jdbc.driver.OracleDriver";

        String dbUname = "mydb";

        String dbPwd = "mydbpw";

        // Load the JDBC driver
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Get the connection
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbUrl, dbUname, dbPwd);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return conn;
    }
}

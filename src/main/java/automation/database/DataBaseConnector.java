package automation.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author abrar
 * since 9/9/20
 */

public class DataBaseConnector {
    private final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/node-complete";
    private final Logger log = LoggerFactory.getLogger(DataBaseConnector.class);
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private ResultSetMetaData resultSetMetaData;
    private String currentDbConnectionUrl;

    public Connection getConnectionToDb() {
        currentDbConnectionUrl = DB_CONNECTION_URL;
        try {
            log.info("Connecting to DB with url: " + currentDbConnectionUrl);
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(currentDbConnectionUrl, "root", "123456aA#");
            log.info("Database Connection established!");
            return connection;

        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("Database Connection could not be established!");
            log.error(ex.toString());
            return null;
        }
    }

    public void endConnection(Connection connection) {
        try {
            log.info("Closing connection to DB with url: " + currentDbConnectionUrl);
            connection.close();
            log.info("Connection closed.");
        } catch (Exception ex) {
            log.error("Could not close the connection!");
            log.error(ex.toString());
        }
    }

    public String getSingleResult(String query) {
        try {
            getConnectionToDb();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            log.info("Executing query : " + query);
            resultSet.next();
            String result = resultSet.getString(1);
            endConnection(connection);
            log.info("Query Result: " + result);
            return result;
        } catch (SQLException ex) {
            log.error(ex.toString());
            endConnection(connection);
            return null;
        }
    }

    public ArrayList<String> getMultipleResults(String query) {
        try {
            StringBuilder currentResult = null;
            ArrayList<String> results = new ArrayList<>();
            getConnectionToDb();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            resultSetMetaData = resultSet.getMetaData();
            log.info("Executing query : " + query);
            int columnCount = resultSetMetaData.getColumnCount();
            log.info("Column Count: " + columnCount);
            while (resultSet.next()) {
                currentResult = new StringBuilder();
                for (int index = 1; index <= columnCount; index++) {
                    log.info("Query Result: " + resultSet.getString(index));
                    currentResult.append(resultSet.getString(index)).append(" ");
                }
                results.add(currentResult.toString());
            }
            endConnection(connection);
            return results;
        } catch (SQLException ex) {
            ex.printStackTrace();
            endConnection(connection);
            return null;
        }
    }

    public void executeUpdateQuery(String query) {
        try {
            getConnectionToDb();
            log.info("Executing query : " + query);
            statement = connection.createStatement();
            statement.executeQuery(query);
            statement.executeQuery("commit");
            endConnection(connection);
        } catch (SQLException ex) {
            ex.printStackTrace();
            endConnection(connection);
        }
    }
}

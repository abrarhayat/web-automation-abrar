package database;

import automation.database.DataBaseConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import automation.database.QueryBuilder;

import java.sql.*;

/**
 * @author abrar
 * since 9/9/20
 */

public class DatabaseConnectionTest {
    private final Logger log = LoggerFactory.getLogger(DatabaseConnectionTest.class);
    private Statement statement;
    private ResultSet resultSet;
    private ResultSetMetaData resultSetMetaData;
    private DataBaseConnector dataBaseConnector;
    private Connection connection;

    @BeforeTest
    public void before() {
        dataBaseConnector = new DataBaseConnector();
        connection = dataBaseConnector.getConnectionToDb();
    }

    @Test
    public void testDBConnection() throws SQLException {
        String query = QueryBuilder.getSelectQUERY_TO_EXECUTE("title", "products", "WHERE id = 1");

        statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        resultSetMetaData = resultSet.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();

        while (resultSet.next()) {
            String info;
            for (int columnNumber = 1; columnNumber <= columnCount; columnNumber++) {
                info = resultSetMetaData.getColumnLabel(columnNumber) + ": "
                        + resultSet.getString(columnNumber) + ", ";
                log.info(info);
                System.out.println(info);
            }
            System.out.println("\n");
        }
    }

    @AfterTest
    public void after() {
        dataBaseConnector.endConnection(connection);
    }
}

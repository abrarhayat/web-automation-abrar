package database;

import org.testng.annotations.Test;

import static automation.database.QueryBuilder.getSelectQUERY_TO_EXECUTE;

/**
 * @author abrar
 * since 9/9/20
 */

public class QueryBuilderTest {

    @Test
    public void testGetQUERY_TO_EXECUTE() {
        System.out.println(getSelectQUERY_TO_EXECUTE("title", "products",
                "WHERE id = 1"));
    }
}
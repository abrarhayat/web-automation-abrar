package automation.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author abrar
 * since 9/9/20
 */

public class QueryBuilder {
    private static final Logger log = LoggerFactory.getLogger(QueryBuilder.class);

    public static String getSelectQUERY_TO_EXECUTE(String fieldsRequired, String tableName, String whereClause) {
        String query = String.format("SELECT %s FROM %s %s", fieldsRequired, tableName, whereClause);
        log.info("Query Requested: " + query);
        log.info("Query Requested: " + query);
        return query;
    }

    public static String getUpdateQUERY_TO_EXECUTE(String tableName, String fieldsToUpdate, String fieldValues,
                                                   String whereClause) {
        String query = String.format("UPDATE %s SET %s = '%s' %s", tableName, fieldsToUpdate, fieldValues, whereClause);
        log.info("Query Requested: " + query);
        log.info("Query Requested: " + query);
        return query;
    }
}

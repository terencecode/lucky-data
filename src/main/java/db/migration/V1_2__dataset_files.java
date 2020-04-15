package db.migration;

import org.flywaydb.core.api.migration.spring.SpringJdbcMigration;
import org.springframework.jdbc.core.JdbcTemplate;

public class V1_2__dataset_files implements SpringJdbcMigration {
    @Override
    public void migrate(JdbcTemplate jdbcTemplate) throws Exception {
        /*String fileName = "";
        String absolutePathToFile = "";
        String sql = "INSERT INTO dataset_file (id, name, type, data, dataset_id) " +
                "VALUES (1, '" + fileName + "', 'text/csv'," +
                "lo_import('" + absolutePathToFile + "'), 1);";
        jdbcTemplate.execute(sql);*/
        String currentDirectory = System.getProperty("user.dir");
        System.out.println("The current working directory is " + currentDirectory);
    }
}

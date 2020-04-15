package db.migration;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import javax.activation.MimetypesFileTypeMap;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class V1_2__dataset_files extends BaseJavaMigration {
    @Override
    public void migrate(Context context) throws Exception {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true));
        Path dir = Paths.get("").toAbsolutePath().resolve("src").resolve("main").resolve("resources").resolve("static").resolve("sample_datasets");
        try (Stream<Path> stream = Files.walk(dir)) {
            stream.filter(file -> !Files.isDirectory(file))
                    .forEach(path -> {
                        //System.out.println(new MimetypesFileTypeMap().getContentType(path.getFileName().toString()));
                        jdbcTemplate.execute(String.format("INSERT INTO dataset_file (name, type, data, dataset_id) " +
                                "VALUES ('%s', 'text/csv', lo_import('%s'), currval(pg_get_serial_sequence('dataset', 'id')));",
                                path.getFileName().toString(), path.toString()));
                    });
        }
    }
}

package de.rieckpil.quickstarts;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.sql.DataSource;
import java.sql.SQLException;

@ApplicationScoped
public class JdbcDataSourceVerifier {

    @Resource(lookup = "jdbc/postgresql")
    private DataSource dataSource;

    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) throws SQLException {
        System.out.println("Verifying connection to PostgreSQL");

        var metaData = dataSource.getConnection().getMetaData();

        System.out.println(metaData.getDatabaseProductName());
        System.out.println("Version: " + metaData.getDatabaseMajorVersion() + "." + metaData.getDatabaseMinorVersion());
    }
}

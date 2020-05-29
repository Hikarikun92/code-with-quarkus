package br.hikarikun92.config;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.jooq.tools.jdbc.JDBCUtils;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

@Dependent
public class JooqConfig {
    @Produces
    public DSLContext dslContext(DataSource dataSource, @ConfigProperty(name = "quarkus.datasource.jdbc.url") String url) {
        final Configuration configuration = new DefaultConfiguration();
        configuration.set(JDBCUtils.dialect(url));
        configuration.set(new DataSourceConnectionProvider(dataSource));

        return new DefaultDSLContext(configuration);
    }
}

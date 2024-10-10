package ru.sberstart.finalproject.infrastructure.configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;

@Import({
        BankAccountConfiguration.class,
        BankConfiguration.class,
        CardConfiguration.class,
        UserConfiguration.class
})
@Configuration
public class MainConfiguration {
    private final Logger logger = LoggerFactory.getLogger(MainConfiguration.class);

    @Bean
    public DataSourceConnectionProvider dataSourceConnectionProvider(DataSource dataSource) {
        if (dataSource instanceof HikariDataSource) {
            HikariDataSource hikariDataSource = (HikariDataSource) dataSource;
            System.out.println("HikariCP is being used.");
            System.out.println("Hikari Pool Size: " + hikariDataSource.getMaximumPoolSize());
        } else {
            System.out.println("DataSource is not an instance of HikariDataSource.");
        }

        return new DataSourceConnectionProvider(new TransactionAwareDataSourceProxy(dataSource));
    }
    
    @Bean
    public DefaultConfiguration defaultConfiguration(DataSourceConnectionProvider dataSourceConnectionProvider) {
        DefaultConfiguration configuration = new DefaultConfiguration();
        configuration.setConnectionProvider(dataSourceConnectionProvider);
        configuration.setSQLDialect(SQLDialect.H2);
        return configuration;
    }

    @Bean
    public DSLContext context(DefaultConfiguration configuration) {
        return new DefaultDSLContext(configuration);
    }
}

package ru.sberstart.finalproject.infrastructure.configuration;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
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
    @Bean
    public DataSourceConnectionProvider dataSourceConnectionProvider(DataSource dataSource) {
        return new DataSourceConnectionProvider(new TransactionAwareDataSourceProxy(dataSource));
    }
    
    @Bean
    public DefaultConfiguration defaultConfiguration(DataSourceConnectionProvider dataSourceConnectionProvider) {
        DefaultConfiguration configuration = new DefaultConfiguration();
//        configuration.setSQLDialect(SQLDialect.H2);
//        configuration.setSystemConnectionProvider(dataSourceConnectionProvider);
        configuration.setSQLDialect(SQLDialect.POSTGRES);
        configuration.setConnectionProvider(dataSourceConnectionProvider);
        return configuration;
    }

    @Bean
    public DSLContext context(DefaultConfiguration configuration) {
        return new DefaultDSLContext(configuration);
    }
}

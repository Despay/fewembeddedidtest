package com;


import com.controllers.BookController;
import com.daos.AuthorRepository;
import com.daos.BookRepository;
import com.entities.Book;
import com.services.IBookService;
import org.eclipse.persistence.config.PersistenceUnitProperties;
import org.eclipse.persistence.config.TargetDatabase;
import org.eclipse.persistence.jpa.PersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@SpringBootApplication
@PropertySource(value = {"classpath:jdbc.properties"})
@EnableJpaRepositories(basePackageClasses = AuthorRepository.class,
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager")
@ComponentScan(basePackageClasses = {
        BookController.class,
        AuthorRepository.class,
        BookRepository.class,
        IBookService.class,
        Book.class
})
public class Application {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    private static final String EC_WEAVING_PROP_INTERNAL_WEAVING = "eclipselink.weaving.internal";
    private static final String EC_WEAVING_PROP_INTERNAL_WEAVING_VALUE = "false";

    private static final String EC_WEAVING_PROP_WEAVING = "eclipselink.weaving";
    private static final String EC_WEAVING_PROP_WEAVING_VALUE = "false";

    private static final String EC_WEAVING_PROP_SQL_LOG = "eclipselink.logging.level.sql";
    private static final String EC_WEAVING_PROP_SQL_LOG_VALUE = "FINEST";

    private static final String EC_WEAVING_PROP_LOG = "eclipselink.logging.level";
    private static final String EC_WEAVING_PROP_LOG_VALUE = "FINEST";

    private static final String EC_WEAVING_PROP_LOG_CACHE = "eclipselink.logging.level.cache";
    private static final String EC_WEAVING_PROP_LOG_CACHE_VALUE = "FINEST";


    @Autowired
    private Environment env;


    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setDataSource(dataSource());
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }

    private EclipseLinkJpaVendorAdapter vendorAdaptor() {
        EclipseLinkJpaVendorAdapter vendorAdapter = new EclipseLinkJpaVendorAdapter();
        vendorAdapter.setShowSql(true);
        return vendorAdapter;
    }

    @Bean
    public InstrumentationLoadTimeWeaver weaver() {
        return new InstrumentationLoadTimeWeaver();
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        return dataSource;
    }


    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdaptor());
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(PersistenceProvider.class);
        entityManagerFactoryBean.setJpaProperties(jpaEclipselinkProperties());
        entityManagerFactoryBean.setPackagesToScan(Book.class.getPackage().getName());
        entityManagerFactoryBean.setLoadTimeWeaver(weaver());
        return entityManagerFactoryBean;
    }

    private Properties jpaEclipselinkProperties() {

        Properties properties = new Properties();
        properties.put(PersistenceUnitProperties.TARGET_DATABASE, TargetDatabase.MySQL);
        properties.put(PersistenceUnitProperties.DDL_GENERATION, "drop-and-create-tables");
        properties.put(PersistenceUnitProperties.JAVASE_DB_INTERACTION, true);
        properties.put(EC_WEAVING_PROP_LOG_CACHE, EC_WEAVING_PROP_LOG_CACHE_VALUE);
        properties.put(EC_WEAVING_PROP_LOG, EC_WEAVING_PROP_LOG_VALUE);
        properties.put(EC_WEAVING_PROP_SQL_LOG, EC_WEAVING_PROP_SQL_LOG_VALUE);
        properties.put(EC_WEAVING_PROP_WEAVING, EC_WEAVING_PROP_WEAVING_VALUE);
        properties.put(EC_WEAVING_PROP_INTERNAL_WEAVING, EC_WEAVING_PROP_INTERNAL_WEAVING_VALUE);
        /*properties.put(PersistenceUnitProperties.PERSISTENCE_CONTEXT_CLOSE_ON_COMMIT, "true");
        properties.put(PersistenceUnitProperties.PERSISTENCE_CONTEXT_PERSIST_ON_COMMIT, "false");
        properties.put(PersistenceUnitProperties.PERSISTENCE_CONTEXT_COMMIT_WITHOUT_PERSIST_RULES, "false");*/
        return properties;
    }

}
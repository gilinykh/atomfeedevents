package com.example.atomfeedclient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ict4h.atomfeed.client.AtomFeedProperties;
import org.ict4h.atomfeed.client.domain.Event;
import org.ict4h.atomfeed.client.service.EventWorker;
import org.ict4h.atomfeed.jdbc.AtomFeedJdbcTransactionManager;
import org.ict4h.atomfeed.jdbc.JdbcConnectionProvider;
import org.ict4h.atomfeed.transaction.AFTransactionManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.URI;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

@SpringBootApplication
@ImportResource({"classpath:atomFeedClientContext.xml"})
public class AtomfeedclientApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtomfeedclientApplication.class, args);
    }

    @Bean
    public JdbcConnectionProvider jdbcConnectionProvider(DataSource dataSource) {
        return () -> DataSourceUtils.doGetConnection(dataSource);
    }

    @Bean
    public URI feedUri() {
        return URI.create("http://localhost:8080/feed/recent");
    }

    @Bean
    public EventWorker feedWorker(ObjectMapper objectMapper) {
        return new EventWorker() {
            @Override
            public void process(Event event) {
                HashMap<String, Object> content = null;

                try {
                    content = objectMapper.readValue(event.getContent(), new TypeReference<HashMap<String, Object>>() {});
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Processed event: [" + event + "] with content: [" + content + "]");
            }

            @Override
            public void cleanUp(Event event) {
                System.out.println("Cleaned up event: [" + event + "]");
            }
        };
    }

    @Bean
    public AtomFeedProperties atomFeedProperties() {
        return new AtomFeedProperties();
    }

    @Bean
    public AFTransactionManager afTransactionManager(JdbcConnectionProvider jdbcConnectionProvider) {
        return new AtomFeedJdbcTransactionManager(jdbcConnectionProvider);
    }
}

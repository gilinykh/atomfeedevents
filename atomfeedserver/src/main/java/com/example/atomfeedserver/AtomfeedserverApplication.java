package com.example.atomfeedserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.WebContentInterceptor;

@SpringBootApplication
//@Import({
//        // security
////        WebSecurityConfigurer.class,
////        AuthorizationServerConfig.class,
////        ResourceServerConfig.class,
//
//        // web
////        AtomfeedserverApplication.WebMvcConfig.class,
////        WebConfiguration.class,
////        CorsConfig.class,
//        WebMvcAutoConfiguration.class,
//        DispatcherServletAutoConfiguration.class,
////        EmbeddedServletContainerAutoConfiguration.class,
////        ServerPropertiesAutoConfiguration.class,
//        ErrorMvcAutoConfiguration.class,
//        HttpEncodingAutoConfiguration.class,
//        HttpMessageConvertersAutoConfiguration.class,
//        JacksonAutoConfiguration.class,
//
//        // persistence
//        DataSourceAutoConfiguration.class,
////        HibernateJpaAutoConfiguration.class,
//        LiquibaseAutoConfiguration.class
//})

@ImportResource("classpath:atomFeedContext.xml")
public class AtomfeedserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtomfeedserverApplication.class, args);
    }

//    @Configuration
//    public static class WebMvcConfig implements WebMvcConfigurer {
//
//        @Override
//        public void addInterceptors(InterceptorRegistry registry) {
//            WebContentInterceptor nonCachingInterceptor = new WebContentInterceptor();
//            nonCachingInterceptor.addCacheMapping(CacheControl.noCache(), "/feed/recent");
//
//            registry.addInterceptor(nonCachingInterceptor);
//        }
//    }
}

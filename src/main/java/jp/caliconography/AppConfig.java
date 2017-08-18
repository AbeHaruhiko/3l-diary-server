package jp.caliconography;

import net.sf.log4jdbc.sql.jdbcapi.DataSourceSpy;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.sql.DataSource;

@Configuration
public class AppConfig {
    @Inject
    DataSourceProperties dataSourceProperties;

//    @Named
    @Component
    static class JerseyConfig extends ResourceConfig {
        public JerseyConfig() {
            this.packages("jp.caliconography");
            property(ServletProperties.FILTER_FORWARD_ON_404, true);
//            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//            CorsConfiguration config = new CorsConfiguration();
//            config.setAllowCredentials(true);
//            config.addAllowedOrigin("http://localhost:8888");
//            config.addAllowedHeader("*");
//            config.addAllowedMethod("*");
//            source.registerCorsConfiguration("/**", config);
//            register(new CORSFilter());
        }
    }

    @Bean
    DataSource dataSource() {
        DataSource dataSource = DataSourceBuilder
                .create(this.dataSourceProperties.getClassLoader())
                .url(this.dataSourceProperties.getUrl())
                .username(this.dataSourceProperties.getUsername())
                .password(this.dataSourceProperties.getPassword())
                .build();
        return new DataSourceSpy(dataSource);
    }

//    @Bean
//    public FilterRegistrationBean urlBasedCorsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("http://localhost:8888");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config);
//        FilterRegistrationBean bean = new FilterRegistrationBean();
//        bean.setFilter(new CorsFilter(source));
//        bean.setOrder(0);
//        return bean;
//    }
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/.*").allowedOrigins("http://localhost:8888");
//            }
//        };
//    }
}
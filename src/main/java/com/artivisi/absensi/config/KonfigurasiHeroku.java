package com.artivisi.absensi.config;

import java.net.URI;
import java.net.URISyntaxException;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration @Profile("heroku")
public class KonfigurasiHeroku {
    
    @Bean
    public DataSource datasourcePostgreHeroku() throws URISyntaxException{
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        return DataSourceBuilder.create().url(dbUrl)
                .username(username)
                .password(password)
                .build();
    }
}

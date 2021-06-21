package com.agenda.agenda;

import javax.sql.DataSource;
	
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class AgendaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgendaApplication.class, args);
	}
	 
	
	@Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
//        dataSource.setUrl("jdbc:postgresql://ec2-54-147-209-121.compute-1.amazonaws.com:5432/d2fbsp8uo7p4i1");
//        dataSource.setUsername("jgkjznnzcxydgv");
//        dataSource.setPassword("141c54253f2b87560fdbb475b835e81d390af18b9b8bad0613f4eb4a314bdc85");

        dataSource.setUrl("jdbc:postgresql://localhost:5432/AGENDAONLINE");
        dataSource.setUsername("postgres");
        dataSource.setPassword("123");
        
        return dataSource;
    }

}

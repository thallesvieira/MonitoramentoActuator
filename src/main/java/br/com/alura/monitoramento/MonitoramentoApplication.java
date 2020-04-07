package br.com.alura.monitoramento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import br.com.alura.monitoramento.security.config.SecurityConfigurations;
import de.codecentric.boot.admin.server.config.EnableAdminServer;

@Configuration
@EnableAutoConfiguration
@EnableAdminServer
@Import({SecurityConfigurations.class})
public class MonitoramentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitoramentoApplication.class, args);
	}

}

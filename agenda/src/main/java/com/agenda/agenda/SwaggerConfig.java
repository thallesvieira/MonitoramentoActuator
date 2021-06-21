package com.agenda.agenda;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.agenda.agenda.resource.persistence.entity.User;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
    public Docket Apis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.agenda.agenda"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaInfo().build())
                .ignoredParameterTypes(User.class)
                .globalOperationParameters(Arrays.asList(
                		new ParameterBuilder()
                		.name("Authorization")
                		.description("Header to token jwt")
                		.modelRef(new ModelRef("String"))
                		.parameterType("header")
                		.required(false)
                		.build()));
    }
	
	private ApiInfoBuilder metaInfo() {

		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        		 
		apiInfoBuilder.title("Projeto API agendamento online");
		apiInfoBuilder.description("Api para auxiliar com requisições do sistema agendamento online.");
		apiInfoBuilder.version("1.0");
		apiInfoBuilder.termsOfServiceUrl("Termos de serviço");
		apiInfoBuilder.contact(this.contato());
                

        return apiInfoBuilder;
    }
	
	private Contact contato() {
		 
		return new Contact(
				"Thalles Jacobs Vieira",
				"", 
				"thalles_jacobs@hotmail.com");
	}
	
}

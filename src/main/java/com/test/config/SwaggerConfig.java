package com.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter  {

	  @Bean
	    public Docket api() { 
//		  return new Docket(DocumentationType.SWAGGER_2).
//				  select().build()
//				  .apiInfo(apiInfo());   
		  
		  return new Docket(DocumentationType.SWAGGER_2).select()
	                // .apis(RequestHandlerSelectors.any())
	                .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
	                // .paths(PathSelectors.any())
	                // .paths(PathSelectors.ant("/swagger2-demo"))
	                .build().apiInfo(apiInfo());
	    }
	  
	 
        @Bean
        public ApiInfo apiInfo() {
			Contact contact=new Contact("Akash Dubey", "https://salesken.ai", "akki136@gmail.com");
			return new ApiInfoBuilder().title("Crud SalesKen API")
					.description("Crud SalesKen API reference for developers")
					.termsOfServiceUrl("https://salesken.ai/")
					.contact(contact).version("0.1").build();
		}
        
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("swagger-ui.html")
              .addResourceLocations("classpath:/META-INF/resources/");

            registry.addResourceHandler("/webjars/**")
              .addResourceLocations("classpath:/META-INF/resources/webjars/");
        }


		
}



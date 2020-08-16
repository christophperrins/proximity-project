package uk.co.bpdts.proximity.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import uk.co.bpdts.proximity.mapping.StringToLengthUnitConverter;
import uk.co.bpdts.proximity.utils.ReadConfig;

@Configuration
@EnableSwagger2
public class Config implements WebMvcConfigurer {

	/**
	 * Adds formatters for enum's so that string values for enums can be easily converted
	 */
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new StringToLengthUnitConverter());
	}

	/**
	 * ModelMapper is a semi-fast object mapping library
	 * @return modelMapper
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	/**
	 * Swagger Bean necessary to create a frontend swagger page for users.
	 * @return
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				.paths(PathSelectors.any())
				.build();
	}
	
	/**
	 * Helper class to read in config in application.properties
	 * @return
	 */
	@Bean
	public ReadConfig readConfig() {
		return new ReadConfig();
	}
}

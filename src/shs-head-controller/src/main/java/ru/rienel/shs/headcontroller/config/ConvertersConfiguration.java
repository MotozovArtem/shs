package ru.rienel.shs.headcontroller.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import ru.rienel.shs.headcontroller.domain.converter.ResourceTypeWebConverter;

@Configuration
public class ConvertersConfiguration extends WebMvcConfigurationSupport {
	@Override
	public FormattingConversionService mvcConversionService() {
		FormattingConversionService formattingConversionService = super.mvcConversionService();
		formattingConversionService.addConverter(new ResourceTypeWebConverter());
		return formattingConversionService;
	}
}

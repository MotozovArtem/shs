package ru.rienel.shs.headcontroller.domain.converter;

import org.springframework.core.convert.converter.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.rienel.shs.headcontroller.domain.ResourceType;

public class ResourceTypeWebConverter implements Converter<String, ResourceType> {
	private static final Logger log = LoggerFactory.getLogger(ResourceTypeWebConverter.class);

	@Override
	public ResourceType convert(String s) {
		ResourceType target = null;
		try {
			target = ResourceType.valueOf(s.toUpperCase());
		} catch (Exception e) {
			log.warn("Exception during converting to ResourceType {}", e.getLocalizedMessage());
		}
		return target;
	}
}

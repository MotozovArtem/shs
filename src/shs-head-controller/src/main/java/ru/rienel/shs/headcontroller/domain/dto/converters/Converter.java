package ru.rienel.shs.headcontroller.domain.dto.converters;

public interface Converter<Domain, Dto> {
	Domain fromDto(Dto dto);

	Dto fromDomain(Domain domain);
}

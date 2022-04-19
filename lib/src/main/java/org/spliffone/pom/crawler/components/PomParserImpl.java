package org.spliffone.pom.crawler.components;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spliffone.pom.crawler.dto.pom.Pom;

public class PomParserImpl implements PomParser {
	private static final Logger LOGGER = LoggerFactory.getLogger(PomParserImpl.class);

	private final XmlMapper mapper;

	public PomParserImpl() {
		this.mapper = XmlMapper.builder().defaultUseWrapper(true).build();
	}

	@Override
	public Pom unmarshal(String pomXML) {
		try {
			LOGGER.debug("Parse POM\n{}", pomXML);
			return mapper.readValue(pomXML, Pom.class);
		} catch (IOException e) {
			LOGGER.error("Unexpected exception while parsing pom", e);
			throw new RuntimeException("Unexpected exception while parsing", e);
		}
	}
}

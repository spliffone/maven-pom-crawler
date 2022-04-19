package org.spliffone.pom.crawler.components;

import org.spliffone.pom.crawler.dto.pom.Pom;

public interface PomParser {
	/**
	 * Unmarshal pom xml into a Java object
	 *
	 * @param pomXML
	 *            pom xml as UTF-8 string
	 * @return unmarshalled Java object
	 */
	Pom unmarshal(String pomXML);
}

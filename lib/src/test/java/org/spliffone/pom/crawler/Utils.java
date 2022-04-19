package org.spliffone.pom.crawler;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;

public class Utils {

	public InputStream getResourceAs(Class clazz, String name) {
		InputStream is = clazz.getResourceAsStream(name);
		assertThat(is).as("Should find resource %s", name).isNotNull();
		return is;
	}

	public String getResourceAsString(Class clazz, String name) throws IOException {
		InputStream is = clazz.getResourceAsStream(name);
		assertThat(is).as("Should find resource %s", name).isNotNull();
		return IOUtils.toString(is, "UTF-8");
	}
}

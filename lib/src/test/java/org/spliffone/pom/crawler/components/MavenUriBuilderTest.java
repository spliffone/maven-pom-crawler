package org.spliffone.pom.crawler.components;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.URISyntaxException;
import org.junit.jupiter.api.Test;

class MavenUriBuilderTest {

	@Test
	void testBuildUrl() throws URISyntaxException {
		MavenUriBuilder builder = new MavenUriBuilder();
		URI pom = builder.groupId("org.springframework").artifactId("spring").version("5.3.19").buildPomUri();
		assertThat(pom).isNotNull();
		assertThat(pom).isEqualTo(
				new URI("https://repo1.maven.org/maven2/org/springframework/spring/5.3.19/spring-5.3.19.pom"));
	}
}

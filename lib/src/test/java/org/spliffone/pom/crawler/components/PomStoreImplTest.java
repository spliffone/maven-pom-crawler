package org.spliffone.pom.crawler.components;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.spliffone.pom.crawler.dto.pom.MavenId;
import org.spliffone.pom.crawler.dto.pom.Pom;
import org.spliffone.pom.crawler.dto.pom.Scm;

class PomStoreImplTest {
	private final String GROUP_ID = "org.springframework.boot";
	private final String baseSourceURL = "https://github.com/spring/";

	PomStore sut = new PomStoreImpl();

	Pom createPom(String artifactId, String version) throws MalformedURLException {
		return Pom.builder().groupId(GROUP_ID).artifactId(artifactId).version(version)
				.description(RandomStringUtils.randomAlphabetic(20))
				.scm(Scm.builder().url(new URL(baseSourceURL + RandomStringUtils.randomAlphabetic(10))).build())
				.build();
	}

	@Test
	void testMavenIdIsInStore() throws MalformedURLException {
		Pom springBoot = createPom("spring-boot", "2.6.6");
		sut.store(springBoot);

		assertThat(sut.hasMavenId(springBoot)).as("Should find maven id").isTrue();
		assertThat(
				sut.hasMavenId(MavenId.builder().groupId(GROUP_ID).artifactId("spring-boot").version("2.6.6").build()))
						.as("Should find maven id").isTrue();
		assertThat(
				sut.hasMavenId(MavenId.builder().groupId(GROUP_ID).artifactId("spring-boot").version("2.6.7").build()))
						.as("Shouldn't find maven id").isFalse();
	}
}

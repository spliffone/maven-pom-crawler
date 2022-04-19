package org.spliffone.pom.crawler.components;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.spliffone.pom.crawler.Utils;
import org.spliffone.pom.crawler.dto.pom.Pom;

class PomParserImplTest {

	private static Utils utils = new Utils();

	private PomParser sut = new PomParserImpl();

	@Test
	void parseSpringBootPom() throws IOException {
		Pom pom = sut.unmarshal(utils.getResourceAsString(this.getClass(), "spring-boot.pom"));
		assertThat(pom).isNotNull();
		assertThat(pom.getLicenses().size()).isEqualTo(1);
		assertThat(pom.getLicenses().size()).isEqualTo(1);
		assertThat(pom.getDependencies().size()).isEqualTo(2);
		assertThat(pom.getScm()).isNotNull();
		assertThat(pom.getScm().getUrl()).isNotNull();
		assertThat(pom.getOrganization()).isNotNull();
	}
}

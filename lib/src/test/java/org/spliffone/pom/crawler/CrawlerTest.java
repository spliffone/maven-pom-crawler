package org.spliffone.pom.crawler;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spliffone.pom.crawler.components.MavenDownloader;
import org.spliffone.pom.crawler.dto.pom.MavenId;
import org.spliffone.pom.crawler.dto.pom.Pom;

class CrawlerTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(CrawlerTest.class);

	private final String GROUP_ID = "org.springframework.boot";

	static class TestDownloader implements MavenDownloader {

		byte[] getData(URI uri) {
			try {
				final String url = uri.toString();
				final String pomFile = url.substring(url.lastIndexOf("/") + 1);
				final String pomFileBaseName = pomFile.substring(0, pomFile.lastIndexOf("-"));
				final String data = (new Utils()).getResourceAsString(getClass(),
						"components/" + pomFileBaseName + ".pom");
				return data.getBytes(StandardCharsets.UTF_8);
			} catch (IOException e) {
				LOGGER.error("Unexpected exception", e);
				return null;
			}
		}

		@Override
		public CompletableFuture<byte[]> downloadPom(URI uri) {
			return CompletableFuture.supplyAsync(() -> getData(uri));
		}
	}

	@Test
	void testCrawlerReturnAllLinkedDependencies() throws URISyntaxException, ExecutionException, InterruptedException {
		MavenId id = MavenId.builder().groupId(GROUP_ID).artifactId("spring-boot").version("2.6.6").build();
		Crawler crawler = new Crawler(new TestDownloader());
		List<Pom> result = crawler.crawl(id);
		assertThat(result).isNotNull();
		assertThat(result.size()).isEqualTo(8);
	}
}

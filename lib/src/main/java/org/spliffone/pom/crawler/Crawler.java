package org.spliffone.pom.crawler;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spliffone.pom.crawler.components.MavenDownloader;
import org.spliffone.pom.crawler.components.MavenUriBuilder;
import org.spliffone.pom.crawler.components.PomParser;
import org.spliffone.pom.crawler.components.PomParserImpl;
import org.spliffone.pom.crawler.components.PomStore;
import org.spliffone.pom.crawler.components.PomStoreImpl;
import org.spliffone.pom.crawler.components.UrlCacheImpl;
import org.spliffone.pom.crawler.dto.pom.MavenId;
import org.spliffone.pom.crawler.dto.pom.Pom;

public class Crawler {
	private static final Logger LOGGER = LoggerFactory.getLogger(Crawler.class);

	final MavenDownloader downloader;
	final PomParser pomParser;
	final UrlCacheImpl urlCache;
	final PomStore pomStore;

	public Crawler(MavenDownloader downloader) {
		this.downloader = downloader;
		this.pomParser = new PomParserImpl();
		this.urlCache = new UrlCacheImpl();
		this.pomStore = new PomStoreImpl();
	}

	public List<Pom> crawl(MavenId mavenId) throws URISyntaxException, ExecutionException, InterruptedException {
		List<Pom> result = new ArrayList<>();

		Queue<MavenId> queue = new LinkedList<>();
		queue.add(mavenId);

		while (!queue.isEmpty()) {
			MavenId id = queue.remove();
			LOGGER.info("Process {}", id);
			URI uri = MavenUriBuilder.builder().mavenId(id).buildPomUri();

			byte[] downloadBuffer = urlCache.hasUrl(uri) ? urlCache.getContent(uri) : downloader.downloadPom(uri).get();
			urlCache.addUrl(uri, downloadBuffer);

			Pom pom = pomStore.hasMavenId(id)
					? pomStore.get(id)
					: pomParser.unmarshal(new String(downloadBuffer, StandardCharsets.UTF_8));
			pomStore.store(pom);

			result.add(pom);

			pom.getDependencies().forEach(d -> {
				if (!pomStore.hasMavenId(d)) {
					queue.add(d);
				}
			});
		}

		return result;
	}
}

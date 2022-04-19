package org.spliffone.pom.crawler.components;

import java.net.URI;
import java.util.concurrent.CompletableFuture;

public interface MavenDownloader {
	/**
	 * Download POM file from maven central
	 *
	 * @param uri
	 *            URL of the pom file to download
	 * @return content in UTF-8
	 */
	CompletableFuture<byte[]> downloadPom(URI uri);
}

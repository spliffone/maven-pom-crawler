package org.spliffone.pom.crawler.components;

import java.net.URI;

public interface UrlCache {
	boolean hasUrl(URI uri);

	byte[] getContent(URI uri);

	void addUrl(URI uri, byte[] response);
}

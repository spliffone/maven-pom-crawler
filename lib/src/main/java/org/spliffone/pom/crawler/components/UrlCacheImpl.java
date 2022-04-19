package org.spliffone.pom.crawler.components;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class UrlCacheImpl implements UrlCache {

	Map<URI, byte[]> cache = new HashMap<>();

	@Override
	public boolean hasUrl(URI uri) {
		return cache.containsKey(uri);
	}

	@Override
	public byte[] getContent(URI uri) {
		return cache.get(uri);
	}

	@Override
	public void addUrl(URI uri, byte[] response) {
		if (cache.containsKey(uri)) {
			cache.put(uri, response);
		}
	}
}

package org.spliffone.pom.crawler.components;

import java.net.URI;
import java.util.concurrent.CompletableFuture;
import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;
import org.apache.hc.client5.http.async.methods.SimpleRequestBuilder;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.core5.concurrent.FutureCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MavenDownloaderImpl implements MavenDownloader {
	private static final Logger LOGGER = LoggerFactory.getLogger(MavenDownloaderImpl.class);

	private final CloseableHttpAsyncClient client;

	public MavenDownloaderImpl(CloseableHttpAsyncClient client) {
		this.client = client;
	}

	@Override
	public CompletableFuture<byte[]> downloadPom(URI uri) {
		LOGGER.debug("Download {}", uri);
		CompletableFuture<byte[]> future = new CompletableFuture<>();
		client.execute(SimpleRequestBuilder.get(uri).build(), new FutureCallback<SimpleHttpResponse>() {
			@Override
			public void completed(SimpleHttpResponse result) {
				if (successful(result.getCode())) {
					future.complete(result.getBodyBytes());
				} else {
					this.failed(new Exception("Unexpected error of HTTP GET " + uri + "status: " + result.getCode()));
				}
			}

			@Override
			public void failed(Exception ex) {
				future.completeExceptionally(ex);
			}

			@Override
			public void cancelled() {
				LOGGER.info("Request canceled");
			}
		});

		return future;
	}

	/**
	 * Verify the HTTP response status code.
	 *
	 * @param statusCode
	 *            HTTP status
	 * @return successful or failed request.
	 */
	protected boolean successful(int statusCode) {
		return statusCode >= 200 && statusCode < 400;
	}
}

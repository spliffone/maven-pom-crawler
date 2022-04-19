package org.spliffone.pom.crawler.components;

import java.net.URI;
import java.net.URISyntaxException;
import org.spliffone.pom.crawler.dto.pom.MavenId;

public class MavenUriBuilder {
	public static String BASE_URL = "https://repo1.maven.org/maven2";
	MavenId mavenId = new MavenId();

	public static MavenUriBuilder builder() {
		return new MavenUriBuilder();
	}

	public MavenUriBuilder mavenId(MavenId id) {
		this.mavenId = id;
		return this;
	}

	public MavenUriBuilder groupId(String groupId) {
		mavenId.setGroupId(groupId);
		return this;
	}

	public MavenUriBuilder artifactId(String artifactId) {
		mavenId.setArtifactId(artifactId);
		return this;
	}

	public MavenUriBuilder version(String version) {
		mavenId.setVersion(version);
		return this;
	}

	public URI buildPomUri() throws URISyntaxException {
		final String pom = String.format("%s/%s/%s/%s/%s-%s.pom", BASE_URL, mavenId.getGroupId().replaceAll("\\.", "/"),
				mavenId.getArtifactId(), mavenId.getVersion(), mavenId.getArtifactId(), mavenId.getVersion());
		return new URI(pom);
	}
}

package org.spliffone.pom.crawler.dto.pom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class MavenId {
	/**
	 * Uniquely identifies your project across all projects. This means it starts
	 * with a reversed domain name you control.
	 */
	private String groupId;
	/** Is the name of the jar without version. */
	private String artifactId;

	/** Unique version of the distribution (1.0, 1.1, 1.0.1, ...). */
	private String version;

	public String getMavenId() {
		return new StringBuilder().append(getGroupId()).append(":").append(getArtifactId()).append(":")
				.append(getVersion()).toString();
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

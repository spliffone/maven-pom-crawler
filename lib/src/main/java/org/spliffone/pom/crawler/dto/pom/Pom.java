package org.spliffone.pom.crawler.dto.pom;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
public class Pom extends MavenId implements CommonInfo {
	@JacksonXmlProperty(isAttribute = true)
	private String schemaLocation;

	@JacksonXmlProperty(isAttribute = true)
	private String modelVersion;

	private String name;
	private String description;
	private URL url;
	private Organisation organization;
	@Builder.Default
	List<License> licenses = new ArrayList<>();
	Scm scm;
	@Builder.Default
	List<Dependency> dependencies = new ArrayList<>();
}

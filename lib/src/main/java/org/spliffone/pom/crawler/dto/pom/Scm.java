package org.spliffone.pom.crawler.dto.pom;

import java.net.URL;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Scm {
	String connection;
	String developerConnection;
	URL url;
}

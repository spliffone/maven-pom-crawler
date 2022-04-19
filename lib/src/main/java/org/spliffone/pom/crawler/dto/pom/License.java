package org.spliffone.pom.crawler.dto.pom;

import java.net.URL;
import lombok.Data;

@Data
public class License {
	private String name;
	private URL url;
	private String distribution;
}

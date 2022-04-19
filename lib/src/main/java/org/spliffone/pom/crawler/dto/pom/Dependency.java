package org.spliffone.pom.crawler.dto.pom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Dependency extends MavenId {
	/**
	 * This attribute is used to specify the visibility of a dependency, relative to
	 * the different lifecycle phases (build, test, runtime etc).
	 */
	private String scope;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

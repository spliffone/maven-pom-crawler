package org.spliffone.pom.crawler.components;

import org.spliffone.pom.crawler.dto.pom.MavenId;
import org.spliffone.pom.crawler.dto.pom.Pom;

public interface PomStore {

	/**
	 * Add a pom object to store
	 *
	 * @param pom
	 *            object to store
	 */
	void store(Pom pom);

	/**
	 * Indicates either the maven id including the pom object is in the store.
	 *
	 * @param mavenId
	 *            id of an artifact (group, artifact and version)
	 * @return is in store
	 */
	boolean hasMavenId(MavenId mavenId);

	/**
	 * Get the pom object which belong to the id.
	 *
	 * @param mavenId
	 *            id of an artifact (group, artifact and version)
	 * @return pom object obtained from the store.
	 */
	Pom get(MavenId mavenId);
}

package org.spliffone.pom.crawler.components;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.spliffone.pom.crawler.dto.pom.MavenId;
import org.spliffone.pom.crawler.dto.pom.Pom;

public class PomStoreImpl implements PomStore {
	Map<String, Pom> pomStore = new HashMap<>();

	@Override
	public void store(Pom pom) {
		if (Objects.nonNull(pom)) {
			pomStore.put(pom.getMavenId(), pom);
		}
	}

	@Override
	public boolean hasMavenId(MavenId mavenId) {
		return pomStore.containsKey(mavenId.getMavenId());
	}

	@Override
	public Pom get(MavenId mavenId) {
		if (hasMavenId(mavenId)) {
			return pomStore.get(mavenId.getMavenId());
		} else {
			return null;
		}
	}
}

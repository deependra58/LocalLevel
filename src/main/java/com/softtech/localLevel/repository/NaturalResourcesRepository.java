package com.softtech.localLevel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softtech.localLevel.model.NaturalResources;
import com.softtech.localLevel.model.State;

@Repository
public interface NaturalResourcesRepository extends JpaRepository<NaturalResources,Long> {

	NaturalResources findByRiver(String river_name);

	NaturalResources findBylake(String lake_name);

	NaturalResources findByWaterfalls(String waterfall_name);

	NaturalResources findByProtectedAreas(String protectedArea_name);

	NaturalResources findByMountain(String mountains);

	List<NaturalResources> findAllByState(State state);

}

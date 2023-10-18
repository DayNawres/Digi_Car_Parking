package com.remotehiring.Missions;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface MissionsRepository extends JpaRepository<Missions, Integer>{
	@Query("Select b from Missions  b where b.email = ?1")
	List<Missions> listByUsers(String email);
	
	@Modifying
	@Transactional
	@Query("Update Missions b set b.paid = 1 where b.missionid = ?1")
	int endMission(Integer missionid);

}

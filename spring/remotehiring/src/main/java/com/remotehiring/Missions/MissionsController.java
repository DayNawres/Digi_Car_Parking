package com.remotehiring.Missions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class MissionsController {

	@Autowired
	private MissionsService service;
	
	@PostMapping("/missions/add")
	public Missions add(@RequestBody Missions mission) {
		 return service.add(mission);
	}
	
	@GetMapping("/missions")
	public List<Missions> listAll(){
		return service.listAll();
	}
	
	@GetMapping("missionss/getByUser/{email}")
	public List<Missions> listByUsers(@PathVariable String email){
		return service.listByUsers(email);
	}
	
	@GetMapping("/missions/endMission/{missionid}")
	public boolean endMission(@PathVariable Integer missionid) {
		return service.endMission(missionid);
	}
	
	@GetMapping("/missions/allMissions")
	public List<Missions> allMissions(){
		return service.listAll();
	}
	
}

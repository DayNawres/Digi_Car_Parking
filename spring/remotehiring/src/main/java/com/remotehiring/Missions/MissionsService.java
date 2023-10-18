package com.remotehiring.Missions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.remotehiring.Mail.MailService;
import com.remotehiring.Slots.SlotsService;
import com.remotehiring.Vehicle.VehicleService;

@Service
public class MissionsService {
	
	@Autowired
	private MissionsRepository repo;
	@Autowired
	private SlotsService slotservice;
	
	@Autowired
	private VehicleService vehicleservice;
	
	@Autowired
	private MailService mailservice;
	
	public Missions add(Missions mission) {
		
		Missions currentMission =  repo.save(mission);
//		mailservice.sendEmail(currentMission);
		slotservice.updateSlot(mission);
		return currentMission;
	}
		
	public List<Missions> listAll(){
		return repo.findAll();
	}
	
	
	public List<Missions> listByUsers(String email){
		return repo.listByUsers(email);
	}
	
	public boolean endMission(Integer missionid) {
		
		repo.endMission(missionid);
		
		String[] time1 = getTime();
		String[] date1 = getDate();
		
		
		
		String missionTime = repo.findById(missionid).get().getTime();
		
		String missionDate = repo.findById(missionid).get().getDate();
		
		String[] date2 = splitDate(missionDate);
		
		String[] time2 = splittime(missionTime);
		
		int duration = getDuration(time1,time2,date1,date2);
		
		
		Missions currentMission = repo.findById(missionid).get();
		
		currentMission.setDuration(duration);
		
		int vehicleCost =  vehicleservice.getVehicleCost(currentMission.getVehicle_type());
		
		int cost = duration*vehicleCost;
		currentMission.setCost(String.valueOf(cost));
		
		repo.save(currentMission);
		slotservice.rollbackSlot(currentMission.getSlotid());
		
		return true;
	}
	
	public String[] getDate() {
		SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        sd.setTimeZone(TimeZone.getTimeZone("IST"));
        String time = sd.format(date);
        String[] datearr = time.split("-");
        return (datearr);
	}
	
	public String[] getTime() {
		SimpleDateFormat sd = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        sd.setTimeZone(TimeZone.getTimeZone("IST"));
        String time = sd.format(date);
        String[] timearr = time.split(":");
        return (timearr);
	}
	
	public String[] splitDate(String date) {
		return date.split("-");
	}
	
	public String[] splittime(String time) {
		return  time.split(":");
	}
	
	public int getDuration(String[] time1,String[] time2, String[] date1, String[] date2) {
		int d1 = Integer.parseInt(date1[0]);
		int d2 = Integer.parseInt(date2[0]);
		int t1 = Integer.parseInt(time1[0]);
		int t2 = Integer.parseInt(time2[0]);
		int time = Math.abs(d1-d2) * 24;
		return Math.abs(t1-t2) + time;
	}
}

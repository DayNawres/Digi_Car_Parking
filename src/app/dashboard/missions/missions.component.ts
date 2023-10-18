import { Component, OnInit } from '@angular/core';
import { Missions } from 'src/app/models/missions.model';
import { MissionsService } from 'src/app/services/missions.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-missions',
  templateUrl: './missions.component.html',
  styleUrls: ['./missions.component.css']
})
export class MissionsComponent implements OnInit {

  show: boolean;
  email$ =  sessionStorage.getItem('email');
  missions$: Missions[] = [];

  constructor(private missionsService: MissionsService,
    public router: Router) { }

  ngOnInit() {
    this.checkLogin();
    this.getMissionById();
    
  }

  getMissionById(){
    return this.missionsService.getMissions(this.email$)
    .subscribe(data => {this.missions$ = data, this.checkMissionFn();})
  }

  endMission(missionid){
    return this.missionsService.endMission(missionid)
    .subscribe((data:{}) => {
      alert('Mission Ended');
      location.reload();
      this.router.navigate(['dashboard/missions'])
    })
  }
  checkMissionFn(){
    console.log()
    if (this.missions$.length == 0){
      this.show = true
    }
    else{
      this.show = false
    }
  }

  checkLogin(){
    if (sessionStorage.length == 0){
      this.router.navigate(['login']);
    }
  }

}

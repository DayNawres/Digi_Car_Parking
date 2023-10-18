import { Component, OnInit } from '@angular/core';
import { Missions } from 'src/app/models/missions.model';
import { MissionsService } from 'src/app/services/missions.service';

@Component({
  selector: 'app-allmissions',
  templateUrl: './allmissions.component.html',
  styleUrls: ['./allmissions.component.css']
})
export class AllmissionsComponent implements OnInit {

  AllMissions$ : Missions[];

  constructor(private missionsService : MissionsService) { }

  ngOnInit(): void {
    this.getAllMissions();
  }

  getAllMissions(){
    return this.missionsService.getAllMissions()
    .subscribe(data => this.AllMissions$ = data)
  }

}

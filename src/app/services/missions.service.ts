import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Missions } from '../models/missions.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MissionsService {

  apiUrl = 'http://localhost:8080'
  
  httpOptions = {
    headers :new HttpHeaders({
      'Content-Type':'application/json'
    })    
  }

  constructor(private _http: HttpClient) { }

  getAllMissions(){
    return this._http.get<Missions[]>(this.apiUrl+'/missions/allMissions')
  }

  getMissions(email){
    return this._http.get<Missions[]>(this.apiUrl+'/missions/getByUser/'+email);
  }

  addMission(id,missions):Observable<Missions>{
    missions.locationid = id;
    missions.email = sessionStorage.getItem('email');
    console.log(missions);
    return this._http.post<Missions>(this.apiUrl+'/missions/add', JSON.stringify(missions), this.httpOptions );
  }

  endMission(missionid){
    return this._http.get<Boolean>(this.apiUrl+'/missions/endMission/'+missionid);
  }

}

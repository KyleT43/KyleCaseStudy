import { Injectable } from '@angular/core';


import { HttpClient } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';
import {HttpResponse} from '@angular/common/http';
import {map} from 'rxjs/operators';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class DataService {

  url:any = "http://localhost:8080/flightreservation/reservations/";

  
  constructor(private http: HttpClient) { }




  getReservation(id:number){
return this.http.get(this.url + id)
// .subscribe(response => console.log(response))
  }

 checkin(checkInRequest:any){
   return this.http.post(this.url,checkInRequest)
  //  .subscribe(response => console.log(response))

 }


}

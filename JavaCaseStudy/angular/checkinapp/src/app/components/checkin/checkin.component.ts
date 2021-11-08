import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Data } from '@angular/router';
import { DataService } from 'src/app/services/data.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-checkin',
  templateUrl: './checkin.component.html',
  styleUrls: ['./checkin.component.css']
})
export class CheckinComponent implements OnInit {

  data:any
  respo:any
  noOfBags:any
  constructor(private route:ActivatedRoute, 
    private service:DataService, 
    private router:Router) { }

  ngOnInit(): void {

    var id:any = this.route.snapshot.paramMap.get("id");
    this.service.getReservation(id).subscribe(res=>{
    this.data = res;
    console.log(this.data)

   }) }

   checkin(noOfBags:number){
    var checkInRequest = {
    id: this.data.id,
    checkedIn: true,
    numberOfBags: noOfBags
     }


    this.service.checkin(checkInRequest).subscribe((res)=>{
      this.respo = res;
      
    });

    this.router.navigate(['/confirm']);
  }

}

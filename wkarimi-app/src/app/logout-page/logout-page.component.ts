import { Component, OnInit } from '@angular/core';
import { LoginDataService } from '../services/login/login-data.service';

@Component({
  selector: 'app-logout-page',
  standalone:true,
  imports: [],
  templateUrl: './logout-page.component.html',
  styleUrl: './logout-page.component.css'
})
export class LogoutPageComponent implements OnInit{
  constructor(private service:LoginDataService){}
  ngOnInit(): void {
    this.service.logout();
  }



}

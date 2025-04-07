
import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { LoginDataService } from '../services/login/login-data.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-header',
  standalone:true,
  imports: [RouterLink,CommonModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit{
  constructor(private loginService:LoginDataService){}
  userLoggedIn=false;
  ngOnInit(): void {
    this.loginService.isLoggedIn$.subscribe(status=>{
      this.userLoggedIn = status;
    })
  }

}

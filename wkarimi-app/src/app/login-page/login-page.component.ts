import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import {FormsModule, NgForm} from '@angular/forms';
import { LoginDataService } from '../services/login/login-data.service';
// import { LoginDataService } from '../services/login/login-data.service';
// import { User } from '../services/entity/user';
@Component({
  selector: 'app-login-page',
  standalone:true,
  imports: [RouterLink,CommonModule,FormsModule],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.css'
})
export class LoginPageComponent {
  constructor(
    private serviceUser:LoginDataService,
    private router:Router,
    private route:ActivatedRoute
  ){}
  private userName!:string;
  private password!:string;
  private email!:string;
  public errorMessage="";

  private isLoggedIn!:boolean;
  loginUser(formValue:NgForm){
    
    this.email = formValue.value['email'];
    this.password = formValue.value['password'];
    
    this.serviceUser.getUser(this.email,this.password).subscribe({
      next:
      (response) => {
        if(response.ok){
          // sessionStorage.setItem('authenticateUser',this.email);
          this.serviceUser.loginUser(this.email);

          const returnUrl = this.route.snapshot.queryParams['returnUrl'] || 'home';
        this.router.navigate([returnUrl]);
        }
          
      
      },
      error: (err)=>{
        if (err.status === 404) {
          this.errorMessage = "Email ID does not exist.";
      } else if (err.status === 401) {
          this.errorMessage = "Wrong Email or Password";
      } else {
          this.errorMessage = "Server error. Please try again";
      }
      }
      }
      
    );
  }
  
  


}

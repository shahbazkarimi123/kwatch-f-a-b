import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule,NgForm } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { SignupDataService } from '../services/singup/signup-data.service';
import { User } from '../services/entity/user';
import { LoginDataService } from '../services/login/login-data.service';



@Component({
  selector: 'app-signup-page',
  standalone:true,
  imports: [RouterLink,CommonModule,FormsModule
  ],
  templateUrl: './signup-page.component.html',
  styleUrl: './signup-page.component.css'
})
export class SignupPageComponent {
  constructor(
    private singup:SignupDataService,
    private router:Router,
    private loginService:LoginDataService
    
  ){}

  public errorMessage!:string;
  public fullName!:string;
  public email!:string;
  public password!:string;
  public contact!:string;
  public address!:string;


  createUser(singupForm:NgForm){
    this.fullName=singupForm.value['fullName'];
    this.email = singupForm.value['email'];
    this.password = singupForm.value['password'];
    if(this.password.length<8){
      this.errorMessage="Password must be more than 8 character"
    }
    this.contact = singupForm.value['contactNumber'];
    this.address = singupForm.value['address'];
    let user = new User();
    user.fullName = this.fullName;
    user.email = this.email;
    user.password = this.password;
    user.contactNumber = this.contact;
    user.address = this.address;
    user.confirmPassword = this.password;
    user.userName=this.getUserName(this.fullName);
    
    this.singup.signupNewUser(user).subscribe(
      response=>{
        if(response.ok){
          this.loginService.loginUser(this.email);
          this.router.navigate(['home'])
        }
      }
    );
    

  }
  getUserName(userName:string):string{
    let tempUser:string=userName.replace(" ","");
    return tempUser;

  }
  
}

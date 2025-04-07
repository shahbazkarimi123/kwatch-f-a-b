import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../entity/user';
@Injectable({
  providedIn: 'root'
})
export class SignupDataService {

  constructor(private http:HttpClient) { }
  signupNewUser(user:User){
    const header = new HttpHeaders({
      'Content-Type': 'application/json'
    })
    
    return this.http.post("http://192.168.1.13:8000/users/register",user,{observe:'response',responseType:'text',headers:header});
  }
}

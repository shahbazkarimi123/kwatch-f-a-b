import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../entity/user';
// import { Observable } from 'rxjs';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginDataService {
  private loggedIn= new BehaviorSubject<boolean>(false);

  constructor(private http:HttpClient) {
    // const initialStatus = this.isUserLoggedIn();
    if(this.isUserLoggedIn()){
      this.loggedIn.next(true);
    }
    if (typeof window !== 'undefined' && typeof localStorage !== 'undefined') {
      window.addEventListener('storage', () => {
        this.loggedIn.next(this.isUserLoggedIn());
      });
    }
    
   }

  
  getUser(email:string,password:string){
    const header = new HttpHeaders({
      'Content-Type': 'application/json'
    })
    return this.http.post<User>('http://192.168.1.13:8000/users/login',{email,password}, { 
      observe: 'response',
      withCredentials: true,
      headers: header
    });
  }

  isUserLoggedIn():boolean{
    if (typeof window !== 'undefined' && typeof localStorage !== 'undefined') {
      return localStorage.getItem('authenticateUser') !== null;
    }
    return false;
  }
  logout(){
    if (typeof window !== 'undefined' && typeof localStorage !== 'undefined') {
      localStorage.removeItem('authenticateUser');
    }
    this.loggedIn.next(false);
  }
  get isLoggedIn$(){
    return this.loggedIn.asObservable();
  }
  loginUser(email:string){
    if (typeof window !== 'undefined' && typeof localStorage !== 'undefined') {
      localStorage.setItem('authenticateUser', email);
    }
    this.loggedIn.next(true);
  }
}
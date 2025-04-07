import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, GuardResult, MaybeAsync, Router, RouterStateSnapshot } from '@angular/router';
// import { LoginPageComponent } from '../../login-page/login-page.component';
import { LoginDataService } from '../login/login-data.service';


@Injectable({
  providedIn: 'root'
})
export class RouteGuardService implements CanActivate{

  constructor(private loginData:LoginDataService,
    private router:Router) { }



  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot){
    if(typeof window !== 'undefined' && typeof localStorage !== 'undefined'){
      const user = localStorage.getItem('authenticateUser');
      if(user){
        return true;
      }
    }
      
        this.router.navigate(['login'], { queryParams: { returnUrl: state.url } });
        return false;

      
    
    
  }
}

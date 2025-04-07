import { Routes } from '@angular/router';
import { LoginPageComponent } from './login-page/login-page.component';
import { SignupPageComponent } from './signup-page/signup-page.component';
import { HomePageComponent } from './home-page/home-page.component';
import { DetailsComponent } from './details/details.component';
import { AddProductComponent } from './add-product/add-product.component';
import { RouteGuardService } from './services/route/route-guard.service';
import { LogoutPageComponent } from './logout-page/logout-page.component';


export const routes: Routes = [
    {path:"",component:HomePageComponent},
    {path:"login",component:LoginPageComponent},
    {path:"signup",component:SignupPageComponent},
    {path:"logout",component:LogoutPageComponent},
    {path:"home",component:HomePageComponent},
    {path:"details/:productId",component:DetailsComponent,canActivate:[RouteGuardService]},
    {path:"add-product",component:AddProductComponent,canActivate:[RouteGuardService]}
];

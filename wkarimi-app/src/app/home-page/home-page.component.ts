import { Component, OnDestroy, OnInit } from '@angular/core';
import { Router, RouterLink, NavigationEnd} from '@angular/router';
import { Product } from '../services/entity/product';
import { CommonModule } from '@angular/common';
import { ProductDataService } from '../services/product/product-data.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [RouterLink,CommonModule],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css'
})
export class HomePageComponent implements OnInit,OnDestroy{
  public products:Product[] = [];
  private routerSubscription?: Subscription;
  constructor(private productService:ProductDataService,private router:Router){}
  ngOnDestroy(): void {
    
      this.routerSubscription?.unsubscribe();
  
    this.products=[];
  }
  ngOnInit(): void {
    this.getAllProduct();
    this.routerSubscription = this.router.events.subscribe(event=>{
      if(event instanceof NavigationEnd){
        this.getAllProduct();
      }
    })
  }



  
  

  getAllProduct(){
    this.productService.getAllProduct().subscribe(
      data=>{
        
        if(data){
          this.products=data;
          
        }
      },
      error=>{
        console.log('Error fetching product',error);
      }
    );
  }
  
}

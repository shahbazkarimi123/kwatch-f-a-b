import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ProductDataService } from '../services/product/product-data.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../services/entity/product';

@Component({
  selector: 'app-details',
  standalone:true,
  imports: [CommonModule],
  templateUrl: './details.component.html',
  styleUrl: './details.component.css'
})
export class DetailsComponent implements OnInit{
  constructor(private service:ProductDataService,
    private activatedRoute:ActivatedRoute,private router:Router
  ){}
  public product!:Product;
  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.params['productId'];
    this.service.getProductByProductId(id).subscribe(
      response=>{
        this.product = response;
        

        
      }
    );
    
    
  }


}

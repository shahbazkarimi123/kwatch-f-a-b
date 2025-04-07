import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Product } from '../services/entity/product';
import { ProductDataService } from '../services/product/product-data.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-add-product',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './add-product.component.html',
  styleUrl: './add-product.component.css'
})
export class AddProductComponent {
  constructor(private productService: ProductDataService,
    private router:Router
  ) { }
  public productName!: string;
  public title!:string;
  public brandName!: string;
  public fabric!: string;
  public color!: string[];
  public colorStr!: string;

  public productDescription!:string;
  public discount!:number;
  public comboProduct!:boolean;
  public cod!:boolean;
  public material!: string;
  public productWeight!: number;
  public packageWeight!: number;
  public category!: string;
  public categoryOption = ["Men's watch", "Women's watch", "Watch", "Specticle"]
  public length!: number;
  public breadth!: number;
  public height!: number;
  public volume!:number;
  public area!:number;
  public price!: number;
  public warranty: number | string = "None";
  public replacement: boolean | string = "None";
  public productReturn: boolean | string = "None";
  public selectedFile: File[] = [];
  previewUrl: string[] = [];
  private product = new Product();


  onFileSelected(event: any, index: number) {
    const file = event.target.files[0];
    if (!file) {
      console.warn(`No file selected for index ${index}`);
      return;
    }
    const reader = new FileReader();
    reader.onload = () => {
      this.previewUrl[index] = reader.result as string;
    };
    reader.readAsDataURL(file);
    this.selectedFile[index] = file;
  }

  calulateVol(){
    if(this.length>0 && this.breadth>0 && this.height){
      this.volume = this.length*this.breadth*this.height;
      this.area = 2*((this.length*this.breadth)+(this.breadth*this.height)+(this.height*this.length));
    }
  }



  createProduct() {
    this.product.productTitle=this.productName;
    this.product.productName = this.productName;
    this.product.brandName = this.brandName;
    

    this.product.fabric = this.fabric;
    
    this.product.productDescription=this.productDescription;
    this.product.material = this.material || "";
    this.product.productColor = this.color || [];
    this.product.productWeight = this.productWeight || 0;
    this.product.packageWeight = this.packageWeight || 0;
    this.product.category = this.category || "";
    this.product.productLength = this.length || 0;
    this.product.productBreadth = this.breadth || 0;
    this.product.productHeight = this.height || 0;
    this.product.price = this.price || 0;
    this.product.warranty = isNaN(Number(this.warranty)) ? 0 : Number(this.warranty);
    this.product.productReplace = this.replacement === "None" ? false : this.replacement == true ? true : false;
    this.product.productReturn = this.productReturn === "None" ? false : this.productReturn == true ? true : false;

    this.productService.createProduct(this.product, this.selectedFile).subscribe(
      response => {
        if(response){

          this.router.navigate(['home']);
        }
        
        
      }

    );
  }

  
  getProductByUserId(productId: number) {
    this.productService.getProductByProductId(productId).subscribe(
      response => {
        

      }

    );
  }
  

  
}



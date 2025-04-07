import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Product } from '../entity/product';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class ProductDataService {

  constructor(private http: HttpClient) { }

  createProduct(product: Product, selectedFiles: File[]): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('product', new Blob([JSON.stringify(product)], { type: 'application/json' }));
    const imageFields = ['productFrontImg', 'productBackImg', 'productSide1Img', 'productSide2Img', 'productAboveImg'];

    selectedFiles.forEach((file, index) => {
      if (file && imageFields[index]) {
        formData.append(imageFields[index], file);
      }
    })
    const header = new HttpHeaders({
      'Accept': 'application/json'
    })
    return this.http.post<Product>('http://192.168.1.13:8100/products', formData, { 
      headers: header
    });
  }

  getProductByProductId(productId:Number){
    return this.http.get<Product>(`http://192.168.1.13:8100/products/${productId}`)
  }
  getAllProduct(){
    const header = new HttpHeaders({
      'Accept': 'application/json'
    })
    return this.http.get<Product[]>('http://192.168.1.13:8100/products',{headers:header})
  }
}

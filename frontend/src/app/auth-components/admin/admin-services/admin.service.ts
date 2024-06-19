import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from '../../../auth-services/storage.service';

const BASIC_URL = "http://localhost:8080/"

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  postProduct(productDto: any): Observable<any> {
    console.log(productDto)
    return this.http.post<[]>(BASIC_URL + "api/admin/product", productDto,
    { 
      headers: this.createAuthorizationHeader() 
    })
  }

  postBatch(batchDto: any): Observable<any> {
    return this.http.post<[]>(BASIC_URL + "api/admin/batch", batchDto,
    {
      headers: this.createAuthorizationHeader()
    })
  }
  
  postStorage(storageDto: any): Observable<any> {
    return this.http.post<[]>(BASIC_URL + "api/admin/storage", storageDto,
    {
      headers: this.createAuthorizationHeader()
    })
  }

  getAllBatches(): Observable<any> {
    return this.http.get<[]>(BASIC_URL + "api/admin/batch",
      {
        headers: this.createAuthorizationHeader()
      })
  }

  getAllStorages(): Observable<any> {
    return this.http.get<[]>(BASIC_URL + "api/admin/storage",
      {
        headers: this.createAuthorizationHeader()
      })
  }

  getAllProducts(): Observable<any> {
    return this.http.get<[]>(BASIC_URL + "api/admin/product",
    { 
      headers: this.createAuthorizationHeader() 
    })
  }
  

  deleteProduct(id: number): Observable<any> {
    return this.http.delete(BASIC_URL + "api/admin/product/" + id, {
      headers: this.createAuthorizationHeader()
    });
  }

  editProduct(id: number, product: any): Observable<any> {
    return this.http.put(BASIC_URL + "api/admin/product/" + id, product, {
      headers: this.createAuthorizationHeader()
    });
  }

  getProduct(id: number): Observable<any> {
    return this.http.get(BASIC_URL + "api/admin/product/" + id, {
      headers: this.createAuthorizationHeader()
    });
  }
  


createAuthorizationHeader(): HttpHeaders {
    let authHeader: HttpHeaders = new HttpHeaders()
      .set("Authorization", "Bearer " + StorageService.getToken()); // Asumiendo que getToken() es un método para obtener el token

    return authHeader;
  }

}

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { StorageService } from '../../../auth-services/storage.service';
import { Observable } from 'rxjs';


const BASIC_URL = ["http://localhost:8080/"]


@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient) { }


  getAllProducts(): Observable<any> {
    return this.http.get<[]>(BASIC_URL + "api/customer/product",
    { 
      headers: this.createAuthorizationHeader() 
    })
  }


  // Este metodo nos permite obtener las sales por usuario
  getAllSales(): Observable<any> {
    const userId = StorageService.getUserId();
    const url = `http://localhost:8080/api/customer/${userId}/sale`;  // Ajusta la URL para incluir userId en el path
    return this.http.get<[]>(url, { headers: this.createAuthorizationHeader() });
  }

  postSale(saleDto: any): Observable<any> {
    return this.http.post<[]>(BASIC_URL + "api/customer/sale", saleDto,
    {
      headers: this.createAuthorizationHeader()
    })
  }

  createAuthorizationHeader(): HttpHeaders{
    let authHeader: HttpHeaders = new HttpHeaders();
    return authHeader.set("Authorization","Bearer " + StorageService.getToken());
  }

  


}

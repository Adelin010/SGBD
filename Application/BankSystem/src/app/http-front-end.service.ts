import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpFrontEndService {

  private URLAPI = 'localhost:8080'

  constructor(private http: HttpClient) { }

  // Get the main message
  getMainMessage(): Observable<Object> {
    return this.http.get(`${this.URLAPI}`)
  }

  // Get all the users
  getAllUsers(): Observable<Object> {
    return this.http.get(`${this.URLAPI}/users`)
  }

  getUser(id: string): Observable<Object>{
    return this.http.get(`${this.URLAPI}/${id}`)
  }

  postUser( payload: Object): Observable<any> {
    return this.http.post(`${this.URLAPI}/register`,payload)
  }
  
  putUser( id: string, payload: Object): Observable<any> {
    return this.http.put(`${this.URLAPI}/users/${id}`,payload)
  }

  deleteUser( id: string, payload: any): Observable<any> {
    return this.http.delete(`${this.URLAPI}/users/${id}`,payload)
  }

}

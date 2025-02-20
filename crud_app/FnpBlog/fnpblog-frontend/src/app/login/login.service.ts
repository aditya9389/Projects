import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root' // ✅ Ensures service is available globally
})
export class LoginService {
  private loginUrl = 'http://localhost:8080/api/users/login'; 

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<any> {
    console.log("Sending login request:", { username, password });
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.post(
      this.loginUrl, 
      { username, password }, 
      { headers, withCredentials: true } 
    );
  }
}

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root' // ✅ Ensures service is available globally
})
export class RegisterService {
  private registerUrl = 'http://localhost:8080/api/users/register'; // ✅ Ensure correct API URL

  constructor(private http: HttpClient) {}

  Register(username: string,empId: string, password: string): Observable<any> {
    console.log("Sending register request:", { username, password, empId });
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.post(
      this.registerUrl, 
      { username, empId,password }, 
      { headers, withCredentials: true } // ✅ Ensure credentials are sent
    );
  }
}

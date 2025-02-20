import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root' // ✅ Ensures service is available globally
})
export class GoogleLoginService {
  private loginUrl = 'http://localhost:8080/oauth2/authorization/google'; 

  constructor(private http: HttpClient,private authService:AuthService) {}

  googleLogin(): void {
    window.location.href=this.loginUrl;
  }
}

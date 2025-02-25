import { Component ,inject} from '@angular/core';
import { CommonModule } from '@angular/common'; 
import { LoginService } from './login.service';
import { FormsModule } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { GoogleLoginService } from './googleLogin.service';
@Component({
  selector: 'app-login',
  standalone: true,
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [LoginService],
  imports: [CommonModule, FormsModule, RouterModule] 
})
export class LoginComponent {
  username = '';
  password = '';
  message = '';

  constructor(
     private authService: AuthService 
    ,private loginService: LoginService
    ,private router: Router
    ,private googleLoginService:GoogleLoginService
    ) {}

  onLogin() {
    this.loginService.login(this.username, this.password).subscribe({
      next: (response) => {
        const token = response.token;
        this.message = 'Login Successful!Token recieved: ' + response.token; 
        this.authService.saveToken(token);
        console.log('Token saved:', this.authService.getToken());
        console.log('Login successful', response);
        this.goToDashboard();

      },
      error: (error) => {
        console.error('Login failed', error);
        this.message = 'Login Failed. Try Again! ' + error;
      }
    });
  }

  goToReg() {
    this.router.navigate(['/register']); 
  }
  goToDashboard() {
    this.router.navigate(['/dashboard']); 

  }
  goToGoogleReg() {
    this.googleLoginService.googleLogin(); 
  }
}
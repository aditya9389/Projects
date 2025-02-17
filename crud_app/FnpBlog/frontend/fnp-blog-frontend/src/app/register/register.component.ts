import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { RegisterService } from './register.service';
import { Router } from '@angular/router'; 

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [RouterModule,CommonModule,FormsModule],
  providers: [RegisterService],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  username = '';
  empId = '';
  password = '';
  message = '';

  constructor(private registerService: RegisterService, private router: Router) {}

  onRegister() {
    this.registerService.Register(this.username,this.empId, this.password).subscribe({
      next: (response) => {
        console.log('register successfull', response);
        this.message = 'Register Successful!';  
      },
      error: (error) => {
        console.error('Registration failed', error);
        this.message = 'Registration Failed. give proper details ' + error;
      }
    });
  }

  goTologin() {
    this.router.navigate(['/login']); 
  }
}

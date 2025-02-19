import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  imports: [CommonModule,RouterModule]
})
export class DashboardComponent {
  notes: any[] = [];
  notesFetched = false; // To track if notes were fetched

  constructor(private http: HttpClient, private router: Router, private authservice: AuthService) {}

  getNotes() {
    const token = this.authservice.getToken();
    if (!token) {
      console.error('No token found! Redirecting to login.');
      this.router.navigate(['/login']);
      return;
    }
    console.log('Fetching notes...');
    console.log('Token:', token);

    this.http.get('http://localhost:8080/api/notes/getNotes', {
      headers: { Authorization: `Bearer ${token}` }
    }).subscribe(
      (response: any) => {
        this.notes = response;
        this.notesFetched = true; // Mark notes as fetched
      },
      error => {
        console.error('Error fetching notes:', error);
        this.notesFetched = false; // Prevent infinite loading
      }
    );
  }

  logout() {
    this.authservice.removeToken();
    this.router.navigate(['/login']);
  }
    createNote(){
      this.router.navigate(['/createNote']);
    }
}

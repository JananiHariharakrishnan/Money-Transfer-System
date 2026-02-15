import { Component, OnInit, signal, inject } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './service/auth';

@Component({
  selector: 'app-root',
  templateUrl: './app.html',
  standalone: false,
  styleUrl: './app.css'
})
export class App implements OnInit {
  protected readonly title = signal('mts-frontend');
  private authService = inject(AuthService);
  private router = inject(Router);

  ngOnInit() {
    // Auto-login on app initialization if token exists in sessionStorage
    if (this.authService.isUserLoggedin()) {
      const userId = this.authService.getLoggedinUser();
      const currentPath = this.router.url;
      
      // If logged in but viewing root or login page, redirect to dashboard
      if (currentPath === '/' || currentPath === '' || currentPath === '/login') {
        this.router.navigate(['/dashboard', userId]);
      }
    }
  }
}

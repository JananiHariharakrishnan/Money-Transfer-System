import { Component, ChangeDetectorRef, OnInit, inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Accountholderservice } from '../../service/accountholderservice';
import { AccountHolderInterface } from '../account-holder-interface';

@Component({
  selector: 'app-profile',
  standalone: false,
  templateUrl: './profile.html',
  styleUrl: './profile.css',
})
export class Profile implements OnInit {
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private service = inject(Accountholderservice);
  private cd = inject(ChangeDetectorRef);

  user?: AccountHolderInterface;
  accId: number = 0;
  loading: boolean = true;

  ngOnInit(): void {
    this.accId = Number(this.route.snapshot.paramMap.get('id')) || 0;
    if (this.accId > 0) {
      this.loadUser();
    } else {
      this.router.navigate(['/']);
    }
  }

  loadUser() {
    this.service.getUserById(this.accId).subscribe({
      next: (data) => {
        this.user = data;
        this.loading = false;
        this.cd.detectChanges();
      },
      error: (err) => {
        console.error('Error loading user', err);
        this.loading = false;
        this.router.navigate(['/']);
      }
    });
  }

  goHome() {
    this.router.navigate(['/dashboard', this.accId]);
  }

  goToTransfer() {
    this.router.navigate(['/transfer', this.accId]);
  }

  goToHistory() {
    this.router.navigate(['/history', this.accId]);
  }
}


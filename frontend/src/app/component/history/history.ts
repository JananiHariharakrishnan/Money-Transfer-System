import { Component, ChangeDetectorRef, OnInit, inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Accountholderservice } from '../../service/accountholderservice';
import { TransactionLogInterface } from '../transaction-log-interface';

@Component({
  selector: 'app-history',
  templateUrl: './history.html',
  styleUrls: ['./history.css'],
  standalone:false
})
export class History implements OnInit {
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private service = inject(Accountholderservice);
  private cd = inject(ChangeDetectorRef);

  accId = 0;
  transactions: TransactionLogInterface[] = [];
  loading = true;

  ngOnInit(): void {
    this.accId = Number(this.route.snapshot.paramMap.get('id')) || 0;
    if (this.accId <= 0) {
      this.router.navigate(['/']);
      return;
    }

    this.service.getTransactionsById(this.accId).subscribe({
      next: (data) => {
        this.transactions = (data || []).slice().sort((a, b) =>
          (b.createdOn || '').localeCompare(a.createdOn || '')
        );
        this.loading = false;
        this.cd.detectChanges();
      },
      error: (err) => {
        console.error('Transaction API error:', err);
        this.loading = false;
        this.cd.detectChanges();
      },
    });
  }

  // CREDIT if money came IN to this account
  isCredit(t: TransactionLogInterface): boolean {
    return t.toAccountId === this.accId;
  }

  // Show a friendly status
  statusText(t: TransactionLogInterface): string {
    return (t.status || '').toUpperCase() === 'SUCCESS' ? 'Success' : 'Failed';
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

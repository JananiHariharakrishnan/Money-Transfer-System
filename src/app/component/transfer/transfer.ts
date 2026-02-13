import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Accountholderservice } from '../../service/accountholderservice';
import { TransferService } from '../../transfer-service'; // Path to your new service
import { AccountHolderInterface } from '../account-holder-interface';
import { TransferRequestDto } from '../../models/transferModel';
import { v4 as uuidv4 } from 'uuid'; // Standard for idempotencyKey

@Component({
  selector: 'app-transfer',
  standalone: false,
  templateUrl: './transfer.html',
  styleUrl: './transfer.css',
})
export class Transfer implements OnInit {
  route = inject(ActivatedRoute);
  router = inject(Router);
  service = inject(Accountholderservice);
  transferService = inject(TransferService); // Inject the transfer API service

  user?: AccountHolderInterface;
  accId: number = 0;

  // Form Fields (Bind these in your HTML using [(ngModel)])
  recipientId: number = 0;
  transferAmount: number = 0;

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
      next: (data) => { this.user = data; },
      error: (err) => {
        console.error('Error loading user', err);
        this.router.navigate(['/']);
      }
    });
  }
 submitTransfer() {
  // 1. Generate the custom timestamp (MMddHHmm)
  const now = new Date();
  const month = String(now.getMonth() + 1).padStart(2, '0');
  const day = String(now.getDate()).padStart(2, '0');
  const hours = String(now.getHours()).padStart(2, '0');
  const minutes = String(now.getMinutes()).padStart(2, '0');
  const timestamp = `${month}${day}${hours}${minutes}`;
 
  // 2. Build the request DTO
  const request: TransferRequestDto = {
    fromAccountId: this.accId,
    toAccountId: this.recipientId,
    amount: this.transferAmount,
    // Format: MMddHHmm_sender_receiver
    idempotencyKey: `${timestamp}_${this.accId}_${this.recipientId}`
  };
 
  // 3. Send the request
  this.transferService.executeTransfer(request).subscribe({
    next: (response) => {
      console.log('Backend response:', response.finalMessage);
      alert(`Transfer Successful! Transaction ID: ${response.id}`);
      this.router.navigate(['/dashboard']);
    },
    error: (err) => {
      alert('Transfer failed: ' + (err.error?.finalMessage || 'Unknown Error'));
    }
  });
}
}
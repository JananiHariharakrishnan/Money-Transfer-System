import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TransferRequestDto, TransferResponseDto } from './models/transferModel';

@Injectable({ providedIn: 'root' })
export class TransferService {
  private apiUrl = 'http://localhost:8080/api/v1/transfers'; // Replace with your actual endpoint

  constructor(private http: HttpClient) {}

  // This method takes your DTO and sends it to the backend
  executeTransfer(request: TransferRequestDto): Observable<TransferResponseDto> {
    return this.http.post<TransferResponseDto>(this.apiUrl, request);
  }
}
import { TransactionStatus } from "./transaction-status.enum"; // Ensure you have this enum

export interface TransferRequestDto {
  fromAccountId: number;
  toAccountId: number;
  amount: number;
  idempotencyKey: string;
}

export interface TransferResponseDto {
  id: string;
  finalMessage: string;
  status: TransactionStatus;
  toAccountId: number;
  fromAccountId: number;
  amount: number;
}
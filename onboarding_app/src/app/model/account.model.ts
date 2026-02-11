export interface Account {
  id?: number;
  customerId: number;
  accountNumber: number;
  status: 'ACTIVE' | 'INACTIVE';
}

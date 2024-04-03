export interface Order {
  orderId?: number;
  note: string;
  paid: string;
  status: string;
  createdAt?: Date;
  customer?: {
    customerId: number;
    name: string;
    contact: string;
    address: string;
    paymentMethod: string;
    collectionMethod: string
  };
  account?: {
    accountId: number;
  },
  collectionDate: Date;
}
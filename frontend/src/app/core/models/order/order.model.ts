export interface Order {
  orderId?: number;
  note: string;
  paid: string;
  status: string;
  createdAt: Date;
  stock_on_hand: number;
  customer: {
    customerId: number;
    name: string;
    contact: string;
    address: string;
    paymentMethod: string;
    collectionDate: Date
  };
  account: {
    accountId: number;
  },
  collectionDate: Date;
}
export interface Item {
  itemId: number;
  name: string;
  cost: number;
  description: string;
  reference: string;
  stock_on_hand: number;
  account: {
    accountId: number;
    email: string;
  };
}
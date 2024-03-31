export interface Item {
  name: string;
  cost: number;
  description: string;
  reference: string;
  stock_on_hand: number;
  account: {
    accountId: number;
  };
}
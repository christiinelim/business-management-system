export interface ItemOrder {
  purchaseId? : number;
  quantity: number;
  item: {
    itemId: number;
    name?: string;
  };
  order: {
    orderId: number;
  }
}
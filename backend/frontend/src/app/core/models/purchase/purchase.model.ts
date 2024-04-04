export interface Purchase {
  purchaseId?: number;
  quantity: number,
  order: {
    orderId: number;
  },
  item: {
    itemId: number,
    name: string,
    cost: number
  }
}
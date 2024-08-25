// Product model file (e.g., product.model.ts)
import { Status } from './status.type';

export interface Product {
  id?: string;
  name: string;
  price: number;
  status: Status;
  quantity: number;
  createdAt: string | null;
  updatedAt: string | null;
}

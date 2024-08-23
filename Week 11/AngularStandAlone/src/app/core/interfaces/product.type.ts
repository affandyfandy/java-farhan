import { Status } from './status.type';

export interface Product {
  id?: string | undefined;
  name: string;
  price: number | undefined;
  status: string | undefined;
  quantity: number | undefined;
  createdAt: string | null;
  updatedAt: string | null;
}

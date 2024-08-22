import { Status } from '../interfaces/status.type';

export class Product {
  id?: string;
  name?: string;
  price?: number;
  status?: Status.Active;
  quantity?: number;
  createdAt?: string;
  updatedAt?: string;
}

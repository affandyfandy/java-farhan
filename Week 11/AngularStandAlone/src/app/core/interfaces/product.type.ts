import { Status } from './status.type';

export interface Products {
  id: string;
  name: string;
  price: number;
  status: Status;
  quantity: number;
  createdAt: string;
  updatedAt: string;
}

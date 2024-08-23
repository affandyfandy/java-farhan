import { Status } from './status.type';

export interface Product {
  id?: string | undefined;
  name: string;
  price?: number;
  status?: Status;
  quantity?: number;
  createdAt?: Date;
  updatedAt?: Date;
}

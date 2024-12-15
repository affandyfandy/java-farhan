export interface Customers {
  id: string | undefined;
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
}

export interface Customer {
  id: string | undefined;
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
}

export interface UpdateCustomerResponse {
  message: string;
  data: Customer;
}

import { NotfoundComponent } from '../pages/notfound/notfound.component';

export const AppConstants = {
  APPLICATION_NAME: 'Javascript Application Framework',
  BASE_API_URL: 'http://localhost:3000',
  LOG_OFF_ICON: 'sign-out',
};

export interface RouteLink {
  path: string;
  link: string;
}

export const RouterConfig = {
  HOME: { path: '', link: '/' },
  AUTH: { path: 'auth', link: '/auth', title: 'Auth Page' },
  CUSTOMER: { path: 'customers', link: '/customers', title: 'Customer Page' },
  PRODUCTS: { path: 'products', link: '/products', title: 'Product Page' },
  NOT_FOUND: {
    path: 'not-found', // Changed to a relative path for routing
    link: '/not-found',
    title: 'Page Not Found',
    component: NotfoundComponent,
  },
};

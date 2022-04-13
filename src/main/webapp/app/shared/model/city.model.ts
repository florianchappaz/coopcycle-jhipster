import { ICustomer } from 'app/shared/model/customer.model';
import { IRestaurant } from 'app/shared/model/restaurant.model';
import { IZone } from 'app/shared/model/zone.model';

export interface ICity {
  id?: number;
  name?: string;
  zipCode?: string;
  customer?: ICustomer | null;
  restaurant?: IRestaurant | null;
  zone?: IZone | null;
}

export const defaultValue: Readonly<ICity> = {};

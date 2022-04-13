import { IRestaurant } from 'app/shared/model/restaurant.model';

export interface IMeal {
  id?: number;
  name?: string;
  price?: number;
  description?: string | null;
  restaurant?: IRestaurant | null;
}

export const defaultValue: Readonly<IMeal> = {};

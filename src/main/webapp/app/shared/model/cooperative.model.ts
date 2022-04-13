import { IZone } from 'app/shared/model/zone.model';
import { IDeliverMan } from 'app/shared/model/deliver-man.model';
import { IRestaurant } from 'app/shared/model/restaurant.model';
import { ICustomer } from 'app/shared/model/customer.model';

export interface ICooperative {
  id?: number;
  name?: string;
  zone?: IZone | null;
  deliverMen?: IDeliverMan[] | null;
  restaurants?: IRestaurant[] | null;
  customers?: ICustomer[] | null;
}

export const defaultValue: Readonly<ICooperative> = {};

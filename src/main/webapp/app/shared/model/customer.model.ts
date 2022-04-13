import { ICity } from 'app/shared/model/city.model';
import { ICooperative } from 'app/shared/model/cooperative.model';

export interface ICustomer {
  id?: number;
  name?: string;
  age?: number;
  adress?: string;
  city?: ICity | null;
  cooperative?: ICooperative | null;
}

export const defaultValue: Readonly<ICustomer> = {};

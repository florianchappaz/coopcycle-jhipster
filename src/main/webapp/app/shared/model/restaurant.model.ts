import { ICity } from 'app/shared/model/city.model';
import { IMeal } from 'app/shared/model/meal.model';
import { ICooperative } from 'app/shared/model/cooperative.model';
import { Category } from 'app/shared/model/enumerations/category.model';

export interface IRestaurant {
  id?: number;
  name?: string;
  adress?: string;
  category?: Category | null;
  city?: ICity | null;
  meals?: IMeal[] | null;
  cooperative?: ICooperative | null;
}

export const defaultValue: Readonly<IRestaurant> = {};

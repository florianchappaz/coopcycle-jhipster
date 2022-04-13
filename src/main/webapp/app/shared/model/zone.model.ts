import { ICity } from 'app/shared/model/city.model';
import { ICooperative } from 'app/shared/model/cooperative.model';

export interface IZone {
  id?: number;
  cities?: ICity[] | null;
  cooperative?: ICooperative | null;
}

export const defaultValue: Readonly<IZone> = {};

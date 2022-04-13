import { ICooperative } from 'app/shared/model/cooperative.model';

export interface IDeliverMan {
  id?: number;
  name?: string;
  age?: number;
  profilePictureContentType?: string | null;
  profilePicture?: string | null;
  cooperative?: ICooperative | null;
}

export const defaultValue: Readonly<IDeliverMan> = {};

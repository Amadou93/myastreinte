import { IAstreinte } from 'app/shared/model/astreinte.model';
import { INotification } from 'app/shared/model/notification.model';
import { IAbsence } from 'app/shared/model/absence.model';
export interface IEmploye {
  id?: number;
  matricul?: number;
  firstName?: string;
  lastName?: string;
  email?: string;
  phoneNumber?: number;
  astreintes?: IAstreinte[];
  notifications?: INotification[];
  absences?: IAbsence[];
  equipeId?: number;
  domaineId?: number;
}

export class Employe implements IEmploye {
  constructor(
    public id?: number,
    public matricul?: number,
    public firstName?: string,
    public lastName?: string,
    public email?: string,
    public phoneNumber?: number,
    public astreintes?: IAstreinte[],
    public notifications?: INotification[],
    public absences?: IAbsence[],
    public equipeId?: number[],
    public domaineId?: number[]
  ) {}
}

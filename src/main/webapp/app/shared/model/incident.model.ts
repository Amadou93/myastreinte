import { Moment } from 'moment';
import { INotification } from 'app/shared/model/notification.model';

export const enum State {
  UP = 'UP',
  DOWN = 'DOWN'
}

export interface IIncident {
  id?: number;
  date?: Moment;
  type?: string;
  criticite?: string;
  sla?: number;
  description?: string;
  adresseIP?: string;
  composant?: string;
  responsable?: string;
  status?: State;
  equipementName?: string;
  message?: string;
  equipeId?: number;
  notifications?: INotification[];
}

export class Incident implements IIncident {
  constructor(
    public id?: number,
    public date?: Moment,
    public type?: string,
    public criticite?: string,
    public sla?: number,
    public description?: string,
    public adresseIP?: string,
    public composant?: string,
    public responsable?: string,
    public status?: State,
    public equipementName?: string,
    public message?: string,
    public equipeId?: number,
    public notifications?: INotification[]
  ) {}
}

import { Moment } from 'moment';

export interface IIncident {
  id?: number;
  date?: Moment;
  type?: string;
  criticite?: string;
  sla?: number;
  description?: string;
  equipeId?: number;
}

export class Incident implements IIncident {
  constructor(
    public id?: number,
    public date?: Moment,
    public type?: string,
    public criticite?: string,
    public sla?: number,
    public description?: string,
    public equipeId?: number
  ) {}
}

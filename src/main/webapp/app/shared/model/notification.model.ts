import { Moment } from 'moment';

export const enum State {
  Down = 'Down',
  Up = 'Up'
}

export interface INotification {
  id?: number;
  date?: Moment;
  type?: string;
  level?: number;
  dispositifName?: string;
  state?: State;
  groupe?: string;
  astreinteName?: string;
  availiblity?: Moment;
  employeId?: number;
}

export class Notification implements INotification {
  constructor(
    public id?: number,
    public date?: Moment,
    public type?: string,
    public level?: number,
    public dispositifName?: string,
    public state?: State,
    public groupe?: string,
    public astreinteName?: string,
    public availiblity?: Moment,
    public employeId?: number
  ) {}
}

import { Moment } from 'moment';

export interface IAbsence {
  id?: number;
  startDate?: Moment;
  endDate?: Moment;
  employeId?: number;
}

export class Absence implements IAbsence {
  constructor(public id?: number, public startDate?: Moment, public endDate?: Moment, public employeId?: number) {}
}

export interface IAstreinte {
  id?: number;
  matricul?: number;
  year?: number;
  numberWeek?: number;
  employeId?: number;
}

export class Astreinte implements IAstreinte {
  constructor(public id?: number, public matricul?: number, public year?: number, public numberWeek?: number, public employeId?: number) {}
}

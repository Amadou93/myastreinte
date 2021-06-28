import { IDivision } from 'app/shared/model/division.model';

export interface IDepartement {
  id?: number;
  departementName?: string;
  divisions?: IDivision[];
}

export class Departement implements IDepartement {
  constructor(public id?: number, public departementName?: string, public divisions?: IDivision[]) {}
}

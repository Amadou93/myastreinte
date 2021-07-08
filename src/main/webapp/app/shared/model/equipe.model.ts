import { IEmploye } from 'app/shared/model/employe.model';

export interface IEquipe {
  id?: number;
  name?: string;
  employes?: IEmploye[];
}

export class Equipe implements IEquipe {
  constructor(public id?: number, public name?: string, public employes?: IEmploye[]) {}
}

import { IEquipe } from 'app/shared/model/equipe.model';

export interface IDivision {
  id?: number;
  divisionName?: string;
  divisionChef?: string;
  departementId?: number;
  nameEquipes?: IEquipe[];
}

export class Division implements IDivision {
  constructor(
    public id?: number,
    public divisionName?: string,
    public divisionChef?: string,
    public departementId?: number,
    public nameEquipes?: IEquipe[]
  ) {}
}

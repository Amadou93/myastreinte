import { IEmploye } from 'app/shared/model/employe.model';
import { IServeur } from 'app/shared/model/serveur.model';
import { IEquipementReseau } from 'app/shared/model/equipement-reseau.model';
import { ISwitche } from 'app/shared/model/switche.model';
import { IIncident } from 'app/shared/model/incident.model';
import { IApplication } from 'app/shared/model/application.model';
import { IBasededonnee } from 'app/shared/model/basededonnee.model';

export interface IEquipe {
  id?: number;
  name?: string;
  employes?: IEmploye[];
  serveurs?: IServeur[];
  equipements?: IEquipementReseau[];
  switches?: ISwitche[];
  dates?: IIncident[];
  applications?: IApplication[];
  basededonnees?: IBasededonnee[];
  divisionId?: number;
}

export class Equipe implements IEquipe {
  constructor(
    public id?: number,
    public name?: string,
    public employes?: IEmploye[],
    public serveurs?: IServeur[],
    public equipements?: IEquipementReseau[],
    public switches?: ISwitche[],
    public dates?: IIncident[],
    public applications?: IApplication[],
    public basededonnees?: IBasededonnee[],
    public divisionId?: number
  ) {}
}

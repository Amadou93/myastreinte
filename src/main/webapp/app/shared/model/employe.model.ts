export interface IEmploye {
  id?: number;
  matricul?: number;
  firstName?: string;
  lastName?: string;
  email?: string;
  phoneNumber?: number;
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
    public equipeId?: number,
    public domaineId?: number
  ) {}
}

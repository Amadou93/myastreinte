export interface IServeur {
  id?: number;
  nameServeur?: string;
  cpu?: string;
  memory?: number;
  disque?: number;
  avalibity?: string;
  equipeId?: number;
}

export class Serveur implements IServeur {
  constructor(
    public id?: number,
    public nameServeur?: string,
    public cpu?: string,
    public memory?: number,
    public disque?: number,
    public avalibity?: string,
    public equipeId?: number
  ) {}
}

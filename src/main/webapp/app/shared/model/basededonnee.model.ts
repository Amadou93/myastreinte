export interface IBasededonnee {
  id?: number;
  name?: string;
  baseType?: string;
  memory?: string;
  equipeId?: number;
}

export class Basededonnee implements IBasededonnee {
  constructor(public id?: number, public name?: string, public baseType?: string, public memory?: string, public equipeId?: number) {}
}

export interface IEquipementReseau {
  id?: number;
  equipementName?: string;
  type?: string;
  ipAddress?: number;
  version?: number;
  equipeId?: number;
}

export class EquipementReseau implements IEquipementReseau {
  constructor(
    public id?: number,
    public equipementName?: string,
    public type?: string,
    public ipAddress?: number,
    public version?: number,
    public equipeId?: number
  ) {}
}

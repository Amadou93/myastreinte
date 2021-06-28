export interface ISwitche {
  id?: number;
  ipAddress?: number;
  equipeId?: number;
}

export class Switche implements ISwitche {
  constructor(public id?: number, public ipAddress?: number, public equipeId?: number) {}
}

export interface IApplication {
  id?: number;
  name?: string;
  type?: string;
  equipeId?: number;
}

export class Application implements IApplication {
  constructor(public id?: number, public name?: string, public type?: string, public equipeId?: number) {}
}

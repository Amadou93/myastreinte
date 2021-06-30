export interface IDomaine {
  id?: number;
  nom?: string;
  service?: string;
  responsableService?: string;
  numTelResponsableService?: string;
  emailResponsableService?: string;
  division?: string;
  responsableDivision?: string;
  numResponsableDivision?: string;
  emailResponsableDivision?: string;
  departement?: string;
  responsableDepartement?: string;
  numeroTelResponsableDepartement?: string;
  emailResponsableDepartement?: string;
  direction?: string;
  responsableDirection?: string;
  numeroTelDirecteur?: string;
  emailDirecteur?: string;
}

export class Domaine implements IDomaine {
  constructor(
    public id?: number,
    public nom?: string,
    public service?: string,
    public responsableService?: string,
    public numTelResponsableService?: string,
    public emailResponsableService?: string,
    public division?: string,
    public responsableDivision?: string,
    public numResponsableDivision?: string,
    public emailResponsableDivision?: string,
    public departement?: string,
    public responsableDepartement?: string,
    public numeroTelResponsableDepartement?: string,
    public emailResponsableDepartement?: string,
    public direction?: string,
    public responsableDirection?: string,
    public numeroTelDirecteur?: string,
    public emailDirecteur?: string
  ) {}
}

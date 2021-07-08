export interface IDomaine {
  id?: number;
  nom?: string;
  nomService?: string;
  respService?: string;
  telRespService?: string;
  emailRespService?: string;
  nomDivision?: string;
  respDivision?: string;
  telRespDivision?: string;
  emailRespDivision?: string;
  nomDepart?: string;
  respDepart?: string;
  telRespDepart?: string;
  emailRespDepart?: string;
  nomDirection?: string;
  respDirection?: string;
  telDirecteur?: string;
  emailDirecteur?: string;
}

export class Domaine implements IDomaine {
  constructor(
    public id?: number,
    public nom?: string,
    public nomService?: string,
    public respService?: string,
    public telRespService?: string,
    public emailRespService?: string,
    public nomDivision?: string,
    public respDivision?: string,
    public telRespDivision?: string,
    public emailRespDivision?: string,
    public nomDepart?: string,
    public respDepart?: string,
    public telRespDepart?: string,
    public emailRespDepart?: string,
    public nomDirection?: string,
    public respDirection?: string,
    public telDirecteur?: string,
    public emailDirecteur?: string
  ) {}
}

import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IEquipementReseau } from 'app/shared/model/equipement-reseau.model';

type EntityResponseType = HttpResponse<IEquipementReseau>;
type EntityArrayResponseType = HttpResponse<IEquipementReseau[]>;

@Injectable({ providedIn: 'root' })
export class EquipementReseauService {
  public resourceUrl = SERVER_API_URL + 'api/equipement-reseaus';

  constructor(protected http: HttpClient) {}

  create(equipementReseau: IEquipementReseau): Observable<EntityResponseType> {
    return this.http.post<IEquipementReseau>(this.resourceUrl, equipementReseau, { observe: 'response' });
  }

  update(equipementReseau: IEquipementReseau): Observable<EntityResponseType> {
    return this.http.put<IEquipementReseau>(this.resourceUrl, equipementReseau, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEquipementReseau>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEquipementReseau[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}

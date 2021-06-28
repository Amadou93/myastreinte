import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IBasededonnee } from 'app/shared/model/basededonnee.model';

type EntityResponseType = HttpResponse<IBasededonnee>;
type EntityArrayResponseType = HttpResponse<IBasededonnee[]>;

@Injectable({ providedIn: 'root' })
export class BasededonneeService {
  public resourceUrl = SERVER_API_URL + 'api/basededonnees';

  constructor(protected http: HttpClient) {}

  create(basededonnee: IBasededonnee): Observable<EntityResponseType> {
    return this.http.post<IBasededonnee>(this.resourceUrl, basededonnee, { observe: 'response' });
  }

  update(basededonnee: IBasededonnee): Observable<EntityResponseType> {
    return this.http.put<IBasededonnee>(this.resourceUrl, basededonnee, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBasededonnee>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBasededonnee[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}

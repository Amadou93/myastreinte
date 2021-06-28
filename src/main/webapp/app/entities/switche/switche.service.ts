import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISwitche } from 'app/shared/model/switche.model';

type EntityResponseType = HttpResponse<ISwitche>;
type EntityArrayResponseType = HttpResponse<ISwitche[]>;

@Injectable({ providedIn: 'root' })
export class SwitcheService {
  public resourceUrl = SERVER_API_URL + 'api/switches';

  constructor(protected http: HttpClient) {}

  create(switche: ISwitche): Observable<EntityResponseType> {
    return this.http.post<ISwitche>(this.resourceUrl, switche, { observe: 'response' });
  }

  update(switche: ISwitche): Observable<EntityResponseType> {
    return this.http.put<ISwitche>(this.resourceUrl, switche, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISwitche>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISwitche[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}

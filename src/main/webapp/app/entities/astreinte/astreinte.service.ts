import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAstreinte } from 'app/shared/model/astreinte.model';
import { Message } from 'app/shared/model/message';

type EntityResponseType = HttpResponse<IAstreinte>;
type EntityArrayResponseType = HttpResponse<IAstreinte[]>;

@Injectable({ providedIn: 'root' })
export class AstreinteService {
  public resourceUrl = SERVER_API_URL + 'api/astreintes';
  /* public apisms = "https://192.168.41.45:8033/services/SendNotifications.aspx?_wadl" */

  constructor(protected http: HttpClient) {}

  requestOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/xml'
    })
  };

  create(astreinte: IAstreinte): Observable<EntityResponseType> {
    return this.http.post<IAstreinte>(this.resourceUrl, astreinte, { observe: 'response' });
  }

  update(astreinte: IAstreinte): Observable<EntityResponseType> {
    return this.http.put<IAstreinte>(this.resourceUrl, astreinte, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAstreinte>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  findByChaine(chaine: number): Observable<EntityArrayResponseType> {
    return this.http.get<IAstreinte[]>(this.resourceUrl + '/search/' + chaine);
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAstreinte[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
  upload(formData: FormData): Observable<EntityResponseType> {
    return this.http.post<any>(`${this.resourceUrl}/upload`, formData, { observe: 'response' });
  }

  /*  sendMessage(message: Message): Observable<any>{
    console.log(message)
    return this.http.post<any>(`${this.apisms}`, message, this.requestOptions )
  }
 */
}

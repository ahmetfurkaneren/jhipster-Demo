import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISirketBilgileri } from 'app/shared/model/sirket-bilgileri.model';

type EntityResponseType = HttpResponse<ISirketBilgileri>;
type EntityArrayResponseType = HttpResponse<ISirketBilgileri[]>;

@Injectable({ providedIn: 'root' })
export class SirketBilgileriService {
  public resourceUrl = SERVER_API_URL + 'api/sirket-bilgileris';

  constructor(protected http: HttpClient) {}

  create(sirketBilgileri: ISirketBilgileri): Observable<EntityResponseType> {
    return this.http.post<ISirketBilgileri>(this.resourceUrl, sirketBilgileri, { observe: 'response' });
  }

  update(sirketBilgileri: ISirketBilgileri): Observable<EntityResponseType> {
    return this.http.put<ISirketBilgileri>(this.resourceUrl, sirketBilgileri, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISirketBilgileri>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISirketBilgileri[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}

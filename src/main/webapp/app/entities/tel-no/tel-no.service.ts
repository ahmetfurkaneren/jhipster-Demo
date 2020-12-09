import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITelNo } from 'app/shared/model/tel-no.model';

type EntityResponseType = HttpResponse<ITelNo>;
type EntityArrayResponseType = HttpResponse<ITelNo[]>;

@Injectable({ providedIn: 'root' })
export class TelNoService {
  public resourceUrl = SERVER_API_URL + 'api/tel-nos';

  constructor(protected http: HttpClient) {}

  create(telNo: ITelNo): Observable<EntityResponseType> {
    return this.http.post<ITelNo>(this.resourceUrl, telNo, { observe: 'response' });
  }

  update(telNo: ITelNo): Observable<EntityResponseType> {
    return this.http.put<ITelNo>(this.resourceUrl, telNo, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITelNo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITelNo[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}

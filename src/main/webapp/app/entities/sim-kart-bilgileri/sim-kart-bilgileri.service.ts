import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISimKartBilgileri } from 'app/shared/model/sim-kart-bilgileri.model';

type EntityResponseType = HttpResponse<ISimKartBilgileri>;
type EntityArrayResponseType = HttpResponse<ISimKartBilgileri[]>;

@Injectable({ providedIn: 'root' })
export class SimKartBilgileriService {
  public resourceUrl = SERVER_API_URL + 'api/sim-kart-bilgileris';

  constructor(protected http: HttpClient) {}

  create(simKartBilgileri: ISimKartBilgileri): Observable<EntityResponseType> {
    return this.http.post<ISimKartBilgileri>(this.resourceUrl, simKartBilgileri, { observe: 'response' });
  }

  update(simKartBilgileri: ISimKartBilgileri): Observable<EntityResponseType> {
    return this.http.put<ISimKartBilgileri>(this.resourceUrl, simKartBilgileri, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISimKartBilgileri>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISimKartBilgileri[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}

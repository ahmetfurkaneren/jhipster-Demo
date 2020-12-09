import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPaketler } from 'app/shared/model/paketler.model';

type EntityResponseType = HttpResponse<IPaketler>;
type EntityArrayResponseType = HttpResponse<IPaketler[]>;

@Injectable({ providedIn: 'root' })
export class PaketlerService {
  public resourceUrl = SERVER_API_URL + 'api/paketlers';

  constructor(protected http: HttpClient) {}

  create(paketler: IPaketler): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(paketler);
    return this.http
      .post<IPaketler>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(paketler: IPaketler): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(paketler);
    return this.http
      .put<IPaketler>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPaketler>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPaketler[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(paketler: IPaketler): IPaketler {
    const copy: IPaketler = Object.assign({}, paketler, {
      baslangicTarihi: paketler.baslangicTarihi && paketler.baslangicTarihi.isValid() ? paketler.baslangicTarihi.toJSON() : undefined,
      bitisTarihi: paketler.bitisTarihi && paketler.bitisTarihi.isValid() ? paketler.bitisTarihi.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.baslangicTarihi = res.body.baslangicTarihi ? moment(res.body.baslangicTarihi) : undefined;
      res.body.bitisTarihi = res.body.bitisTarihi ? moment(res.body.bitisTarihi) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((paketler: IPaketler) => {
        paketler.baslangicTarihi = paketler.baslangicTarihi ? moment(paketler.baslangicTarihi) : undefined;
        paketler.bitisTarihi = paketler.bitisTarihi ? moment(paketler.bitisTarihi) : undefined;
      });
    }
    return res;
  }
}

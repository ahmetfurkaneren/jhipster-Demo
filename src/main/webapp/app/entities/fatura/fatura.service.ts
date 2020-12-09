import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFatura } from 'app/shared/model/fatura.model';

type EntityResponseType = HttpResponse<IFatura>;
type EntityArrayResponseType = HttpResponse<IFatura[]>;

@Injectable({ providedIn: 'root' })
export class FaturaService {
  public resourceUrl = SERVER_API_URL + 'api/faturas';

  constructor(protected http: HttpClient) {}

  create(fatura: IFatura): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fatura);
    return this.http
      .post<IFatura>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(fatura: IFatura): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fatura);
    return this.http
      .put<IFatura>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFatura>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFatura[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(fatura: IFatura): IFatura {
    const copy: IFatura = Object.assign({}, fatura, {
      ilkOdemeTarihi: fatura.ilkOdemeTarihi && fatura.ilkOdemeTarihi.isValid() ? fatura.ilkOdemeTarihi.toJSON() : undefined,
      sonOdemeTarihi: fatura.sonOdemeTarihi && fatura.sonOdemeTarihi.isValid() ? fatura.sonOdemeTarihi.toJSON() : undefined,
      odenenTarih: fatura.odenenTarih && fatura.odenenTarih.isValid() ? fatura.odenenTarih.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.ilkOdemeTarihi = res.body.ilkOdemeTarihi ? moment(res.body.ilkOdemeTarihi) : undefined;
      res.body.sonOdemeTarihi = res.body.sonOdemeTarihi ? moment(res.body.sonOdemeTarihi) : undefined;
      res.body.odenenTarih = res.body.odenenTarih ? moment(res.body.odenenTarih) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((fatura: IFatura) => {
        fatura.ilkOdemeTarihi = fatura.ilkOdemeTarihi ? moment(fatura.ilkOdemeTarihi) : undefined;
        fatura.sonOdemeTarihi = fatura.sonOdemeTarihi ? moment(fatura.sonOdemeTarihi) : undefined;
        fatura.odenenTarih = fatura.odenenTarih ? moment(fatura.odenenTarih) : undefined;
      });
    }
    return res;
  }
}

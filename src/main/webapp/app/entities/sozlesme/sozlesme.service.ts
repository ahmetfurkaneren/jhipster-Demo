import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISozlesme } from 'app/shared/model/sozlesme.model';

type EntityResponseType = HttpResponse<ISozlesme>;
type EntityArrayResponseType = HttpResponse<ISozlesme[]>;

@Injectable({ providedIn: 'root' })
export class SozlesmeService {
  public resourceUrl = SERVER_API_URL + 'api/sozlesmes';

  constructor(protected http: HttpClient) {}

  create(sozlesme: ISozlesme): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sozlesme);
    return this.http
      .post<ISozlesme>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(sozlesme: ISozlesme): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sozlesme);
    return this.http
      .put<ISozlesme>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ISozlesme>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISozlesme[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(sozlesme: ISozlesme): ISozlesme {
    const copy: ISozlesme = Object.assign({}, sozlesme, {
      tarih: sozlesme.tarih && sozlesme.tarih.isValid() ? sozlesme.tarih.toJSON() : undefined,
      bitisTarihi: sozlesme.bitisTarihi && sozlesme.bitisTarihi.isValid() ? sozlesme.bitisTarihi.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.tarih = res.body.tarih ? moment(res.body.tarih) : undefined;
      res.body.bitisTarihi = res.body.bitisTarihi ? moment(res.body.bitisTarihi) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((sozlesme: ISozlesme) => {
        sozlesme.tarih = sozlesme.tarih ? moment(sozlesme.tarih) : undefined;
        sozlesme.bitisTarihi = sozlesme.bitisTarihi ? moment(sozlesme.bitisTarihi) : undefined;
      });
    }
    return res;
  }
}

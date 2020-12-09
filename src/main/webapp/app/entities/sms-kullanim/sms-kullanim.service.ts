import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISmsKullanim } from 'app/shared/model/sms-kullanim.model';

type EntityResponseType = HttpResponse<ISmsKullanim>;
type EntityArrayResponseType = HttpResponse<ISmsKullanim[]>;

@Injectable({ providedIn: 'root' })
export class SmsKullanimService {
  public resourceUrl = SERVER_API_URL + 'api/sms-kullanims';

  constructor(protected http: HttpClient) {}

  create(smsKullanim: ISmsKullanim): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(smsKullanim);
    return this.http
      .post<ISmsKullanim>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(smsKullanim: ISmsKullanim): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(smsKullanim);
    return this.http
      .put<ISmsKullanim>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ISmsKullanim>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISmsKullanim[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(smsKullanim: ISmsKullanim): ISmsKullanim {
    const copy: ISmsKullanim = Object.assign({}, smsKullanim, {
      tarih: smsKullanim.tarih && smsKullanim.tarih.isValid() ? smsKullanim.tarih.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.tarih = res.body.tarih ? moment(res.body.tarih) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((smsKullanim: ISmsKullanim) => {
        smsKullanim.tarih = smsKullanim.tarih ? moment(smsKullanim.tarih) : undefined;
      });
    }
    return res;
  }
}

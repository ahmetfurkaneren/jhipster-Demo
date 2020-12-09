import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDakikaKullanim } from 'app/shared/model/dakika-kullanim.model';

type EntityResponseType = HttpResponse<IDakikaKullanim>;
type EntityArrayResponseType = HttpResponse<IDakikaKullanim[]>;

@Injectable({ providedIn: 'root' })
export class DakikaKullanimService {
  public resourceUrl = SERVER_API_URL + 'api/dakika-kullanims';

  constructor(protected http: HttpClient) {}

  create(dakikaKullanim: IDakikaKullanim): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(dakikaKullanim);
    return this.http
      .post<IDakikaKullanim>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(dakikaKullanim: IDakikaKullanim): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(dakikaKullanim);
    return this.http
      .put<IDakikaKullanim>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IDakikaKullanim>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IDakikaKullanim[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(dakikaKullanim: IDakikaKullanim): IDakikaKullanim {
    const copy: IDakikaKullanim = Object.assign({}, dakikaKullanim, {
      tarih: dakikaKullanim.tarih && dakikaKullanim.tarih.isValid() ? dakikaKullanim.tarih.toJSON() : undefined,
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
      res.body.forEach((dakikaKullanim: IDakikaKullanim) => {
        dakikaKullanim.tarih = dakikaKullanim.tarih ? moment(dakikaKullanim.tarih) : undefined;
      });
    }
    return res;
  }
}

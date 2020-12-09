import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IInternetKullanim } from 'app/shared/model/internet-kullanim.model';

type EntityResponseType = HttpResponse<IInternetKullanim>;
type EntityArrayResponseType = HttpResponse<IInternetKullanim[]>;

@Injectable({ providedIn: 'root' })
export class InternetKullanimService {
  public resourceUrl = SERVER_API_URL + 'api/internet-kullanims';

  constructor(protected http: HttpClient) {}

  create(internetKullanim: IInternetKullanim): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(internetKullanim);
    return this.http
      .post<IInternetKullanim>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(internetKullanim: IInternetKullanim): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(internetKullanim);
    return this.http
      .put<IInternetKullanim>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IInternetKullanim>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IInternetKullanim[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(internetKullanim: IInternetKullanim): IInternetKullanim {
    const copy: IInternetKullanim = Object.assign({}, internetKullanim, {
      tarih: internetKullanim.tarih && internetKullanim.tarih.isValid() ? internetKullanim.tarih.toJSON() : undefined,
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
      res.body.forEach((internetKullanim: IInternetKullanim) => {
        internetKullanim.tarih = internetKullanim.tarih ? moment(internetKullanim.tarih) : undefined;
      });
    }
    return res;
  }
}

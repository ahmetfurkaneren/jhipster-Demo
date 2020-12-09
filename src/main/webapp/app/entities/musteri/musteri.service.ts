import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMusteri } from 'app/shared/model/musteri.model';

type EntityResponseType = HttpResponse<IMusteri>;
type EntityArrayResponseType = HttpResponse<IMusteri[]>;

@Injectable({ providedIn: 'root' })
export class MusteriService {
  public resourceUrl = SERVER_API_URL + 'api/musteris';

  constructor(protected http: HttpClient) {}

  create(musteri: IMusteri): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(musteri);
    return this.http
      .post<IMusteri>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(musteri: IMusteri): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(musteri);
    return this.http
      .put<IMusteri>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IMusteri>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IMusteri[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(musteri: IMusteri): IMusteri {
    const copy: IMusteri = Object.assign({}, musteri, {
      dogumTarihi: musteri.dogumTarihi && musteri.dogumTarihi.isValid() ? musteri.dogumTarihi.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dogumTarihi = res.body.dogumTarihi ? moment(res.body.dogumTarihi) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((musteri: IMusteri) => {
        musteri.dogumTarihi = musteri.dogumTarihi ? moment(musteri.dogumTarihi) : undefined;
      });
    }
    return res;
  }
}

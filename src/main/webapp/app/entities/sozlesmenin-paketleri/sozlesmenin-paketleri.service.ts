import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISozlesmeninPaketleri } from 'app/shared/model/sozlesmenin-paketleri.model';

type EntityResponseType = HttpResponse<ISozlesmeninPaketleri>;
type EntityArrayResponseType = HttpResponse<ISozlesmeninPaketleri[]>;

@Injectable({ providedIn: 'root' })
export class SozlesmeninPaketleriService {
  public resourceUrl = SERVER_API_URL + 'api/sozlesmenin-paketleris';

  constructor(protected http: HttpClient) {}

  create(sozlesmeninPaketleri: ISozlesmeninPaketleri): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sozlesmeninPaketleri);
    return this.http
      .post<ISozlesmeninPaketleri>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(sozlesmeninPaketleri: ISozlesmeninPaketleri): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(sozlesmeninPaketleri);
    return this.http
      .put<ISozlesmeninPaketleri>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ISozlesmeninPaketleri>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ISozlesmeninPaketleri[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(sozlesmeninPaketleri: ISozlesmeninPaketleri): ISozlesmeninPaketleri {
    const copy: ISozlesmeninPaketleri = Object.assign({}, sozlesmeninPaketleri, {
      baslangicTarihi:
        sozlesmeninPaketleri.baslangicTarihi && sozlesmeninPaketleri.baslangicTarihi.isValid()
          ? sozlesmeninPaketleri.baslangicTarihi.toJSON()
          : undefined,
      bitisTarihi:
        sozlesmeninPaketleri.bitisTarihi && sozlesmeninPaketleri.bitisTarihi.isValid()
          ? sozlesmeninPaketleri.bitisTarihi.toJSON()
          : undefined,
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
      res.body.forEach((sozlesmeninPaketleri: ISozlesmeninPaketleri) => {
        sozlesmeninPaketleri.baslangicTarihi = sozlesmeninPaketleri.baslangicTarihi
          ? moment(sozlesmeninPaketleri.baslangicTarihi)
          : undefined;
        sozlesmeninPaketleri.bitisTarihi = sozlesmeninPaketleri.bitisTarihi ? moment(sozlesmeninPaketleri.bitisTarihi) : undefined;
      });
    }
    return res;
  }
}

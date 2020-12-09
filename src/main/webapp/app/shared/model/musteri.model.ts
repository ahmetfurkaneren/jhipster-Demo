import { Moment } from 'moment';
import { ISozlesme } from 'app/shared/model/sozlesme.model';
import { MusteriTipi } from 'app/shared/model/enumerations/musteri-tipi.model';

export interface IMusteri {
  id?: number;
  ad?: string;
  soyad?: string;
  email?: string;
  parola?: string;
  tC?: string;
  musteriTipi?: MusteriTipi;
  dogumTarihi?: Moment;
  sozlesmes?: ISozlesme[];
}

export class Musteri implements IMusteri {
  constructor(
    public id?: number,
    public ad?: string,
    public soyad?: string,
    public email?: string,
    public parola?: string,
    public tC?: string,
    public musteriTipi?: MusteriTipi,
    public dogumTarihi?: Moment,
    public sozlesmes?: ISozlesme[]
  ) {}
}

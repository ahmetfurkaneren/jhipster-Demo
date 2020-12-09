import { Moment } from 'moment';
import { ISozlesme } from 'app/shared/model/sozlesme.model';

export interface IFatura {
  id?: number;
  ilkOdemeTarihi?: Moment;
  sonOdemeTarihi?: Moment;
  odenenTarih?: Moment;
  toplamTutar?: number;
  sozlesme?: ISozlesme;
}

export class Fatura implements IFatura {
  constructor(
    public id?: number,
    public ilkOdemeTarihi?: Moment,
    public sonOdemeTarihi?: Moment,
    public odenenTarih?: Moment,
    public toplamTutar?: number,
    public sozlesme?: ISozlesme
  ) {}
}

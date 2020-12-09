import { Moment } from 'moment';
import { ISozlesmeninPaketleri } from 'app/shared/model/sozlesmenin-paketleri.model';
import { IFatura } from 'app/shared/model/fatura.model';
import { IMusteri } from 'app/shared/model/musteri.model';
import { ITelNo } from 'app/shared/model/tel-no.model';
import { Tip } from 'app/shared/model/enumerations/tip.model';

export interface ISozlesme {
  id?: number;
  tip?: Tip;
  tarih?: Moment;
  bitisTarihi?: Moment;
  sozlesmeninPaketleris?: ISozlesmeninPaketleri[];
  faturas?: IFatura[];
  musteri?: IMusteri;
  telNo?: ITelNo;
}

export class Sozlesme implements ISozlesme {
  constructor(
    public id?: number,
    public tip?: Tip,
    public tarih?: Moment,
    public bitisTarihi?: Moment,
    public sozlesmeninPaketleris?: ISozlesmeninPaketleri[],
    public faturas?: IFatura[],
    public musteri?: IMusteri,
    public telNo?: ITelNo
  ) {}
}

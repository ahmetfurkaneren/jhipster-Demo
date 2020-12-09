import { Moment } from 'moment';
import { ISozlesmeninPaketleri } from 'app/shared/model/sozlesmenin-paketleri.model';
import { ITelNo } from 'app/shared/model/tel-no.model';

export interface ISmsKullanim {
  id?: number;
  tarih?: Moment;
  icerik?: string;
  sozlesmeninPaketleri?: ISozlesmeninPaketleri;
  telNo?: ITelNo;
}

export class SmsKullanim implements ISmsKullanim {
  constructor(
    public id?: number,
    public tarih?: Moment,
    public icerik?: string,
    public sozlesmeninPaketleri?: ISozlesmeninPaketleri,
    public telNo?: ITelNo
  ) {}
}

import { Moment } from 'moment';
import { ISozlesmeninPaketleri } from 'app/shared/model/sozlesmenin-paketleri.model';
import { ITelNo } from 'app/shared/model/tel-no.model';

export interface IDakikaKullanim {
  id?: number;
  tarih?: Moment;
  miktar?: number;
  sozlesmeninPaketleri?: ISozlesmeninPaketleri;
  telNo?: ITelNo;
}

export class DakikaKullanim implements IDakikaKullanim {
  constructor(
    public id?: number,
    public tarih?: Moment,
    public miktar?: number,
    public sozlesmeninPaketleri?: ISozlesmeninPaketleri,
    public telNo?: ITelNo
  ) {}
}

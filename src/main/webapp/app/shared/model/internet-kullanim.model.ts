import { Moment } from 'moment';
import { ISozlesmeninPaketleri } from 'app/shared/model/sozlesmenin-paketleri.model';

export interface IInternetKullanim {
  id?: number;
  tarih?: Moment;
  miktar?: number;
  sozlesmeninPaketleri?: ISozlesmeninPaketleri;
}

export class InternetKullanim implements IInternetKullanim {
  constructor(public id?: number, public tarih?: Moment, public miktar?: number, public sozlesmeninPaketleri?: ISozlesmeninPaketleri) {}
}

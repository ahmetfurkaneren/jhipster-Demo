import { Moment } from 'moment';
import { ISozlesmeninPaketleri } from 'app/shared/model/sozlesmenin-paketleri.model';
import { Aktif } from 'app/shared/model/enumerations/aktif.model';
import { Tip } from 'app/shared/model/enumerations/tip.model';
import { PaketTipi } from 'app/shared/model/enumerations/paket-tipi.model';
import { Donem } from 'app/shared/model/enumerations/donem.model';

export interface IPaketler {
  id?: number;
  paketAdi?: string;
  aciklama?: string;
  baslangicTarihi?: Moment;
  bitisTarihi?: Moment;
  fiyat?: number;
  yeniMusteriFiyat?: number;
  tahahutSure?: number;
  dakika?: number;
  sms?: number;
  internet?: number;
  aktif?: Aktif;
  tip?: Tip;
  paketTipi?: PaketTipi;
  donem?: Donem;
  dakikaUcret?: number;
  smsUcret?: number;
  internetUcret?: number;
  sozlesmeninPaketleris?: ISozlesmeninPaketleri[];
}

export class Paketler implements IPaketler {
  constructor(
    public id?: number,
    public paketAdi?: string,
    public aciklama?: string,
    public baslangicTarihi?: Moment,
    public bitisTarihi?: Moment,
    public fiyat?: number,
    public yeniMusteriFiyat?: number,
    public tahahutSure?: number,
    public dakika?: number,
    public sms?: number,
    public internet?: number,
    public aktif?: Aktif,
    public tip?: Tip,
    public paketTipi?: PaketTipi,
    public donem?: Donem,
    public dakikaUcret?: number,
    public smsUcret?: number,
    public internetUcret?: number,
    public sozlesmeninPaketleris?: ISozlesmeninPaketleri[]
  ) {}
}

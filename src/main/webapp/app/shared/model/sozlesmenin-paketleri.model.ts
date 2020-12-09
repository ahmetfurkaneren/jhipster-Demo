import { Moment } from 'moment';
import { IDakikaKullanim } from 'app/shared/model/dakika-kullanim.model';
import { ISmsKullanim } from 'app/shared/model/sms-kullanim.model';
import { IInternetKullanim } from 'app/shared/model/internet-kullanim.model';
import { ISozlesme } from 'app/shared/model/sozlesme.model';
import { IPaketler } from 'app/shared/model/paketler.model';

export interface ISozlesmeninPaketleri {
  id?: number;
  fiyat?: number;
  baslangicTarihi?: Moment;
  bitisTarihi?: Moment;
  kalanDakika?: number;
  kalanSms?: number;
  kalanInternet?: number;
  dakikaKullanims?: IDakikaKullanim[];
  smsKullanims?: ISmsKullanim[];
  internetKullanims?: IInternetKullanim[];
  sozlesme?: ISozlesme;
  paketler?: IPaketler;
}

export class SozlesmeninPaketleri implements ISozlesmeninPaketleri {
  constructor(
    public id?: number,
    public fiyat?: number,
    public baslangicTarihi?: Moment,
    public bitisTarihi?: Moment,
    public kalanDakika?: number,
    public kalanSms?: number,
    public kalanInternet?: number,
    public dakikaKullanims?: IDakikaKullanim[],
    public smsKullanims?: ISmsKullanim[],
    public internetKullanims?: IInternetKullanim[],
    public sozlesme?: ISozlesme,
    public paketler?: IPaketler
  ) {}
}

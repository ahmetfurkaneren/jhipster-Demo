import { ISozlesme } from 'app/shared/model/sozlesme.model';
import { IDakikaKullanim } from 'app/shared/model/dakika-kullanim.model';
import { ISmsKullanim } from 'app/shared/model/sms-kullanim.model';

export interface ITelNo {
  id?: number;
  numara?: number;
  sozlesmes?: ISozlesme[];
  dakikaKullanims?: IDakikaKullanim[];
  smsKullanims?: ISmsKullanim[];
}

export class TelNo implements ITelNo {
  constructor(
    public id?: number,
    public numara?: number,
    public sozlesmes?: ISozlesme[],
    public dakikaKullanims?: IDakikaKullanim[],
    public smsKullanims?: ISmsKullanim[]
  ) {}
}

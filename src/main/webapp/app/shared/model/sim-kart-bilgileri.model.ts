import { ISozlesme } from 'app/shared/model/sozlesme.model';

export interface ISimKartBilgileri {
  id?: number;
  pinNo?: number;
  pukNo?: number;
  barkod?: string;
  bitMiktari?: number;
  sozlesme?: ISozlesme;
}

export class SimKartBilgileri implements ISimKartBilgileri {
  constructor(
    public id?: number,
    public pinNo?: number,
    public pukNo?: number,
    public barkod?: string,
    public bitMiktari?: number,
    public sozlesme?: ISozlesme
  ) {}
}

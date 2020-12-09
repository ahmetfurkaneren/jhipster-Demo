import { IMusteri } from 'app/shared/model/musteri.model';

export interface ISirketBilgileri {
  id?: number;
  sirketAdi?: string;
  sirketAdresi?: string;
  musteri?: IMusteri;
}

export class SirketBilgileri implements ISirketBilgileri {
  constructor(public id?: number, public sirketAdi?: string, public sirketAdresi?: string, public musteri?: IMusteri) {}
}

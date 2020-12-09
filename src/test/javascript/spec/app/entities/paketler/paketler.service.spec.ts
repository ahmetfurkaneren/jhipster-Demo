import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { PaketlerService } from 'app/entities/paketler/paketler.service';
import { IPaketler, Paketler } from 'app/shared/model/paketler.model';
import { Aktif } from 'app/shared/model/enumerations/aktif.model';
import { Tip } from 'app/shared/model/enumerations/tip.model';
import { PaketTipi } from 'app/shared/model/enumerations/paket-tipi.model';
import { Donem } from 'app/shared/model/enumerations/donem.model';

describe('Service Tests', () => {
  describe('Paketler Service', () => {
    let injector: TestBed;
    let service: PaketlerService;
    let httpMock: HttpTestingController;
    let elemDefault: IPaketler;
    let expectedResult: IPaketler | IPaketler[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(PaketlerService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Paketler(
        0,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        0,
        0,
        0,
        0,
        0,
        0,
        Aktif.Akrif,
        Tip.AnaPaket,
        PaketTipi.Faturali,
        Donem.Aylik,
        0,
        0,
        0
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            baslangicTarihi: currentDate.format(DATE_TIME_FORMAT),
            bitisTarihi: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Paketler', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            baslangicTarihi: currentDate.format(DATE_TIME_FORMAT),
            bitisTarihi: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            baslangicTarihi: currentDate,
            bitisTarihi: currentDate,
          },
          returnedFromService
        );

        service.create(new Paketler()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Paketler', () => {
        const returnedFromService = Object.assign(
          {
            paketAdi: 'BBBBBB',
            aciklama: 'BBBBBB',
            baslangicTarihi: currentDate.format(DATE_TIME_FORMAT),
            bitisTarihi: currentDate.format(DATE_TIME_FORMAT),
            fiyat: 1,
            yeniMusteriFiyat: 1,
            tahahutSure: 1,
            dakika: 1,
            sms: 1,
            internet: 1,
            aktif: 'BBBBBB',
            tip: 'BBBBBB',
            paketTipi: 'BBBBBB',
            donem: 'BBBBBB',
            dakikaUcret: 1,
            smsUcret: 1,
            internetUcret: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            baslangicTarihi: currentDate,
            bitisTarihi: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Paketler', () => {
        const returnedFromService = Object.assign(
          {
            paketAdi: 'BBBBBB',
            aciklama: 'BBBBBB',
            baslangicTarihi: currentDate.format(DATE_TIME_FORMAT),
            bitisTarihi: currentDate.format(DATE_TIME_FORMAT),
            fiyat: 1,
            yeniMusteriFiyat: 1,
            tahahutSure: 1,
            dakika: 1,
            sms: 1,
            internet: 1,
            aktif: 'BBBBBB',
            tip: 'BBBBBB',
            paketTipi: 'BBBBBB',
            donem: 'BBBBBB',
            dakikaUcret: 1,
            smsUcret: 1,
            internetUcret: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            baslangicTarihi: currentDate,
            bitisTarihi: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Paketler', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});

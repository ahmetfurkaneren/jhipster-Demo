import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { SozlesmeninPaketleriService } from 'app/entities/sozlesmenin-paketleri/sozlesmenin-paketleri.service';
import { ISozlesmeninPaketleri, SozlesmeninPaketleri } from 'app/shared/model/sozlesmenin-paketleri.model';

describe('Service Tests', () => {
  describe('SozlesmeninPaketleri Service', () => {
    let injector: TestBed;
    let service: SozlesmeninPaketleriService;
    let httpMock: HttpTestingController;
    let elemDefault: ISozlesmeninPaketleri;
    let expectedResult: ISozlesmeninPaketleri | ISozlesmeninPaketleri[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(SozlesmeninPaketleriService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new SozlesmeninPaketleri(0, 0, currentDate, currentDate, 0, 0, 0);
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

      it('should create a SozlesmeninPaketleri', () => {
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

        service.create(new SozlesmeninPaketleri()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a SozlesmeninPaketleri', () => {
        const returnedFromService = Object.assign(
          {
            fiyat: 1,
            baslangicTarihi: currentDate.format(DATE_TIME_FORMAT),
            bitisTarihi: currentDate.format(DATE_TIME_FORMAT),
            kalanDakika: 1,
            kalanSms: 1,
            kalanInternet: 1,
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

      it('should return a list of SozlesmeninPaketleri', () => {
        const returnedFromService = Object.assign(
          {
            fiyat: 1,
            baslangicTarihi: currentDate.format(DATE_TIME_FORMAT),
            bitisTarihi: currentDate.format(DATE_TIME_FORMAT),
            kalanDakika: 1,
            kalanSms: 1,
            kalanInternet: 1,
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

      it('should delete a SozlesmeninPaketleri', () => {
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

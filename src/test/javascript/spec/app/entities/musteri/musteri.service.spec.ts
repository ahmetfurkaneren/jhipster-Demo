import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { MusteriService } from 'app/entities/musteri/musteri.service';
import { IMusteri, Musteri } from 'app/shared/model/musteri.model';
import { MusteriTipi } from 'app/shared/model/enumerations/musteri-tipi.model';

describe('Service Tests', () => {
  describe('Musteri Service', () => {
    let injector: TestBed;
    let service: MusteriService;
    let httpMock: HttpTestingController;
    let elemDefault: IMusteri;
    let expectedResult: IMusteri | IMusteri[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(MusteriService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Musteri(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', MusteriTipi.Bireysel, currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dogumTarihi: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Musteri', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dogumTarihi: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dogumTarihi: currentDate,
          },
          returnedFromService
        );

        service.create(new Musteri()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Musteri', () => {
        const returnedFromService = Object.assign(
          {
            ad: 'BBBBBB',
            soyad: 'BBBBBB',
            email: 'BBBBBB',
            parola: 'BBBBBB',
            tC: 'BBBBBB',
            musteriTipi: 'BBBBBB',
            dogumTarihi: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dogumTarihi: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Musteri', () => {
        const returnedFromService = Object.assign(
          {
            ad: 'BBBBBB',
            soyad: 'BBBBBB',
            email: 'BBBBBB',
            parola: 'BBBBBB',
            tC: 'BBBBBB',
            musteriTipi: 'BBBBBB',
            dogumTarihi: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dogumTarihi: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Musteri', () => {
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

import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { SozlesmeService } from 'app/entities/sozlesme/sozlesme.service';
import { ISozlesme, Sozlesme } from 'app/shared/model/sozlesme.model';
import { Tip } from 'app/shared/model/enumerations/tip.model';

describe('Service Tests', () => {
  describe('Sozlesme Service', () => {
    let injector: TestBed;
    let service: SozlesmeService;
    let httpMock: HttpTestingController;
    let elemDefault: ISozlesme;
    let expectedResult: ISozlesme | ISozlesme[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(SozlesmeService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Sozlesme(0, Tip.AnaPaket, currentDate, currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            tarih: currentDate.format(DATE_TIME_FORMAT),
            bitisTarihi: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Sozlesme', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            tarih: currentDate.format(DATE_TIME_FORMAT),
            bitisTarihi: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            tarih: currentDate,
            bitisTarihi: currentDate,
          },
          returnedFromService
        );

        service.create(new Sozlesme()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Sozlesme', () => {
        const returnedFromService = Object.assign(
          {
            tip: 'BBBBBB',
            tarih: currentDate.format(DATE_TIME_FORMAT),
            bitisTarihi: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            tarih: currentDate,
            bitisTarihi: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Sozlesme', () => {
        const returnedFromService = Object.assign(
          {
            tip: 'BBBBBB',
            tarih: currentDate.format(DATE_TIME_FORMAT),
            bitisTarihi: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            tarih: currentDate,
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

      it('should delete a Sozlesme', () => {
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

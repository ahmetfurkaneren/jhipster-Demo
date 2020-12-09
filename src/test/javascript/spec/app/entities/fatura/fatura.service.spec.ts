import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { FaturaService } from 'app/entities/fatura/fatura.service';
import { IFatura, Fatura } from 'app/shared/model/fatura.model';

describe('Service Tests', () => {
  describe('Fatura Service', () => {
    let injector: TestBed;
    let service: FaturaService;
    let httpMock: HttpTestingController;
    let elemDefault: IFatura;
    let expectedResult: IFatura | IFatura[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(FaturaService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Fatura(0, currentDate, currentDate, currentDate, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            ilkOdemeTarihi: currentDate.format(DATE_TIME_FORMAT),
            sonOdemeTarihi: currentDate.format(DATE_TIME_FORMAT),
            odenenTarih: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Fatura', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            ilkOdemeTarihi: currentDate.format(DATE_TIME_FORMAT),
            sonOdemeTarihi: currentDate.format(DATE_TIME_FORMAT),
            odenenTarih: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            ilkOdemeTarihi: currentDate,
            sonOdemeTarihi: currentDate,
            odenenTarih: currentDate,
          },
          returnedFromService
        );

        service.create(new Fatura()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Fatura', () => {
        const returnedFromService = Object.assign(
          {
            ilkOdemeTarihi: currentDate.format(DATE_TIME_FORMAT),
            sonOdemeTarihi: currentDate.format(DATE_TIME_FORMAT),
            odenenTarih: currentDate.format(DATE_TIME_FORMAT),
            toplamTutar: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            ilkOdemeTarihi: currentDate,
            sonOdemeTarihi: currentDate,
            odenenTarih: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Fatura', () => {
        const returnedFromService = Object.assign(
          {
            ilkOdemeTarihi: currentDate.format(DATE_TIME_FORMAT),
            sonOdemeTarihi: currentDate.format(DATE_TIME_FORMAT),
            odenenTarih: currentDate.format(DATE_TIME_FORMAT),
            toplamTutar: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            ilkOdemeTarihi: currentDate,
            sonOdemeTarihi: currentDate,
            odenenTarih: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Fatura', () => {
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

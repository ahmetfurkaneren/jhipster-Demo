import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { InternetKullanimService } from 'app/entities/internet-kullanim/internet-kullanim.service';
import { IInternetKullanim, InternetKullanim } from 'app/shared/model/internet-kullanim.model';

describe('Service Tests', () => {
  describe('InternetKullanim Service', () => {
    let injector: TestBed;
    let service: InternetKullanimService;
    let httpMock: HttpTestingController;
    let elemDefault: IInternetKullanim;
    let expectedResult: IInternetKullanim | IInternetKullanim[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(InternetKullanimService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new InternetKullanim(0, currentDate, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            tarih: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a InternetKullanim', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            tarih: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            tarih: currentDate,
          },
          returnedFromService
        );

        service.create(new InternetKullanim()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a InternetKullanim', () => {
        const returnedFromService = Object.assign(
          {
            tarih: currentDate.format(DATE_TIME_FORMAT),
            miktar: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            tarih: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of InternetKullanim', () => {
        const returnedFromService = Object.assign(
          {
            tarih: currentDate.format(DATE_TIME_FORMAT),
            miktar: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            tarih: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a InternetKullanim', () => {
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

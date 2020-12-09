import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { SimKartBilgileriService } from 'app/entities/sim-kart-bilgileri/sim-kart-bilgileri.service';
import { ISimKartBilgileri, SimKartBilgileri } from 'app/shared/model/sim-kart-bilgileri.model';

describe('Service Tests', () => {
  describe('SimKartBilgileri Service', () => {
    let injector: TestBed;
    let service: SimKartBilgileriService;
    let httpMock: HttpTestingController;
    let elemDefault: ISimKartBilgileri;
    let expectedResult: ISimKartBilgileri | ISimKartBilgileri[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(SimKartBilgileriService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new SimKartBilgileri(0, 0, 0, 'AAAAAAA', 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a SimKartBilgileri', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new SimKartBilgileri()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a SimKartBilgileri', () => {
        const returnedFromService = Object.assign(
          {
            pinNo: 1,
            pukNo: 1,
            barkod: 'BBBBBB',
            bitMiktari: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of SimKartBilgileri', () => {
        const returnedFromService = Object.assign(
          {
            pinNo: 1,
            pukNo: 1,
            barkod: 'BBBBBB',
            bitMiktari: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a SimKartBilgileri', () => {
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

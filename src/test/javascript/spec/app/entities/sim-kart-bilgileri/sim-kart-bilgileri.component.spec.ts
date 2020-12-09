import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterDemoTestModule } from '../../../test.module';
import { SimKartBilgileriComponent } from 'app/entities/sim-kart-bilgileri/sim-kart-bilgileri.component';
import { SimKartBilgileriService } from 'app/entities/sim-kart-bilgileri/sim-kart-bilgileri.service';
import { SimKartBilgileri } from 'app/shared/model/sim-kart-bilgileri.model';

describe('Component Tests', () => {
  describe('SimKartBilgileri Management Component', () => {
    let comp: SimKartBilgileriComponent;
    let fixture: ComponentFixture<SimKartBilgileriComponent>;
    let service: SimKartBilgileriService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterDemoTestModule],
        declarations: [SimKartBilgileriComponent],
      })
        .overrideTemplate(SimKartBilgileriComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SimKartBilgileriComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SimKartBilgileriService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SimKartBilgileri(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.simKartBilgileris && comp.simKartBilgileris[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

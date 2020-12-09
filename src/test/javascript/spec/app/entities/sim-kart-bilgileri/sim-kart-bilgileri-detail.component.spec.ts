import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterDemoTestModule } from '../../../test.module';
import { SimKartBilgileriDetailComponent } from 'app/entities/sim-kart-bilgileri/sim-kart-bilgileri-detail.component';
import { SimKartBilgileri } from 'app/shared/model/sim-kart-bilgileri.model';

describe('Component Tests', () => {
  describe('SimKartBilgileri Management Detail Component', () => {
    let comp: SimKartBilgileriDetailComponent;
    let fixture: ComponentFixture<SimKartBilgileriDetailComponent>;
    const route = ({ data: of({ simKartBilgileri: new SimKartBilgileri(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterDemoTestModule],
        declarations: [SimKartBilgileriDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SimKartBilgileriDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SimKartBilgileriDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load simKartBilgileri on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.simKartBilgileri).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterDemoTestModule } from '../../../test.module';
import { SirketBilgileriDetailComponent } from 'app/entities/sirket-bilgileri/sirket-bilgileri-detail.component';
import { SirketBilgileri } from 'app/shared/model/sirket-bilgileri.model';

describe('Component Tests', () => {
  describe('SirketBilgileri Management Detail Component', () => {
    let comp: SirketBilgileriDetailComponent;
    let fixture: ComponentFixture<SirketBilgileriDetailComponent>;
    const route = ({ data: of({ sirketBilgileri: new SirketBilgileri(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterDemoTestModule],
        declarations: [SirketBilgileriDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SirketBilgileriDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SirketBilgileriDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load sirketBilgileri on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sirketBilgileri).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

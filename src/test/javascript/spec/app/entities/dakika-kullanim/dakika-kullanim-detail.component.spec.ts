import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterDemoTestModule } from '../../../test.module';
import { DakikaKullanimDetailComponent } from 'app/entities/dakika-kullanim/dakika-kullanim-detail.component';
import { DakikaKullanim } from 'app/shared/model/dakika-kullanim.model';

describe('Component Tests', () => {
  describe('DakikaKullanim Management Detail Component', () => {
    let comp: DakikaKullanimDetailComponent;
    let fixture: ComponentFixture<DakikaKullanimDetailComponent>;
    const route = ({ data: of({ dakikaKullanim: new DakikaKullanim(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterDemoTestModule],
        declarations: [DakikaKullanimDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(DakikaKullanimDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DakikaKullanimDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load dakikaKullanim on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.dakikaKullanim).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

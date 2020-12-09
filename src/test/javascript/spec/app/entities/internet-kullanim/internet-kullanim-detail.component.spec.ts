import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterDemoTestModule } from '../../../test.module';
import { InternetKullanimDetailComponent } from 'app/entities/internet-kullanim/internet-kullanim-detail.component';
import { InternetKullanim } from 'app/shared/model/internet-kullanim.model';

describe('Component Tests', () => {
  describe('InternetKullanim Management Detail Component', () => {
    let comp: InternetKullanimDetailComponent;
    let fixture: ComponentFixture<InternetKullanimDetailComponent>;
    const route = ({ data: of({ internetKullanim: new InternetKullanim(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterDemoTestModule],
        declarations: [InternetKullanimDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(InternetKullanimDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(InternetKullanimDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load internetKullanim on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.internetKullanim).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

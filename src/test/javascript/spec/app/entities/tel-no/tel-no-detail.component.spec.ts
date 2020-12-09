import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterDemoTestModule } from '../../../test.module';
import { TelNoDetailComponent } from 'app/entities/tel-no/tel-no-detail.component';
import { TelNo } from 'app/shared/model/tel-no.model';

describe('Component Tests', () => {
  describe('TelNo Management Detail Component', () => {
    let comp: TelNoDetailComponent;
    let fixture: ComponentFixture<TelNoDetailComponent>;
    const route = ({ data: of({ telNo: new TelNo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterDemoTestModule],
        declarations: [TelNoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TelNoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TelNoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load telNo on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.telNo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

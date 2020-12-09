import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterDemoTestModule } from '../../../test.module';
import { SozlesmeDetailComponent } from 'app/entities/sozlesme/sozlesme-detail.component';
import { Sozlesme } from 'app/shared/model/sozlesme.model';

describe('Component Tests', () => {
  describe('Sozlesme Management Detail Component', () => {
    let comp: SozlesmeDetailComponent;
    let fixture: ComponentFixture<SozlesmeDetailComponent>;
    const route = ({ data: of({ sozlesme: new Sozlesme(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterDemoTestModule],
        declarations: [SozlesmeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SozlesmeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SozlesmeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load sozlesme on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sozlesme).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

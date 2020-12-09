import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterDemoTestModule } from '../../../test.module';
import { SozlesmeninPaketleriDetailComponent } from 'app/entities/sozlesmenin-paketleri/sozlesmenin-paketleri-detail.component';
import { SozlesmeninPaketleri } from 'app/shared/model/sozlesmenin-paketleri.model';

describe('Component Tests', () => {
  describe('SozlesmeninPaketleri Management Detail Component', () => {
    let comp: SozlesmeninPaketleriDetailComponent;
    let fixture: ComponentFixture<SozlesmeninPaketleriDetailComponent>;
    const route = ({ data: of({ sozlesmeninPaketleri: new SozlesmeninPaketleri(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterDemoTestModule],
        declarations: [SozlesmeninPaketleriDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SozlesmeninPaketleriDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SozlesmeninPaketleriDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load sozlesmeninPaketleri on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sozlesmeninPaketleri).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

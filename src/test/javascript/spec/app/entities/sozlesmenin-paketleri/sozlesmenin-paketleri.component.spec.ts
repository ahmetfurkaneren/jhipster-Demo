import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterDemoTestModule } from '../../../test.module';
import { SozlesmeninPaketleriComponent } from 'app/entities/sozlesmenin-paketleri/sozlesmenin-paketleri.component';
import { SozlesmeninPaketleriService } from 'app/entities/sozlesmenin-paketleri/sozlesmenin-paketleri.service';
import { SozlesmeninPaketleri } from 'app/shared/model/sozlesmenin-paketleri.model';

describe('Component Tests', () => {
  describe('SozlesmeninPaketleri Management Component', () => {
    let comp: SozlesmeninPaketleriComponent;
    let fixture: ComponentFixture<SozlesmeninPaketleriComponent>;
    let service: SozlesmeninPaketleriService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterDemoTestModule],
        declarations: [SozlesmeninPaketleriComponent],
      })
        .overrideTemplate(SozlesmeninPaketleriComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SozlesmeninPaketleriComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SozlesmeninPaketleriService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SozlesmeninPaketleri(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.sozlesmeninPaketleris && comp.sozlesmeninPaketleris[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterDemoTestModule } from '../../../test.module';
import { SozlesmeComponent } from 'app/entities/sozlesme/sozlesme.component';
import { SozlesmeService } from 'app/entities/sozlesme/sozlesme.service';
import { Sozlesme } from 'app/shared/model/sozlesme.model';

describe('Component Tests', () => {
  describe('Sozlesme Management Component', () => {
    let comp: SozlesmeComponent;
    let fixture: ComponentFixture<SozlesmeComponent>;
    let service: SozlesmeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterDemoTestModule],
        declarations: [SozlesmeComponent],
      })
        .overrideTemplate(SozlesmeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SozlesmeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SozlesmeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Sozlesme(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.sozlesmes && comp.sozlesmes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

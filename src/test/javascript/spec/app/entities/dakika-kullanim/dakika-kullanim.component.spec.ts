import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterDemoTestModule } from '../../../test.module';
import { DakikaKullanimComponent } from 'app/entities/dakika-kullanim/dakika-kullanim.component';
import { DakikaKullanimService } from 'app/entities/dakika-kullanim/dakika-kullanim.service';
import { DakikaKullanim } from 'app/shared/model/dakika-kullanim.model';

describe('Component Tests', () => {
  describe('DakikaKullanim Management Component', () => {
    let comp: DakikaKullanimComponent;
    let fixture: ComponentFixture<DakikaKullanimComponent>;
    let service: DakikaKullanimService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterDemoTestModule],
        declarations: [DakikaKullanimComponent],
      })
        .overrideTemplate(DakikaKullanimComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DakikaKullanimComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DakikaKullanimService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new DakikaKullanim(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.dakikaKullanims && comp.dakikaKullanims[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

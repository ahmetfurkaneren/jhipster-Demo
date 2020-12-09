import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterDemoTestModule } from '../../../test.module';
import { InternetKullanimComponent } from 'app/entities/internet-kullanim/internet-kullanim.component';
import { InternetKullanimService } from 'app/entities/internet-kullanim/internet-kullanim.service';
import { InternetKullanim } from 'app/shared/model/internet-kullanim.model';

describe('Component Tests', () => {
  describe('InternetKullanim Management Component', () => {
    let comp: InternetKullanimComponent;
    let fixture: ComponentFixture<InternetKullanimComponent>;
    let service: InternetKullanimService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterDemoTestModule],
        declarations: [InternetKullanimComponent],
      })
        .overrideTemplate(InternetKullanimComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InternetKullanimComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InternetKullanimService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new InternetKullanim(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.internetKullanims && comp.internetKullanims[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

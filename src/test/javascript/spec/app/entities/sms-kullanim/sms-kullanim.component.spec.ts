import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterDemoTestModule } from '../../../test.module';
import { SmsKullanimComponent } from 'app/entities/sms-kullanim/sms-kullanim.component';
import { SmsKullanimService } from 'app/entities/sms-kullanim/sms-kullanim.service';
import { SmsKullanim } from 'app/shared/model/sms-kullanim.model';

describe('Component Tests', () => {
  describe('SmsKullanim Management Component', () => {
    let comp: SmsKullanimComponent;
    let fixture: ComponentFixture<SmsKullanimComponent>;
    let service: SmsKullanimService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterDemoTestModule],
        declarations: [SmsKullanimComponent],
      })
        .overrideTemplate(SmsKullanimComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SmsKullanimComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SmsKullanimService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SmsKullanim(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.smsKullanims && comp.smsKullanims[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

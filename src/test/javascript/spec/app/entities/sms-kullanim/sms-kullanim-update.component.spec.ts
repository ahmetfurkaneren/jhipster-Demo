import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterDemoTestModule } from '../../../test.module';
import { SmsKullanimUpdateComponent } from 'app/entities/sms-kullanim/sms-kullanim-update.component';
import { SmsKullanimService } from 'app/entities/sms-kullanim/sms-kullanim.service';
import { SmsKullanim } from 'app/shared/model/sms-kullanim.model';

describe('Component Tests', () => {
  describe('SmsKullanim Management Update Component', () => {
    let comp: SmsKullanimUpdateComponent;
    let fixture: ComponentFixture<SmsKullanimUpdateComponent>;
    let service: SmsKullanimService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterDemoTestModule],
        declarations: [SmsKullanimUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SmsKullanimUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SmsKullanimUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SmsKullanimService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SmsKullanim(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new SmsKullanim();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});

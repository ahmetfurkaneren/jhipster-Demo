import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterDemoTestModule } from '../../../test.module';
import { InternetKullanimUpdateComponent } from 'app/entities/internet-kullanim/internet-kullanim-update.component';
import { InternetKullanimService } from 'app/entities/internet-kullanim/internet-kullanim.service';
import { InternetKullanim } from 'app/shared/model/internet-kullanim.model';

describe('Component Tests', () => {
  describe('InternetKullanim Management Update Component', () => {
    let comp: InternetKullanimUpdateComponent;
    let fixture: ComponentFixture<InternetKullanimUpdateComponent>;
    let service: InternetKullanimService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterDemoTestModule],
        declarations: [InternetKullanimUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(InternetKullanimUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(InternetKullanimUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(InternetKullanimService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new InternetKullanim(123);
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
        const entity = new InternetKullanim();
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

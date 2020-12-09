import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterDemoTestModule } from '../../../test.module';
import { DakikaKullanimUpdateComponent } from 'app/entities/dakika-kullanim/dakika-kullanim-update.component';
import { DakikaKullanimService } from 'app/entities/dakika-kullanim/dakika-kullanim.service';
import { DakikaKullanim } from 'app/shared/model/dakika-kullanim.model';

describe('Component Tests', () => {
  describe('DakikaKullanim Management Update Component', () => {
    let comp: DakikaKullanimUpdateComponent;
    let fixture: ComponentFixture<DakikaKullanimUpdateComponent>;
    let service: DakikaKullanimService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterDemoTestModule],
        declarations: [DakikaKullanimUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DakikaKullanimUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DakikaKullanimUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DakikaKullanimService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DakikaKullanim(123);
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
        const entity = new DakikaKullanim();
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

import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterDemoTestModule } from '../../../test.module';
import { MusteriUpdateComponent } from 'app/entities/musteri/musteri-update.component';
import { MusteriService } from 'app/entities/musteri/musteri.service';
import { Musteri } from 'app/shared/model/musteri.model';

describe('Component Tests', () => {
  describe('Musteri Management Update Component', () => {
    let comp: MusteriUpdateComponent;
    let fixture: ComponentFixture<MusteriUpdateComponent>;
    let service: MusteriService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterDemoTestModule],
        declarations: [MusteriUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(MusteriUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MusteriUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MusteriService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Musteri(123);
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
        const entity = new Musteri();
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

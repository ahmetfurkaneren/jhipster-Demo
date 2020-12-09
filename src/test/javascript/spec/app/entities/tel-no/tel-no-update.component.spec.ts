import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterDemoTestModule } from '../../../test.module';
import { TelNoUpdateComponent } from 'app/entities/tel-no/tel-no-update.component';
import { TelNoService } from 'app/entities/tel-no/tel-no.service';
import { TelNo } from 'app/shared/model/tel-no.model';

describe('Component Tests', () => {
  describe('TelNo Management Update Component', () => {
    let comp: TelNoUpdateComponent;
    let fixture: ComponentFixture<TelNoUpdateComponent>;
    let service: TelNoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterDemoTestModule],
        declarations: [TelNoUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TelNoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TelNoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TelNoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TelNo(123);
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
        const entity = new TelNo();
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

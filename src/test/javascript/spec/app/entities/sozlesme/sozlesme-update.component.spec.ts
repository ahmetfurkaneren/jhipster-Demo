import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterDemoTestModule } from '../../../test.module';
import { SozlesmeUpdateComponent } from 'app/entities/sozlesme/sozlesme-update.component';
import { SozlesmeService } from 'app/entities/sozlesme/sozlesme.service';
import { Sozlesme } from 'app/shared/model/sozlesme.model';

describe('Component Tests', () => {
  describe('Sozlesme Management Update Component', () => {
    let comp: SozlesmeUpdateComponent;
    let fixture: ComponentFixture<SozlesmeUpdateComponent>;
    let service: SozlesmeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterDemoTestModule],
        declarations: [SozlesmeUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SozlesmeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SozlesmeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SozlesmeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Sozlesme(123);
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
        const entity = new Sozlesme();
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

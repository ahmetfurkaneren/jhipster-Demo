import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterDemoTestModule } from '../../../test.module';
import { PaketlerUpdateComponent } from 'app/entities/paketler/paketler-update.component';
import { PaketlerService } from 'app/entities/paketler/paketler.service';
import { Paketler } from 'app/shared/model/paketler.model';

describe('Component Tests', () => {
  describe('Paketler Management Update Component', () => {
    let comp: PaketlerUpdateComponent;
    let fixture: ComponentFixture<PaketlerUpdateComponent>;
    let service: PaketlerService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterDemoTestModule],
        declarations: [PaketlerUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PaketlerUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PaketlerUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PaketlerService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Paketler(123);
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
        const entity = new Paketler();
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

import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterDemoTestModule } from '../../../test.module';
import { SozlesmeninPaketleriUpdateComponent } from 'app/entities/sozlesmenin-paketleri/sozlesmenin-paketleri-update.component';
import { SozlesmeninPaketleriService } from 'app/entities/sozlesmenin-paketleri/sozlesmenin-paketleri.service';
import { SozlesmeninPaketleri } from 'app/shared/model/sozlesmenin-paketleri.model';

describe('Component Tests', () => {
  describe('SozlesmeninPaketleri Management Update Component', () => {
    let comp: SozlesmeninPaketleriUpdateComponent;
    let fixture: ComponentFixture<SozlesmeninPaketleriUpdateComponent>;
    let service: SozlesmeninPaketleriService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterDemoTestModule],
        declarations: [SozlesmeninPaketleriUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SozlesmeninPaketleriUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SozlesmeninPaketleriUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SozlesmeninPaketleriService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SozlesmeninPaketleri(123);
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
        const entity = new SozlesmeninPaketleri();
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

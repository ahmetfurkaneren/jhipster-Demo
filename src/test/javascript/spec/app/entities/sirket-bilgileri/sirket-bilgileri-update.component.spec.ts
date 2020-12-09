import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterDemoTestModule } from '../../../test.module';
import { SirketBilgileriUpdateComponent } from 'app/entities/sirket-bilgileri/sirket-bilgileri-update.component';
import { SirketBilgileriService } from 'app/entities/sirket-bilgileri/sirket-bilgileri.service';
import { SirketBilgileri } from 'app/shared/model/sirket-bilgileri.model';

describe('Component Tests', () => {
  describe('SirketBilgileri Management Update Component', () => {
    let comp: SirketBilgileriUpdateComponent;
    let fixture: ComponentFixture<SirketBilgileriUpdateComponent>;
    let service: SirketBilgileriService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterDemoTestModule],
        declarations: [SirketBilgileriUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SirketBilgileriUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SirketBilgileriUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SirketBilgileriService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SirketBilgileri(123);
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
        const entity = new SirketBilgileri();
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

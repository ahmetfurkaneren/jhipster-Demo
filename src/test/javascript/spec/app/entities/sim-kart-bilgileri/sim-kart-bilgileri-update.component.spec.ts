import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { JhipsterDemoTestModule } from '../../../test.module';
import { SimKartBilgileriUpdateComponent } from 'app/entities/sim-kart-bilgileri/sim-kart-bilgileri-update.component';
import { SimKartBilgileriService } from 'app/entities/sim-kart-bilgileri/sim-kart-bilgileri.service';
import { SimKartBilgileri } from 'app/shared/model/sim-kart-bilgileri.model';

describe('Component Tests', () => {
  describe('SimKartBilgileri Management Update Component', () => {
    let comp: SimKartBilgileriUpdateComponent;
    let fixture: ComponentFixture<SimKartBilgileriUpdateComponent>;
    let service: SimKartBilgileriService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterDemoTestModule],
        declarations: [SimKartBilgileriUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SimKartBilgileriUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SimKartBilgileriUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SimKartBilgileriService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SimKartBilgileri(123);
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
        const entity = new SimKartBilgileri();
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

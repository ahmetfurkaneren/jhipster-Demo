import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterDemoTestModule } from '../../../test.module';
import { MusteriComponent } from 'app/entities/musteri/musteri.component';
import { MusteriService } from 'app/entities/musteri/musteri.service';
import { Musteri } from 'app/shared/model/musteri.model';

describe('Component Tests', () => {
  describe('Musteri Management Component', () => {
    let comp: MusteriComponent;
    let fixture: ComponentFixture<MusteriComponent>;
    let service: MusteriService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterDemoTestModule],
        declarations: [MusteriComponent],
      })
        .overrideTemplate(MusteriComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MusteriComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MusteriService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Musteri(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.musteris && comp.musteris[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

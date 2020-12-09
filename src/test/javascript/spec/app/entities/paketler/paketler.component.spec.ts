import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterDemoTestModule } from '../../../test.module';
import { PaketlerComponent } from 'app/entities/paketler/paketler.component';
import { PaketlerService } from 'app/entities/paketler/paketler.service';
import { Paketler } from 'app/shared/model/paketler.model';

describe('Component Tests', () => {
  describe('Paketler Management Component', () => {
    let comp: PaketlerComponent;
    let fixture: ComponentFixture<PaketlerComponent>;
    let service: PaketlerService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterDemoTestModule],
        declarations: [PaketlerComponent],
      })
        .overrideTemplate(PaketlerComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PaketlerComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PaketlerService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Paketler(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.paketlers && comp.paketlers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

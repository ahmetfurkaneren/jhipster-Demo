import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterDemoTestModule } from '../../../test.module';
import { SirketBilgileriComponent } from 'app/entities/sirket-bilgileri/sirket-bilgileri.component';
import { SirketBilgileriService } from 'app/entities/sirket-bilgileri/sirket-bilgileri.service';
import { SirketBilgileri } from 'app/shared/model/sirket-bilgileri.model';

describe('Component Tests', () => {
  describe('SirketBilgileri Management Component', () => {
    let comp: SirketBilgileriComponent;
    let fixture: ComponentFixture<SirketBilgileriComponent>;
    let service: SirketBilgileriService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterDemoTestModule],
        declarations: [SirketBilgileriComponent],
      })
        .overrideTemplate(SirketBilgileriComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SirketBilgileriComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SirketBilgileriService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SirketBilgileri(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.sirketBilgileris && comp.sirketBilgileris[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

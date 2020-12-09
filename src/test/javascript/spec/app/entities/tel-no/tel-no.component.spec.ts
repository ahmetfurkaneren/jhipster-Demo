import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhipsterDemoTestModule } from '../../../test.module';
import { TelNoComponent } from 'app/entities/tel-no/tel-no.component';
import { TelNoService } from 'app/entities/tel-no/tel-no.service';
import { TelNo } from 'app/shared/model/tel-no.model';

describe('Component Tests', () => {
  describe('TelNo Management Component', () => {
    let comp: TelNoComponent;
    let fixture: ComponentFixture<TelNoComponent>;
    let service: TelNoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterDemoTestModule],
        declarations: [TelNoComponent],
      })
        .overrideTemplate(TelNoComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TelNoComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TelNoService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TelNo(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.telNos && comp.telNos[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});

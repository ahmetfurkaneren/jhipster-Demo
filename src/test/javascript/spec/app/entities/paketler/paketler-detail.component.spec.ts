import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterDemoTestModule } from '../../../test.module';
import { PaketlerDetailComponent } from 'app/entities/paketler/paketler-detail.component';
import { Paketler } from 'app/shared/model/paketler.model';

describe('Component Tests', () => {
  describe('Paketler Management Detail Component', () => {
    let comp: PaketlerDetailComponent;
    let fixture: ComponentFixture<PaketlerDetailComponent>;
    const route = ({ data: of({ paketler: new Paketler(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterDemoTestModule],
        declarations: [PaketlerDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PaketlerDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PaketlerDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load paketler on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.paketler).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

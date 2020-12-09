import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterDemoTestModule } from '../../../test.module';
import { MusteriDetailComponent } from 'app/entities/musteri/musteri-detail.component';
import { Musteri } from 'app/shared/model/musteri.model';

describe('Component Tests', () => {
  describe('Musteri Management Detail Component', () => {
    let comp: MusteriDetailComponent;
    let fixture: ComponentFixture<MusteriDetailComponent>;
    const route = ({ data: of({ musteri: new Musteri(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterDemoTestModule],
        declarations: [MusteriDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(MusteriDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MusteriDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load musteri on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.musteri).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

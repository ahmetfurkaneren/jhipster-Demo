import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JhipsterDemoTestModule } from '../../../test.module';
import { SmsKullanimDetailComponent } from 'app/entities/sms-kullanim/sms-kullanim-detail.component';
import { SmsKullanim } from 'app/shared/model/sms-kullanim.model';

describe('Component Tests', () => {
  describe('SmsKullanim Management Detail Component', () => {
    let comp: SmsKullanimDetailComponent;
    let fixture: ComponentFixture<SmsKullanimDetailComponent>;
    const route = ({ data: of({ smsKullanim: new SmsKullanim(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [JhipsterDemoTestModule],
        declarations: [SmsKullanimDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SmsKullanimDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SmsKullanimDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load smsKullanim on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.smsKullanim).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});

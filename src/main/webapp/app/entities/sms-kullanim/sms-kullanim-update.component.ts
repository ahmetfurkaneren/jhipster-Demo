import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISmsKullanim, SmsKullanim } from 'app/shared/model/sms-kullanim.model';
import { SmsKullanimService } from './sms-kullanim.service';
import { ISozlesmeninPaketleri } from 'app/shared/model/sozlesmenin-paketleri.model';
import { SozlesmeninPaketleriService } from 'app/entities/sozlesmenin-paketleri/sozlesmenin-paketleri.service';
import { ITelNo } from 'app/shared/model/tel-no.model';
import { TelNoService } from 'app/entities/tel-no/tel-no.service';

type SelectableEntity = ISozlesmeninPaketleri | ITelNo;

@Component({
  selector: 'jhi-sms-kullanim-update',
  templateUrl: './sms-kullanim-update.component.html',
})
export class SmsKullanimUpdateComponent implements OnInit {
  isSaving = false;
  sozlesmeninpaketleris: ISozlesmeninPaketleri[] = [];
  telnos: ITelNo[] = [];

  editForm = this.fb.group({
    id: [],
    tarih: [null, [Validators.required]],
    icerik: [null, [Validators.required]],
    sozlesmeninPaketleri: [null, Validators.required],
    telNo: [null, Validators.required],
  });

  constructor(
    protected smsKullanimService: SmsKullanimService,
    protected sozlesmeninPaketleriService: SozlesmeninPaketleriService,
    protected telNoService: TelNoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ smsKullanim }) => {
      if (!smsKullanim.id) {
        const today = moment().startOf('day');
        smsKullanim.tarih = today;
      }

      this.updateForm(smsKullanim);

      this.sozlesmeninPaketleriService
        .query()
        .subscribe((res: HttpResponse<ISozlesmeninPaketleri[]>) => (this.sozlesmeninpaketleris = res.body || []));

      this.telNoService.query().subscribe((res: HttpResponse<ITelNo[]>) => (this.telnos = res.body || []));
    });
  }

  updateForm(smsKullanim: ISmsKullanim): void {
    this.editForm.patchValue({
      id: smsKullanim.id,
      tarih: smsKullanim.tarih ? smsKullanim.tarih.format(DATE_TIME_FORMAT) : null,
      icerik: smsKullanim.icerik,
      sozlesmeninPaketleri: smsKullanim.sozlesmeninPaketleri,
      telNo: smsKullanim.telNo,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const smsKullanim = this.createFromForm();
    if (smsKullanim.id !== undefined) {
      this.subscribeToSaveResponse(this.smsKullanimService.update(smsKullanim));
    } else {
      this.subscribeToSaveResponse(this.smsKullanimService.create(smsKullanim));
    }
  }

  private createFromForm(): ISmsKullanim {
    return {
      ...new SmsKullanim(),
      id: this.editForm.get(['id'])!.value,
      tarih: this.editForm.get(['tarih'])!.value ? moment(this.editForm.get(['tarih'])!.value, DATE_TIME_FORMAT) : undefined,
      icerik: this.editForm.get(['icerik'])!.value,
      sozlesmeninPaketleri: this.editForm.get(['sozlesmeninPaketleri'])!.value,
      telNo: this.editForm.get(['telNo'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISmsKullanim>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}

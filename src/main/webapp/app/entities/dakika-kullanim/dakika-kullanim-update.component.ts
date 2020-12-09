import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IDakikaKullanim, DakikaKullanim } from 'app/shared/model/dakika-kullanim.model';
import { DakikaKullanimService } from './dakika-kullanim.service';
import { ISozlesmeninPaketleri } from 'app/shared/model/sozlesmenin-paketleri.model';
import { SozlesmeninPaketleriService } from 'app/entities/sozlesmenin-paketleri/sozlesmenin-paketleri.service';
import { ITelNo } from 'app/shared/model/tel-no.model';
import { TelNoService } from 'app/entities/tel-no/tel-no.service';

type SelectableEntity = ISozlesmeninPaketleri | ITelNo;

@Component({
  selector: 'jhi-dakika-kullanim-update',
  templateUrl: './dakika-kullanim-update.component.html',
})
export class DakikaKullanimUpdateComponent implements OnInit {
  isSaving = false;
  sozlesmeninpaketleris: ISozlesmeninPaketleri[] = [];
  telnos: ITelNo[] = [];

  editForm = this.fb.group({
    id: [],
    tarih: [null, [Validators.required]],
    miktar: [null, [Validators.required]],
    sozlesmeninPaketleri: [null, Validators.required],
    telNo: [null, Validators.required],
  });

  constructor(
    protected dakikaKullanimService: DakikaKullanimService,
    protected sozlesmeninPaketleriService: SozlesmeninPaketleriService,
    protected telNoService: TelNoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dakikaKullanim }) => {
      if (!dakikaKullanim.id) {
        const today = moment().startOf('day');
        dakikaKullanim.tarih = today;
      }

      this.updateForm(dakikaKullanim);

      this.sozlesmeninPaketleriService
        .query()
        .subscribe((res: HttpResponse<ISozlesmeninPaketleri[]>) => (this.sozlesmeninpaketleris = res.body || []));

      this.telNoService.query().subscribe((res: HttpResponse<ITelNo[]>) => (this.telnos = res.body || []));
    });
  }

  updateForm(dakikaKullanim: IDakikaKullanim): void {
    this.editForm.patchValue({
      id: dakikaKullanim.id,
      tarih: dakikaKullanim.tarih ? dakikaKullanim.tarih.format(DATE_TIME_FORMAT) : null,
      miktar: dakikaKullanim.miktar,
      sozlesmeninPaketleri: dakikaKullanim.sozlesmeninPaketleri,
      telNo: dakikaKullanim.telNo,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const dakikaKullanim = this.createFromForm();
    if (dakikaKullanim.id !== undefined) {
      this.subscribeToSaveResponse(this.dakikaKullanimService.update(dakikaKullanim));
    } else {
      this.subscribeToSaveResponse(this.dakikaKullanimService.create(dakikaKullanim));
    }
  }

  private createFromForm(): IDakikaKullanim {
    return {
      ...new DakikaKullanim(),
      id: this.editForm.get(['id'])!.value,
      tarih: this.editForm.get(['tarih'])!.value ? moment(this.editForm.get(['tarih'])!.value, DATE_TIME_FORMAT) : undefined,
      miktar: this.editForm.get(['miktar'])!.value,
      sozlesmeninPaketleri: this.editForm.get(['sozlesmeninPaketleri'])!.value,
      telNo: this.editForm.get(['telNo'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDakikaKullanim>>): void {
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

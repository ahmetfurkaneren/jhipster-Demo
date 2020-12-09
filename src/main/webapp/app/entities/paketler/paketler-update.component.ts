import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IPaketler, Paketler } from 'app/shared/model/paketler.model';
import { PaketlerService } from './paketler.service';

@Component({
  selector: 'jhi-paketler-update',
  templateUrl: './paketler-update.component.html',
})
export class PaketlerUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    paketAdi: [null, [Validators.required]],
    aciklama: [],
    baslangicTarihi: [null, [Validators.required]],
    bitisTarihi: [null, [Validators.required]],
    fiyat: [null, [Validators.required]],
    yeniMusteriFiyat: [null, [Validators.required]],
    tahahutSure: [],
    dakika: [],
    sms: [],
    internet: [],
    aktif: [],
    tip: [],
    paketTipi: [],
    donem: [null, [Validators.required]],
    dakikaUcret: [],
    smsUcret: [],
    internetUcret: [],
  });

  constructor(protected paketlerService: PaketlerService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ paketler }) => {
      if (!paketler.id) {
        const today = moment().startOf('day');
        paketler.baslangicTarihi = today;
        paketler.bitisTarihi = today;
      }

      this.updateForm(paketler);
    });
  }

  updateForm(paketler: IPaketler): void {
    this.editForm.patchValue({
      id: paketler.id,
      paketAdi: paketler.paketAdi,
      aciklama: paketler.aciklama,
      baslangicTarihi: paketler.baslangicTarihi ? paketler.baslangicTarihi.format(DATE_TIME_FORMAT) : null,
      bitisTarihi: paketler.bitisTarihi ? paketler.bitisTarihi.format(DATE_TIME_FORMAT) : null,
      fiyat: paketler.fiyat,
      yeniMusteriFiyat: paketler.yeniMusteriFiyat,
      tahahutSure: paketler.tahahutSure,
      dakika: paketler.dakika,
      sms: paketler.sms,
      internet: paketler.internet,
      aktif: paketler.aktif,
      tip: paketler.tip,
      paketTipi: paketler.paketTipi,
      donem: paketler.donem,
      dakikaUcret: paketler.dakikaUcret,
      smsUcret: paketler.smsUcret,
      internetUcret: paketler.internetUcret,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const paketler = this.createFromForm();
    if (paketler.id !== undefined) {
      this.subscribeToSaveResponse(this.paketlerService.update(paketler));
    } else {
      this.subscribeToSaveResponse(this.paketlerService.create(paketler));
    }
  }

  private createFromForm(): IPaketler {
    return {
      ...new Paketler(),
      id: this.editForm.get(['id'])!.value,
      paketAdi: this.editForm.get(['paketAdi'])!.value,
      aciklama: this.editForm.get(['aciklama'])!.value,
      baslangicTarihi: this.editForm.get(['baslangicTarihi'])!.value
        ? moment(this.editForm.get(['baslangicTarihi'])!.value, DATE_TIME_FORMAT)
        : undefined,
      bitisTarihi: this.editForm.get(['bitisTarihi'])!.value
        ? moment(this.editForm.get(['bitisTarihi'])!.value, DATE_TIME_FORMAT)
        : undefined,
      fiyat: this.editForm.get(['fiyat'])!.value,
      yeniMusteriFiyat: this.editForm.get(['yeniMusteriFiyat'])!.value,
      tahahutSure: this.editForm.get(['tahahutSure'])!.value,
      dakika: this.editForm.get(['dakika'])!.value,
      sms: this.editForm.get(['sms'])!.value,
      internet: this.editForm.get(['internet'])!.value,
      aktif: this.editForm.get(['aktif'])!.value,
      tip: this.editForm.get(['tip'])!.value,
      paketTipi: this.editForm.get(['paketTipi'])!.value,
      donem: this.editForm.get(['donem'])!.value,
      dakikaUcret: this.editForm.get(['dakikaUcret'])!.value,
      smsUcret: this.editForm.get(['smsUcret'])!.value,
      internetUcret: this.editForm.get(['internetUcret'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPaketler>>): void {
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
}

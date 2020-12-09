import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IMusteri, Musteri } from 'app/shared/model/musteri.model';
import { MusteriService } from './musteri.service';

@Component({
  selector: 'jhi-musteri-update',
  templateUrl: './musteri-update.component.html',
})
export class MusteriUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    ad: [null, [Validators.required]],
    soyad: [null, [Validators.required]],
    email: [null, [Validators.required]],
    parola: [null, [Validators.required]],
    tC: [null, [Validators.required, Validators.minLength(11), Validators.maxLength(11)]],
    musteriTipi: [],
    dogumTarihi: [null, [Validators.required]],
  });

  constructor(protected musteriService: MusteriService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ musteri }) => {
      if (!musteri.id) {
        const today = moment().startOf('day');
        musteri.dogumTarihi = today;
      }

      this.updateForm(musteri);
    });
  }

  updateForm(musteri: IMusteri): void {
    this.editForm.patchValue({
      id: musteri.id,
      ad: musteri.ad,
      soyad: musteri.soyad,
      email: musteri.email,
      parola: musteri.parola,
      tC: musteri.tC,
      musteriTipi: musteri.musteriTipi,
      dogumTarihi: musteri.dogumTarihi ? musteri.dogumTarihi.format(DATE_TIME_FORMAT) : null,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const musteri = this.createFromForm();
    if (musteri.id !== undefined) {
      this.subscribeToSaveResponse(this.musteriService.update(musteri));
    } else {
      this.subscribeToSaveResponse(this.musteriService.create(musteri));
    }
  }

  private createFromForm(): IMusteri {
    return {
      ...new Musteri(),
      id: this.editForm.get(['id'])!.value,
      ad: this.editForm.get(['ad'])!.value,
      soyad: this.editForm.get(['soyad'])!.value,
      email: this.editForm.get(['email'])!.value,
      parola: this.editForm.get(['parola'])!.value,
      tC: this.editForm.get(['tC'])!.value,
      musteriTipi: this.editForm.get(['musteriTipi'])!.value,
      dogumTarihi: this.editForm.get(['dogumTarihi'])!.value
        ? moment(this.editForm.get(['dogumTarihi'])!.value, DATE_TIME_FORMAT)
        : undefined,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMusteri>>): void {
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

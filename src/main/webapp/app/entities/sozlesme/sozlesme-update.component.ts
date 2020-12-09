import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISozlesme, Sozlesme } from 'app/shared/model/sozlesme.model';
import { SozlesmeService } from './sozlesme.service';
import { IMusteri } from 'app/shared/model/musteri.model';
import { MusteriService } from 'app/entities/musteri/musteri.service';
import { ITelNo } from 'app/shared/model/tel-no.model';
import { TelNoService } from 'app/entities/tel-no/tel-no.service';

type SelectableEntity = IMusteri | ITelNo;

@Component({
  selector: 'jhi-sozlesme-update',
  templateUrl: './sozlesme-update.component.html',
})
export class SozlesmeUpdateComponent implements OnInit {
  isSaving = false;
  musteris: IMusteri[] = [];
  telnos: ITelNo[] = [];

  editForm = this.fb.group({
    id: [],
    tip: [],
    tarih: [null, [Validators.required]],
    bitisTarihi: [],
    musteri: [null, Validators.required],
    telNo: [null, Validators.required],
  });

  constructor(
    protected sozlesmeService: SozlesmeService,
    protected musteriService: MusteriService,
    protected telNoService: TelNoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sozlesme }) => {
      if (!sozlesme.id) {
        const today = moment().startOf('day');
        sozlesme.tarih = today;
        sozlesme.bitisTarihi = today;
      }

      this.updateForm(sozlesme);

      this.musteriService.query().subscribe((res: HttpResponse<IMusteri[]>) => (this.musteris = res.body || []));

      this.telNoService.query().subscribe((res: HttpResponse<ITelNo[]>) => (this.telnos = res.body || []));
    });
  }

  updateForm(sozlesme: ISozlesme): void {
    this.editForm.patchValue({
      id: sozlesme.id,
      tip: sozlesme.tip,
      tarih: sozlesme.tarih ? sozlesme.tarih.format(DATE_TIME_FORMAT) : null,
      bitisTarihi: sozlesme.bitisTarihi ? sozlesme.bitisTarihi.format(DATE_TIME_FORMAT) : null,
      musteri: sozlesme.musteri,
      telNo: sozlesme.telNo,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sozlesme = this.createFromForm();
    if (sozlesme.id !== undefined) {
      this.subscribeToSaveResponse(this.sozlesmeService.update(sozlesme));
    } else {
      this.subscribeToSaveResponse(this.sozlesmeService.create(sozlesme));
    }
  }

  private createFromForm(): ISozlesme {
    return {
      ...new Sozlesme(),
      id: this.editForm.get(['id'])!.value,
      tip: this.editForm.get(['tip'])!.value,
      tarih: this.editForm.get(['tarih'])!.value ? moment(this.editForm.get(['tarih'])!.value, DATE_TIME_FORMAT) : undefined,
      bitisTarihi: this.editForm.get(['bitisTarihi'])!.value
        ? moment(this.editForm.get(['bitisTarihi'])!.value, DATE_TIME_FORMAT)
        : undefined,
      musteri: this.editForm.get(['musteri'])!.value,
      telNo: this.editForm.get(['telNo'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISozlesme>>): void {
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

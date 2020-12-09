import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IFatura, Fatura } from 'app/shared/model/fatura.model';
import { FaturaService } from './fatura.service';
import { ISozlesme } from 'app/shared/model/sozlesme.model';
import { SozlesmeService } from 'app/entities/sozlesme/sozlesme.service';

@Component({
  selector: 'jhi-fatura-update',
  templateUrl: './fatura-update.component.html',
})
export class FaturaUpdateComponent implements OnInit {
  isSaving = false;
  sozlesmes: ISozlesme[] = [];

  editForm = this.fb.group({
    id: [],
    ilkOdemeTarihi: [null, [Validators.required]],
    sonOdemeTarihi: [null, [Validators.required]],
    odenenTarih: [],
    toplamTutar: [null, [Validators.required]],
    sozlesme: [null, Validators.required],
  });

  constructor(
    protected faturaService: FaturaService,
    protected sozlesmeService: SozlesmeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fatura }) => {
      if (!fatura.id) {
        const today = moment().startOf('day');
        fatura.ilkOdemeTarihi = today;
        fatura.sonOdemeTarihi = today;
        fatura.odenenTarih = today;
      }

      this.updateForm(fatura);

      this.sozlesmeService.query().subscribe((res: HttpResponse<ISozlesme[]>) => (this.sozlesmes = res.body || []));
    });
  }

  updateForm(fatura: IFatura): void {
    this.editForm.patchValue({
      id: fatura.id,
      ilkOdemeTarihi: fatura.ilkOdemeTarihi ? fatura.ilkOdemeTarihi.format(DATE_TIME_FORMAT) : null,
      sonOdemeTarihi: fatura.sonOdemeTarihi ? fatura.sonOdemeTarihi.format(DATE_TIME_FORMAT) : null,
      odenenTarih: fatura.odenenTarih ? fatura.odenenTarih.format(DATE_TIME_FORMAT) : null,
      toplamTutar: fatura.toplamTutar,
      sozlesme: fatura.sozlesme,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const fatura = this.createFromForm();
    if (fatura.id !== undefined) {
      this.subscribeToSaveResponse(this.faturaService.update(fatura));
    } else {
      this.subscribeToSaveResponse(this.faturaService.create(fatura));
    }
  }

  private createFromForm(): IFatura {
    return {
      ...new Fatura(),
      id: this.editForm.get(['id'])!.value,
      ilkOdemeTarihi: this.editForm.get(['ilkOdemeTarihi'])!.value
        ? moment(this.editForm.get(['ilkOdemeTarihi'])!.value, DATE_TIME_FORMAT)
        : undefined,
      sonOdemeTarihi: this.editForm.get(['sonOdemeTarihi'])!.value
        ? moment(this.editForm.get(['sonOdemeTarihi'])!.value, DATE_TIME_FORMAT)
        : undefined,
      odenenTarih: this.editForm.get(['odenenTarih'])!.value
        ? moment(this.editForm.get(['odenenTarih'])!.value, DATE_TIME_FORMAT)
        : undefined,
      toplamTutar: this.editForm.get(['toplamTutar'])!.value,
      sozlesme: this.editForm.get(['sozlesme'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFatura>>): void {
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

  trackById(index: number, item: ISozlesme): any {
    return item.id;
  }
}

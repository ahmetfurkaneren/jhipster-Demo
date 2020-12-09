import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { ISozlesmeninPaketleri, SozlesmeninPaketleri } from 'app/shared/model/sozlesmenin-paketleri.model';
import { SozlesmeninPaketleriService } from './sozlesmenin-paketleri.service';
import { ISozlesme } from 'app/shared/model/sozlesme.model';
import { SozlesmeService } from 'app/entities/sozlesme/sozlesme.service';
import { IPaketler } from 'app/shared/model/paketler.model';
import { PaketlerService } from 'app/entities/paketler/paketler.service';

type SelectableEntity = ISozlesme | IPaketler;

@Component({
  selector: 'jhi-sozlesmenin-paketleri-update',
  templateUrl: './sozlesmenin-paketleri-update.component.html',
})
export class SozlesmeninPaketleriUpdateComponent implements OnInit {
  isSaving = false;
  sozlesmes: ISozlesme[] = [];
  paketlers: IPaketler[] = [];

  editForm = this.fb.group({
    id: [],
    fiyat: [null, [Validators.required]],
    baslangicTarihi: [null, [Validators.required]],
    bitisTarihi: [null, [Validators.required]],
    kalanDakika: [null, [Validators.required]],
    kalanSms: [null, [Validators.required]],
    kalanInternet: [null, [Validators.required]],
    sozlesme: [null, Validators.required],
    paketler: [null, Validators.required],
  });

  constructor(
    protected sozlesmeninPaketleriService: SozlesmeninPaketleriService,
    protected sozlesmeService: SozlesmeService,
    protected paketlerService: PaketlerService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sozlesmeninPaketleri }) => {
      if (!sozlesmeninPaketleri.id) {
        const today = moment().startOf('day');
        sozlesmeninPaketleri.baslangicTarihi = today;
        sozlesmeninPaketleri.bitisTarihi = today;
      }

      this.updateForm(sozlesmeninPaketleri);

      this.sozlesmeService.query().subscribe((res: HttpResponse<ISozlesme[]>) => (this.sozlesmes = res.body || []));

      this.paketlerService.query().subscribe((res: HttpResponse<IPaketler[]>) => (this.paketlers = res.body || []));
    });
  }

  updateForm(sozlesmeninPaketleri: ISozlesmeninPaketleri): void {
    this.editForm.patchValue({
      id: sozlesmeninPaketleri.id,
      fiyat: sozlesmeninPaketleri.fiyat,
      baslangicTarihi: sozlesmeninPaketleri.baslangicTarihi ? sozlesmeninPaketleri.baslangicTarihi.format(DATE_TIME_FORMAT) : null,
      bitisTarihi: sozlesmeninPaketleri.bitisTarihi ? sozlesmeninPaketleri.bitisTarihi.format(DATE_TIME_FORMAT) : null,
      kalanDakika: sozlesmeninPaketleri.kalanDakika,
      kalanSms: sozlesmeninPaketleri.kalanSms,
      kalanInternet: sozlesmeninPaketleri.kalanInternet,
      sozlesme: sozlesmeninPaketleri.sozlesme,
      paketler: sozlesmeninPaketleri.paketler,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sozlesmeninPaketleri = this.createFromForm();
    if (sozlesmeninPaketleri.id !== undefined) {
      this.subscribeToSaveResponse(this.sozlesmeninPaketleriService.update(sozlesmeninPaketleri));
    } else {
      this.subscribeToSaveResponse(this.sozlesmeninPaketleriService.create(sozlesmeninPaketleri));
    }
  }

  private createFromForm(): ISozlesmeninPaketleri {
    return {
      ...new SozlesmeninPaketleri(),
      id: this.editForm.get(['id'])!.value,
      fiyat: this.editForm.get(['fiyat'])!.value,
      baslangicTarihi: this.editForm.get(['baslangicTarihi'])!.value
        ? moment(this.editForm.get(['baslangicTarihi'])!.value, DATE_TIME_FORMAT)
        : undefined,
      bitisTarihi: this.editForm.get(['bitisTarihi'])!.value
        ? moment(this.editForm.get(['bitisTarihi'])!.value, DATE_TIME_FORMAT)
        : undefined,
      kalanDakika: this.editForm.get(['kalanDakika'])!.value,
      kalanSms: this.editForm.get(['kalanSms'])!.value,
      kalanInternet: this.editForm.get(['kalanInternet'])!.value,
      sozlesme: this.editForm.get(['sozlesme'])!.value,
      paketler: this.editForm.get(['paketler'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISozlesmeninPaketleri>>): void {
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

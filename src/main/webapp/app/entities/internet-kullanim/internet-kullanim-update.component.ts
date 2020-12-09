import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IInternetKullanim, InternetKullanim } from 'app/shared/model/internet-kullanim.model';
import { InternetKullanimService } from './internet-kullanim.service';
import { ISozlesmeninPaketleri } from 'app/shared/model/sozlesmenin-paketleri.model';
import { SozlesmeninPaketleriService } from 'app/entities/sozlesmenin-paketleri/sozlesmenin-paketleri.service';

@Component({
  selector: 'jhi-internet-kullanim-update',
  templateUrl: './internet-kullanim-update.component.html',
})
export class InternetKullanimUpdateComponent implements OnInit {
  isSaving = false;
  sozlesmeninpaketleris: ISozlesmeninPaketleri[] = [];

  editForm = this.fb.group({
    id: [],
    tarih: [null, [Validators.required]],
    miktar: [null, [Validators.required]],
    sozlesmeninPaketleri: [null, Validators.required],
  });

  constructor(
    protected internetKullanimService: InternetKullanimService,
    protected sozlesmeninPaketleriService: SozlesmeninPaketleriService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ internetKullanim }) => {
      if (!internetKullanim.id) {
        const today = moment().startOf('day');
        internetKullanim.tarih = today;
      }

      this.updateForm(internetKullanim);

      this.sozlesmeninPaketleriService
        .query()
        .subscribe((res: HttpResponse<ISozlesmeninPaketleri[]>) => (this.sozlesmeninpaketleris = res.body || []));
    });
  }

  updateForm(internetKullanim: IInternetKullanim): void {
    this.editForm.patchValue({
      id: internetKullanim.id,
      tarih: internetKullanim.tarih ? internetKullanim.tarih.format(DATE_TIME_FORMAT) : null,
      miktar: internetKullanim.miktar,
      sozlesmeninPaketleri: internetKullanim.sozlesmeninPaketleri,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const internetKullanim = this.createFromForm();
    if (internetKullanim.id !== undefined) {
      this.subscribeToSaveResponse(this.internetKullanimService.update(internetKullanim));
    } else {
      this.subscribeToSaveResponse(this.internetKullanimService.create(internetKullanim));
    }
  }

  private createFromForm(): IInternetKullanim {
    return {
      ...new InternetKullanim(),
      id: this.editForm.get(['id'])!.value,
      tarih: this.editForm.get(['tarih'])!.value ? moment(this.editForm.get(['tarih'])!.value, DATE_TIME_FORMAT) : undefined,
      miktar: this.editForm.get(['miktar'])!.value,
      sozlesmeninPaketleri: this.editForm.get(['sozlesmeninPaketleri'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IInternetKullanim>>): void {
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

  trackById(index: number, item: ISozlesmeninPaketleri): any {
    return item.id;
  }
}

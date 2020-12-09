import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ISirketBilgileri, SirketBilgileri } from 'app/shared/model/sirket-bilgileri.model';
import { SirketBilgileriService } from './sirket-bilgileri.service';
import { IMusteri } from 'app/shared/model/musteri.model';
import { MusteriService } from 'app/entities/musteri/musteri.service';

@Component({
  selector: 'jhi-sirket-bilgileri-update',
  templateUrl: './sirket-bilgileri-update.component.html',
})
export class SirketBilgileriUpdateComponent implements OnInit {
  isSaving = false;
  musteris: IMusteri[] = [];

  editForm = this.fb.group({
    id: [],
    sirketAdi: [null, [Validators.required]],
    sirketAdresi: [null, [Validators.required]],
    musteri: [null, Validators.required],
  });

  constructor(
    protected sirketBilgileriService: SirketBilgileriService,
    protected musteriService: MusteriService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sirketBilgileri }) => {
      this.updateForm(sirketBilgileri);

      this.musteriService
        .query({ filter: 'sirketbilgileri-is-null' })
        .pipe(
          map((res: HttpResponse<IMusteri[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IMusteri[]) => {
          if (!sirketBilgileri.musteri || !sirketBilgileri.musteri.id) {
            this.musteris = resBody;
          } else {
            this.musteriService
              .find(sirketBilgileri.musteri.id)
              .pipe(
                map((subRes: HttpResponse<IMusteri>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IMusteri[]) => (this.musteris = concatRes));
          }
        });
    });
  }

  updateForm(sirketBilgileri: ISirketBilgileri): void {
    this.editForm.patchValue({
      id: sirketBilgileri.id,
      sirketAdi: sirketBilgileri.sirketAdi,
      sirketAdresi: sirketBilgileri.sirketAdresi,
      musteri: sirketBilgileri.musteri,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sirketBilgileri = this.createFromForm();
    if (sirketBilgileri.id !== undefined) {
      this.subscribeToSaveResponse(this.sirketBilgileriService.update(sirketBilgileri));
    } else {
      this.subscribeToSaveResponse(this.sirketBilgileriService.create(sirketBilgileri));
    }
  }

  private createFromForm(): ISirketBilgileri {
    return {
      ...new SirketBilgileri(),
      id: this.editForm.get(['id'])!.value,
      sirketAdi: this.editForm.get(['sirketAdi'])!.value,
      sirketAdresi: this.editForm.get(['sirketAdresi'])!.value,
      musteri: this.editForm.get(['musteri'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISirketBilgileri>>): void {
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

  trackById(index: number, item: IMusteri): any {
    return item.id;
  }
}

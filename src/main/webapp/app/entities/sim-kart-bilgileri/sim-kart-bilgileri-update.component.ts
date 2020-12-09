import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ISimKartBilgileri, SimKartBilgileri } from 'app/shared/model/sim-kart-bilgileri.model';
import { SimKartBilgileriService } from './sim-kart-bilgileri.service';
import { ISozlesme } from 'app/shared/model/sozlesme.model';
import { SozlesmeService } from 'app/entities/sozlesme/sozlesme.service';

@Component({
  selector: 'jhi-sim-kart-bilgileri-update',
  templateUrl: './sim-kart-bilgileri-update.component.html',
})
export class SimKartBilgileriUpdateComponent implements OnInit {
  isSaving = false;
  sozlesmes: ISozlesme[] = [];

  editForm = this.fb.group({
    id: [],
    pinNo: [null, [Validators.required, Validators.max(4)]],
    pukNo: [null, [Validators.required, Validators.max(8)]],
    barkod: [null, [Validators.required]],
    bitMiktari: [null, [Validators.required]],
    sozlesme: [null, Validators.required],
  });

  constructor(
    protected simKartBilgileriService: SimKartBilgileriService,
    protected sozlesmeService: SozlesmeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ simKartBilgileri }) => {
      this.updateForm(simKartBilgileri);

      this.sozlesmeService
        .query({ filter: 'simkartbilgileri-is-null' })
        .pipe(
          map((res: HttpResponse<ISozlesme[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ISozlesme[]) => {
          if (!simKartBilgileri.sozlesme || !simKartBilgileri.sozlesme.id) {
            this.sozlesmes = resBody;
          } else {
            this.sozlesmeService
              .find(simKartBilgileri.sozlesme.id)
              .pipe(
                map((subRes: HttpResponse<ISozlesme>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ISozlesme[]) => (this.sozlesmes = concatRes));
          }
        });
    });
  }

  updateForm(simKartBilgileri: ISimKartBilgileri): void {
    this.editForm.patchValue({
      id: simKartBilgileri.id,
      pinNo: simKartBilgileri.pinNo,
      pukNo: simKartBilgileri.pukNo,
      barkod: simKartBilgileri.barkod,
      bitMiktari: simKartBilgileri.bitMiktari,
      sozlesme: simKartBilgileri.sozlesme,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const simKartBilgileri = this.createFromForm();
    if (simKartBilgileri.id !== undefined) {
      this.subscribeToSaveResponse(this.simKartBilgileriService.update(simKartBilgileri));
    } else {
      this.subscribeToSaveResponse(this.simKartBilgileriService.create(simKartBilgileri));
    }
  }

  private createFromForm(): ISimKartBilgileri {
    return {
      ...new SimKartBilgileri(),
      id: this.editForm.get(['id'])!.value,
      pinNo: this.editForm.get(['pinNo'])!.value,
      pukNo: this.editForm.get(['pukNo'])!.value,
      barkod: this.editForm.get(['barkod'])!.value,
      bitMiktari: this.editForm.get(['bitMiktari'])!.value,
      sozlesme: this.editForm.get(['sozlesme'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISimKartBilgileri>>): void {
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

import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITelNo, TelNo } from 'app/shared/model/tel-no.model';
import { TelNoService } from './tel-no.service';

@Component({
  selector: 'jhi-tel-no-update',
  templateUrl: './tel-no-update.component.html',
})
export class TelNoUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    numara: [null, [Validators.required]],
  });

  constructor(protected telNoService: TelNoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ telNo }) => {
      this.updateForm(telNo);
    });
  }

  updateForm(telNo: ITelNo): void {
    this.editForm.patchValue({
      id: telNo.id,
      numara: telNo.numara,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const telNo = this.createFromForm();
    if (telNo.id !== undefined) {
      this.subscribeToSaveResponse(this.telNoService.update(telNo));
    } else {
      this.subscribeToSaveResponse(this.telNoService.create(telNo));
    }
  }

  private createFromForm(): ITelNo {
    return {
      ...new TelNo(),
      id: this.editForm.get(['id'])!.value,
      numara: this.editForm.get(['numara'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITelNo>>): void {
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

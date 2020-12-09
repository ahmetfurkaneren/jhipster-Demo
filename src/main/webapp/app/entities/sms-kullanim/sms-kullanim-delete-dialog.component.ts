import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISmsKullanim } from 'app/shared/model/sms-kullanim.model';
import { SmsKullanimService } from './sms-kullanim.service';

@Component({
  templateUrl: './sms-kullanim-delete-dialog.component.html',
})
export class SmsKullanimDeleteDialogComponent {
  smsKullanim?: ISmsKullanim;

  constructor(
    protected smsKullanimService: SmsKullanimService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.smsKullanimService.delete(id).subscribe(() => {
      this.eventManager.broadcast('smsKullanimListModification');
      this.activeModal.close();
    });
  }
}

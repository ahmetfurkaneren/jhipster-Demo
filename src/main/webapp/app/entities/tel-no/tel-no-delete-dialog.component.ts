import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITelNo } from 'app/shared/model/tel-no.model';
import { TelNoService } from './tel-no.service';

@Component({
  templateUrl: './tel-no-delete-dialog.component.html',
})
export class TelNoDeleteDialogComponent {
  telNo?: ITelNo;

  constructor(protected telNoService: TelNoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.telNoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('telNoListModification');
      this.activeModal.close();
    });
  }
}

import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISozlesme } from 'app/shared/model/sozlesme.model';
import { SozlesmeService } from './sozlesme.service';

@Component({
  templateUrl: './sozlesme-delete-dialog.component.html',
})
export class SozlesmeDeleteDialogComponent {
  sozlesme?: ISozlesme;

  constructor(protected sozlesmeService: SozlesmeService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.sozlesmeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('sozlesmeListModification');
      this.activeModal.close();
    });
  }
}

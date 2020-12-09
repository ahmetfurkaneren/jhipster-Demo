import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPaketler } from 'app/shared/model/paketler.model';
import { PaketlerService } from './paketler.service';

@Component({
  templateUrl: './paketler-delete-dialog.component.html',
})
export class PaketlerDeleteDialogComponent {
  paketler?: IPaketler;

  constructor(protected paketlerService: PaketlerService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.paketlerService.delete(id).subscribe(() => {
      this.eventManager.broadcast('paketlerListModification');
      this.activeModal.close();
    });
  }
}

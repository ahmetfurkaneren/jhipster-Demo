import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMusteri } from 'app/shared/model/musteri.model';
import { MusteriService } from './musteri.service';

@Component({
  templateUrl: './musteri-delete-dialog.component.html',
})
export class MusteriDeleteDialogComponent {
  musteri?: IMusteri;

  constructor(protected musteriService: MusteriService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.musteriService.delete(id).subscribe(() => {
      this.eventManager.broadcast('musteriListModification');
      this.activeModal.close();
    });
  }
}

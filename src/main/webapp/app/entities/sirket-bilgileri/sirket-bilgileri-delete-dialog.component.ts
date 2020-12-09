import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISirketBilgileri } from 'app/shared/model/sirket-bilgileri.model';
import { SirketBilgileriService } from './sirket-bilgileri.service';

@Component({
  templateUrl: './sirket-bilgileri-delete-dialog.component.html',
})
export class SirketBilgileriDeleteDialogComponent {
  sirketBilgileri?: ISirketBilgileri;

  constructor(
    protected sirketBilgileriService: SirketBilgileriService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.sirketBilgileriService.delete(id).subscribe(() => {
      this.eventManager.broadcast('sirketBilgileriListModification');
      this.activeModal.close();
    });
  }
}

import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISimKartBilgileri } from 'app/shared/model/sim-kart-bilgileri.model';
import { SimKartBilgileriService } from './sim-kart-bilgileri.service';

@Component({
  templateUrl: './sim-kart-bilgileri-delete-dialog.component.html',
})
export class SimKartBilgileriDeleteDialogComponent {
  simKartBilgileri?: ISimKartBilgileri;

  constructor(
    protected simKartBilgileriService: SimKartBilgileriService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.simKartBilgileriService.delete(id).subscribe(() => {
      this.eventManager.broadcast('simKartBilgileriListModification');
      this.activeModal.close();
    });
  }
}

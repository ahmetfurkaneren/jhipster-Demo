import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDakikaKullanim } from 'app/shared/model/dakika-kullanim.model';
import { DakikaKullanimService } from './dakika-kullanim.service';

@Component({
  templateUrl: './dakika-kullanim-delete-dialog.component.html',
})
export class DakikaKullanimDeleteDialogComponent {
  dakikaKullanim?: IDakikaKullanim;

  constructor(
    protected dakikaKullanimService: DakikaKullanimService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.dakikaKullanimService.delete(id).subscribe(() => {
      this.eventManager.broadcast('dakikaKullanimListModification');
      this.activeModal.close();
    });
  }
}

import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISozlesmeninPaketleri } from 'app/shared/model/sozlesmenin-paketleri.model';
import { SozlesmeninPaketleriService } from './sozlesmenin-paketleri.service';

@Component({
  templateUrl: './sozlesmenin-paketleri-delete-dialog.component.html',
})
export class SozlesmeninPaketleriDeleteDialogComponent {
  sozlesmeninPaketleri?: ISozlesmeninPaketleri;

  constructor(
    protected sozlesmeninPaketleriService: SozlesmeninPaketleriService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.sozlesmeninPaketleriService.delete(id).subscribe(() => {
      this.eventManager.broadcast('sozlesmeninPaketleriListModification');
      this.activeModal.close();
    });
  }
}

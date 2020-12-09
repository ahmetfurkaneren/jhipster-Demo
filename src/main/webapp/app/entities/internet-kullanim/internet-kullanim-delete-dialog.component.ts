import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IInternetKullanim } from 'app/shared/model/internet-kullanim.model';
import { InternetKullanimService } from './internet-kullanim.service';

@Component({
  templateUrl: './internet-kullanim-delete-dialog.component.html',
})
export class InternetKullanimDeleteDialogComponent {
  internetKullanim?: IInternetKullanim;

  constructor(
    protected internetKullanimService: InternetKullanimService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.internetKullanimService.delete(id).subscribe(() => {
      this.eventManager.broadcast('internetKullanimListModification');
      this.activeModal.close();
    });
  }
}

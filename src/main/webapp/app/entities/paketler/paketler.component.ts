import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPaketler } from 'app/shared/model/paketler.model';
import { PaketlerService } from './paketler.service';
import { PaketlerDeleteDialogComponent } from './paketler-delete-dialog.component';

@Component({
  selector: 'jhi-paketler',
  templateUrl: './paketler.component.html',
})
export class PaketlerComponent implements OnInit, OnDestroy {
  paketlers?: IPaketler[];
  eventSubscriber?: Subscription;

  constructor(protected paketlerService: PaketlerService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.paketlerService.query().subscribe((res: HttpResponse<IPaketler[]>) => (this.paketlers = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPaketlers();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPaketler): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPaketlers(): void {
    this.eventSubscriber = this.eventManager.subscribe('paketlerListModification', () => this.loadAll());
  }

  delete(paketler: IPaketler): void {
    const modalRef = this.modalService.open(PaketlerDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.paketler = paketler;
  }
}

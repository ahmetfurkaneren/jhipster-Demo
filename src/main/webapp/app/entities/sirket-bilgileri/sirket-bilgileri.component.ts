import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISirketBilgileri } from 'app/shared/model/sirket-bilgileri.model';
import { SirketBilgileriService } from './sirket-bilgileri.service';
import { SirketBilgileriDeleteDialogComponent } from './sirket-bilgileri-delete-dialog.component';

@Component({
  selector: 'jhi-sirket-bilgileri',
  templateUrl: './sirket-bilgileri.component.html',
})
export class SirketBilgileriComponent implements OnInit, OnDestroy {
  sirketBilgileris?: ISirketBilgileri[];
  eventSubscriber?: Subscription;

  constructor(
    protected sirketBilgileriService: SirketBilgileriService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.sirketBilgileriService.query().subscribe((res: HttpResponse<ISirketBilgileri[]>) => (this.sirketBilgileris = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSirketBilgileris();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISirketBilgileri): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSirketBilgileris(): void {
    this.eventSubscriber = this.eventManager.subscribe('sirketBilgileriListModification', () => this.loadAll());
  }

  delete(sirketBilgileri: ISirketBilgileri): void {
    const modalRef = this.modalService.open(SirketBilgileriDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.sirketBilgileri = sirketBilgileri;
  }
}

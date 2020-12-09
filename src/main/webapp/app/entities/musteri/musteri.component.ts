import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMusteri } from 'app/shared/model/musteri.model';
import { MusteriService } from './musteri.service';
import { MusteriDeleteDialogComponent } from './musteri-delete-dialog.component';

@Component({
  selector: 'jhi-musteri',
  templateUrl: './musteri.component.html',
})
export class MusteriComponent implements OnInit, OnDestroy {
  musteris?: IMusteri[];
  eventSubscriber?: Subscription;

  constructor(protected musteriService: MusteriService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.musteriService.query().subscribe((res: HttpResponse<IMusteri[]>) => (this.musteris = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInMusteris();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMusteri): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInMusteris(): void {
    this.eventSubscriber = this.eventManager.subscribe('musteriListModification', () => this.loadAll());
  }

  delete(musteri: IMusteri): void {
    const modalRef = this.modalService.open(MusteriDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.musteri = musteri;
  }
}

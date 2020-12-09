import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISozlesmeninPaketleri } from 'app/shared/model/sozlesmenin-paketleri.model';
import { SozlesmeninPaketleriService } from './sozlesmenin-paketleri.service';
import { SozlesmeninPaketleriDeleteDialogComponent } from './sozlesmenin-paketleri-delete-dialog.component';

@Component({
  selector: 'jhi-sozlesmenin-paketleri',
  templateUrl: './sozlesmenin-paketleri.component.html',
})
export class SozlesmeninPaketleriComponent implements OnInit, OnDestroy {
  sozlesmeninPaketleris?: ISozlesmeninPaketleri[];
  eventSubscriber?: Subscription;

  constructor(
    protected sozlesmeninPaketleriService: SozlesmeninPaketleriService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.sozlesmeninPaketleriService
      .query()
      .subscribe((res: HttpResponse<ISozlesmeninPaketleri[]>) => (this.sozlesmeninPaketleris = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSozlesmeninPaketleris();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISozlesmeninPaketleri): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSozlesmeninPaketleris(): void {
    this.eventSubscriber = this.eventManager.subscribe('sozlesmeninPaketleriListModification', () => this.loadAll());
  }

  delete(sozlesmeninPaketleri: ISozlesmeninPaketleri): void {
    const modalRef = this.modalService.open(SozlesmeninPaketleriDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.sozlesmeninPaketleri = sozlesmeninPaketleri;
  }
}

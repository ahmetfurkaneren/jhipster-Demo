import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISozlesme } from 'app/shared/model/sozlesme.model';
import { SozlesmeService } from './sozlesme.service';
import { SozlesmeDeleteDialogComponent } from './sozlesme-delete-dialog.component';

@Component({
  selector: 'jhi-sozlesme',
  templateUrl: './sozlesme.component.html',
})
export class SozlesmeComponent implements OnInit, OnDestroy {
  sozlesmes?: ISozlesme[];
  eventSubscriber?: Subscription;

  constructor(protected sozlesmeService: SozlesmeService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.sozlesmeService.query().subscribe((res: HttpResponse<ISozlesme[]>) => (this.sozlesmes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSozlesmes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISozlesme): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSozlesmes(): void {
    this.eventSubscriber = this.eventManager.subscribe('sozlesmeListModification', () => this.loadAll());
  }

  delete(sozlesme: ISozlesme): void {
    const modalRef = this.modalService.open(SozlesmeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.sozlesme = sozlesme;
  }
}

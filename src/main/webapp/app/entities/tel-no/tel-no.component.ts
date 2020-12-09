import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITelNo } from 'app/shared/model/tel-no.model';
import { TelNoService } from './tel-no.service';
import { TelNoDeleteDialogComponent } from './tel-no-delete-dialog.component';

@Component({
  selector: 'jhi-tel-no',
  templateUrl: './tel-no.component.html',
})
export class TelNoComponent implements OnInit, OnDestroy {
  telNos?: ITelNo[];
  eventSubscriber?: Subscription;

  constructor(protected telNoService: TelNoService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.telNoService.query().subscribe((res: HttpResponse<ITelNo[]>) => (this.telNos = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTelNos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITelNo): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTelNos(): void {
    this.eventSubscriber = this.eventManager.subscribe('telNoListModification', () => this.loadAll());
  }

  delete(telNo: ITelNo): void {
    const modalRef = this.modalService.open(TelNoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.telNo = telNo;
  }
}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISmsKullanim } from 'app/shared/model/sms-kullanim.model';
import { SmsKullanimService } from './sms-kullanim.service';
import { SmsKullanimDeleteDialogComponent } from './sms-kullanim-delete-dialog.component';

@Component({
  selector: 'jhi-sms-kullanim',
  templateUrl: './sms-kullanim.component.html',
})
export class SmsKullanimComponent implements OnInit, OnDestroy {
  smsKullanims?: ISmsKullanim[];
  eventSubscriber?: Subscription;

  constructor(
    protected smsKullanimService: SmsKullanimService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.smsKullanimService.query().subscribe((res: HttpResponse<ISmsKullanim[]>) => (this.smsKullanims = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSmsKullanims();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISmsKullanim): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSmsKullanims(): void {
    this.eventSubscriber = this.eventManager.subscribe('smsKullanimListModification', () => this.loadAll());
  }

  delete(smsKullanim: ISmsKullanim): void {
    const modalRef = this.modalService.open(SmsKullanimDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.smsKullanim = smsKullanim;
  }
}

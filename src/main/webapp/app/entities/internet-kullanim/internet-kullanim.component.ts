import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IInternetKullanim } from 'app/shared/model/internet-kullanim.model';
import { InternetKullanimService } from './internet-kullanim.service';
import { InternetKullanimDeleteDialogComponent } from './internet-kullanim-delete-dialog.component';

@Component({
  selector: 'jhi-internet-kullanim',
  templateUrl: './internet-kullanim.component.html',
})
export class InternetKullanimComponent implements OnInit, OnDestroy {
  internetKullanims?: IInternetKullanim[];
  eventSubscriber?: Subscription;

  constructor(
    protected internetKullanimService: InternetKullanimService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.internetKullanimService.query().subscribe((res: HttpResponse<IInternetKullanim[]>) => (this.internetKullanims = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInInternetKullanims();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IInternetKullanim): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInInternetKullanims(): void {
    this.eventSubscriber = this.eventManager.subscribe('internetKullanimListModification', () => this.loadAll());
  }

  delete(internetKullanim: IInternetKullanim): void {
    const modalRef = this.modalService.open(InternetKullanimDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.internetKullanim = internetKullanim;
  }
}

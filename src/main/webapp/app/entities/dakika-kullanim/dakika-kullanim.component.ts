import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDakikaKullanim } from 'app/shared/model/dakika-kullanim.model';
import { DakikaKullanimService } from './dakika-kullanim.service';
import { DakikaKullanimDeleteDialogComponent } from './dakika-kullanim-delete-dialog.component';

@Component({
  selector: 'jhi-dakika-kullanim',
  templateUrl: './dakika-kullanim.component.html',
})
export class DakikaKullanimComponent implements OnInit, OnDestroy {
  dakikaKullanims?: IDakikaKullanim[];
  eventSubscriber?: Subscription;

  constructor(
    protected dakikaKullanimService: DakikaKullanimService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.dakikaKullanimService.query().subscribe((res: HttpResponse<IDakikaKullanim[]>) => (this.dakikaKullanims = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInDakikaKullanims();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IDakikaKullanim): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInDakikaKullanims(): void {
    this.eventSubscriber = this.eventManager.subscribe('dakikaKullanimListModification', () => this.loadAll());
  }

  delete(dakikaKullanim: IDakikaKullanim): void {
    const modalRef = this.modalService.open(DakikaKullanimDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.dakikaKullanim = dakikaKullanim;
  }
}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISimKartBilgileri } from 'app/shared/model/sim-kart-bilgileri.model';
import { SimKartBilgileriService } from './sim-kart-bilgileri.service';
import { SimKartBilgileriDeleteDialogComponent } from './sim-kart-bilgileri-delete-dialog.component';

@Component({
  selector: 'jhi-sim-kart-bilgileri',
  templateUrl: './sim-kart-bilgileri.component.html',
})
export class SimKartBilgileriComponent implements OnInit, OnDestroy {
  simKartBilgileris?: ISimKartBilgileri[];
  eventSubscriber?: Subscription;

  constructor(
    protected simKartBilgileriService: SimKartBilgileriService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.simKartBilgileriService.query().subscribe((res: HttpResponse<ISimKartBilgileri[]>) => (this.simKartBilgileris = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSimKartBilgileris();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISimKartBilgileri): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSimKartBilgileris(): void {
    this.eventSubscriber = this.eventManager.subscribe('simKartBilgileriListModification', () => this.loadAll());
  }

  delete(simKartBilgileri: ISimKartBilgileri): void {
    const modalRef = this.modalService.open(SimKartBilgileriDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.simKartBilgileri = simKartBilgileri;
  }
}

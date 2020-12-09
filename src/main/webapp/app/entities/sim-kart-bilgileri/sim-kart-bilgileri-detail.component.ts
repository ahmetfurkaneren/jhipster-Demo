import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISimKartBilgileri } from 'app/shared/model/sim-kart-bilgileri.model';

@Component({
  selector: 'jhi-sim-kart-bilgileri-detail',
  templateUrl: './sim-kart-bilgileri-detail.component.html',
})
export class SimKartBilgileriDetailComponent implements OnInit {
  simKartBilgileri: ISimKartBilgileri | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ simKartBilgileri }) => (this.simKartBilgileri = simKartBilgileri));
  }

  previousState(): void {
    window.history.back();
  }
}

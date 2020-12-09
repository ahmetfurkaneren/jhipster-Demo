import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPaketler } from 'app/shared/model/paketler.model';

@Component({
  selector: 'jhi-paketler-detail',
  templateUrl: './paketler-detail.component.html',
})
export class PaketlerDetailComponent implements OnInit {
  paketler: IPaketler | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ paketler }) => (this.paketler = paketler));
  }

  previousState(): void {
    window.history.back();
  }
}

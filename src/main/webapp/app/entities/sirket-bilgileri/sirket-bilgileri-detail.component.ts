import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISirketBilgileri } from 'app/shared/model/sirket-bilgileri.model';

@Component({
  selector: 'jhi-sirket-bilgileri-detail',
  templateUrl: './sirket-bilgileri-detail.component.html',
})
export class SirketBilgileriDetailComponent implements OnInit {
  sirketBilgileri: ISirketBilgileri | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sirketBilgileri }) => (this.sirketBilgileri = sirketBilgileri));
  }

  previousState(): void {
    window.history.back();
  }
}

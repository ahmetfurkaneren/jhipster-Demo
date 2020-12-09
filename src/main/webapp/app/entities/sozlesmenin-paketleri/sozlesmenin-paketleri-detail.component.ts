import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISozlesmeninPaketleri } from 'app/shared/model/sozlesmenin-paketleri.model';

@Component({
  selector: 'jhi-sozlesmenin-paketleri-detail',
  templateUrl: './sozlesmenin-paketleri-detail.component.html',
})
export class SozlesmeninPaketleriDetailComponent implements OnInit {
  sozlesmeninPaketleri: ISozlesmeninPaketleri | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sozlesmeninPaketleri }) => (this.sozlesmeninPaketleri = sozlesmeninPaketleri));
  }

  previousState(): void {
    window.history.back();
  }
}

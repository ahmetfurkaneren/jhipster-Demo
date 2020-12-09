import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISozlesme } from 'app/shared/model/sozlesme.model';

@Component({
  selector: 'jhi-sozlesme-detail',
  templateUrl: './sozlesme-detail.component.html',
})
export class SozlesmeDetailComponent implements OnInit {
  sozlesme: ISozlesme | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sozlesme }) => (this.sozlesme = sozlesme));
  }

  previousState(): void {
    window.history.back();
  }
}

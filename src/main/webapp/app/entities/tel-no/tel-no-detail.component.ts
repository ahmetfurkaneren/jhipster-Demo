import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITelNo } from 'app/shared/model/tel-no.model';

@Component({
  selector: 'jhi-tel-no-detail',
  templateUrl: './tel-no-detail.component.html',
})
export class TelNoDetailComponent implements OnInit {
  telNo: ITelNo | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ telNo }) => (this.telNo = telNo));
  }

  previousState(): void {
    window.history.back();
  }
}

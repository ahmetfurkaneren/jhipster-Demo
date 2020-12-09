import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISmsKullanim } from 'app/shared/model/sms-kullanim.model';

@Component({
  selector: 'jhi-sms-kullanim-detail',
  templateUrl: './sms-kullanim-detail.component.html',
})
export class SmsKullanimDetailComponent implements OnInit {
  smsKullanim: ISmsKullanim | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ smsKullanim }) => (this.smsKullanim = smsKullanim));
  }

  previousState(): void {
    window.history.back();
  }
}

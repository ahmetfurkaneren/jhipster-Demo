import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IInternetKullanim } from 'app/shared/model/internet-kullanim.model';

@Component({
  selector: 'jhi-internet-kullanim-detail',
  templateUrl: './internet-kullanim-detail.component.html',
})
export class InternetKullanimDetailComponent implements OnInit {
  internetKullanim: IInternetKullanim | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ internetKullanim }) => (this.internetKullanim = internetKullanim));
  }

  previousState(): void {
    window.history.back();
  }
}

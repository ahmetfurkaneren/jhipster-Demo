import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDakikaKullanim } from 'app/shared/model/dakika-kullanim.model';

@Component({
  selector: 'jhi-dakika-kullanim-detail',
  templateUrl: './dakika-kullanim-detail.component.html',
})
export class DakikaKullanimDetailComponent implements OnInit {
  dakikaKullanim: IDakikaKullanim | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dakikaKullanim }) => (this.dakikaKullanim = dakikaKullanim));
  }

  previousState(): void {
    window.history.back();
  }
}

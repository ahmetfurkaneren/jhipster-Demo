import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMusteri } from 'app/shared/model/musteri.model';

@Component({
  selector: 'jhi-musteri-detail',
  templateUrl: './musteri-detail.component.html',
})
export class MusteriDetailComponent implements OnInit {
  musteri: IMusteri | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ musteri }) => (this.musteri = musteri));
  }

  previousState(): void {
    window.history.back();
  }
}

import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMusteri, Musteri } from 'app/shared/model/musteri.model';
import { MusteriService } from './musteri.service';
import { MusteriComponent } from './musteri.component';
import { MusteriDetailComponent } from './musteri-detail.component';
import { MusteriUpdateComponent } from './musteri-update.component';

@Injectable({ providedIn: 'root' })
export class MusteriResolve implements Resolve<IMusteri> {
  constructor(private service: MusteriService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMusteri> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((musteri: HttpResponse<Musteri>) => {
          if (musteri.body) {
            return of(musteri.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Musteri());
  }
}

export const musteriRoute: Routes = [
  {
    path: '',
    component: MusteriComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.musteri.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MusteriDetailComponent,
    resolve: {
      musteri: MusteriResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.musteri.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MusteriUpdateComponent,
    resolve: {
      musteri: MusteriResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.musteri.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MusteriUpdateComponent,
    resolve: {
      musteri: MusteriResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.musteri.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];

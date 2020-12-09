import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISirketBilgileri, SirketBilgileri } from 'app/shared/model/sirket-bilgileri.model';
import { SirketBilgileriService } from './sirket-bilgileri.service';
import { SirketBilgileriComponent } from './sirket-bilgileri.component';
import { SirketBilgileriDetailComponent } from './sirket-bilgileri-detail.component';
import { SirketBilgileriUpdateComponent } from './sirket-bilgileri-update.component';

@Injectable({ providedIn: 'root' })
export class SirketBilgileriResolve implements Resolve<ISirketBilgileri> {
  constructor(private service: SirketBilgileriService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISirketBilgileri> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((sirketBilgileri: HttpResponse<SirketBilgileri>) => {
          if (sirketBilgileri.body) {
            return of(sirketBilgileri.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SirketBilgileri());
  }
}

export const sirketBilgileriRoute: Routes = [
  {
    path: '',
    component: SirketBilgileriComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.sirketBilgileri.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SirketBilgileriDetailComponent,
    resolve: {
      sirketBilgileri: SirketBilgileriResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.sirketBilgileri.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SirketBilgileriUpdateComponent,
    resolve: {
      sirketBilgileri: SirketBilgileriResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.sirketBilgileri.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SirketBilgileriUpdateComponent,
    resolve: {
      sirketBilgileri: SirketBilgileriResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.sirketBilgileri.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];

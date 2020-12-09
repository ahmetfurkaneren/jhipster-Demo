import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISimKartBilgileri, SimKartBilgileri } from 'app/shared/model/sim-kart-bilgileri.model';
import { SimKartBilgileriService } from './sim-kart-bilgileri.service';
import { SimKartBilgileriComponent } from './sim-kart-bilgileri.component';
import { SimKartBilgileriDetailComponent } from './sim-kart-bilgileri-detail.component';
import { SimKartBilgileriUpdateComponent } from './sim-kart-bilgileri-update.component';

@Injectable({ providedIn: 'root' })
export class SimKartBilgileriResolve implements Resolve<ISimKartBilgileri> {
  constructor(private service: SimKartBilgileriService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISimKartBilgileri> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((simKartBilgileri: HttpResponse<SimKartBilgileri>) => {
          if (simKartBilgileri.body) {
            return of(simKartBilgileri.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SimKartBilgileri());
  }
}

export const simKartBilgileriRoute: Routes = [
  {
    path: '',
    component: SimKartBilgileriComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.simKartBilgileri.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SimKartBilgileriDetailComponent,
    resolve: {
      simKartBilgileri: SimKartBilgileriResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.simKartBilgileri.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SimKartBilgileriUpdateComponent,
    resolve: {
      simKartBilgileri: SimKartBilgileriResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.simKartBilgileri.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SimKartBilgileriUpdateComponent,
    resolve: {
      simKartBilgileri: SimKartBilgileriResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.simKartBilgileri.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];

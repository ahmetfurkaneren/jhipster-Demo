import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITelNo, TelNo } from 'app/shared/model/tel-no.model';
import { TelNoService } from './tel-no.service';
import { TelNoComponent } from './tel-no.component';
import { TelNoDetailComponent } from './tel-no-detail.component';
import { TelNoUpdateComponent } from './tel-no-update.component';

@Injectable({ providedIn: 'root' })
export class TelNoResolve implements Resolve<ITelNo> {
  constructor(private service: TelNoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITelNo> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((telNo: HttpResponse<TelNo>) => {
          if (telNo.body) {
            return of(telNo.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TelNo());
  }
}

export const telNoRoute: Routes = [
  {
    path: '',
    component: TelNoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.telNo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TelNoDetailComponent,
    resolve: {
      telNo: TelNoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.telNo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TelNoUpdateComponent,
    resolve: {
      telNo: TelNoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.telNo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TelNoUpdateComponent,
    resolve: {
      telNo: TelNoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.telNo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];

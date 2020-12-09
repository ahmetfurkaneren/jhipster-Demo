import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISozlesme, Sozlesme } from 'app/shared/model/sozlesme.model';
import { SozlesmeService } from './sozlesme.service';
import { SozlesmeComponent } from './sozlesme.component';
import { SozlesmeDetailComponent } from './sozlesme-detail.component';
import { SozlesmeUpdateComponent } from './sozlesme-update.component';

@Injectable({ providedIn: 'root' })
export class SozlesmeResolve implements Resolve<ISozlesme> {
  constructor(private service: SozlesmeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISozlesme> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((sozlesme: HttpResponse<Sozlesme>) => {
          if (sozlesme.body) {
            return of(sozlesme.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Sozlesme());
  }
}

export const sozlesmeRoute: Routes = [
  {
    path: '',
    component: SozlesmeComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.sozlesme.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SozlesmeDetailComponent,
    resolve: {
      sozlesme: SozlesmeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.sozlesme.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SozlesmeUpdateComponent,
    resolve: {
      sozlesme: SozlesmeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.sozlesme.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SozlesmeUpdateComponent,
    resolve: {
      sozlesme: SozlesmeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.sozlesme.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];

import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISozlesmeninPaketleri, SozlesmeninPaketleri } from 'app/shared/model/sozlesmenin-paketleri.model';
import { SozlesmeninPaketleriService } from './sozlesmenin-paketleri.service';
import { SozlesmeninPaketleriComponent } from './sozlesmenin-paketleri.component';
import { SozlesmeninPaketleriDetailComponent } from './sozlesmenin-paketleri-detail.component';
import { SozlesmeninPaketleriUpdateComponent } from './sozlesmenin-paketleri-update.component';

@Injectable({ providedIn: 'root' })
export class SozlesmeninPaketleriResolve implements Resolve<ISozlesmeninPaketleri> {
  constructor(private service: SozlesmeninPaketleriService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISozlesmeninPaketleri> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((sozlesmeninPaketleri: HttpResponse<SozlesmeninPaketleri>) => {
          if (sozlesmeninPaketleri.body) {
            return of(sozlesmeninPaketleri.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SozlesmeninPaketleri());
  }
}

export const sozlesmeninPaketleriRoute: Routes = [
  {
    path: '',
    component: SozlesmeninPaketleriComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.sozlesmeninPaketleri.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SozlesmeninPaketleriDetailComponent,
    resolve: {
      sozlesmeninPaketleri: SozlesmeninPaketleriResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.sozlesmeninPaketleri.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SozlesmeninPaketleriUpdateComponent,
    resolve: {
      sozlesmeninPaketleri: SozlesmeninPaketleriResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.sozlesmeninPaketleri.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SozlesmeninPaketleriUpdateComponent,
    resolve: {
      sozlesmeninPaketleri: SozlesmeninPaketleriResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.sozlesmeninPaketleri.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];

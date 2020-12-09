import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDakikaKullanim, DakikaKullanim } from 'app/shared/model/dakika-kullanim.model';
import { DakikaKullanimService } from './dakika-kullanim.service';
import { DakikaKullanimComponent } from './dakika-kullanim.component';
import { DakikaKullanimDetailComponent } from './dakika-kullanim-detail.component';
import { DakikaKullanimUpdateComponent } from './dakika-kullanim-update.component';

@Injectable({ providedIn: 'root' })
export class DakikaKullanimResolve implements Resolve<IDakikaKullanim> {
  constructor(private service: DakikaKullanimService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDakikaKullanim> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((dakikaKullanim: HttpResponse<DakikaKullanim>) => {
          if (dakikaKullanim.body) {
            return of(dakikaKullanim.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DakikaKullanim());
  }
}

export const dakikaKullanimRoute: Routes = [
  {
    path: '',
    component: DakikaKullanimComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.dakikaKullanim.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DakikaKullanimDetailComponent,
    resolve: {
      dakikaKullanim: DakikaKullanimResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.dakikaKullanim.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DakikaKullanimUpdateComponent,
    resolve: {
      dakikaKullanim: DakikaKullanimResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.dakikaKullanim.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DakikaKullanimUpdateComponent,
    resolve: {
      dakikaKullanim: DakikaKullanimResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.dakikaKullanim.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];

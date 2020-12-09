import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IInternetKullanim, InternetKullanim } from 'app/shared/model/internet-kullanim.model';
import { InternetKullanimService } from './internet-kullanim.service';
import { InternetKullanimComponent } from './internet-kullanim.component';
import { InternetKullanimDetailComponent } from './internet-kullanim-detail.component';
import { InternetKullanimUpdateComponent } from './internet-kullanim-update.component';

@Injectable({ providedIn: 'root' })
export class InternetKullanimResolve implements Resolve<IInternetKullanim> {
  constructor(private service: InternetKullanimService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IInternetKullanim> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((internetKullanim: HttpResponse<InternetKullanim>) => {
          if (internetKullanim.body) {
            return of(internetKullanim.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new InternetKullanim());
  }
}

export const internetKullanimRoute: Routes = [
  {
    path: '',
    component: InternetKullanimComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.internetKullanim.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: InternetKullanimDetailComponent,
    resolve: {
      internetKullanim: InternetKullanimResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.internetKullanim.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: InternetKullanimUpdateComponent,
    resolve: {
      internetKullanim: InternetKullanimResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.internetKullanim.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: InternetKullanimUpdateComponent,
    resolve: {
      internetKullanim: InternetKullanimResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.internetKullanim.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];

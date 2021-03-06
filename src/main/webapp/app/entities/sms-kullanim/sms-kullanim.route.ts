import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISmsKullanim, SmsKullanim } from 'app/shared/model/sms-kullanim.model';
import { SmsKullanimService } from './sms-kullanim.service';
import { SmsKullanimComponent } from './sms-kullanim.component';
import { SmsKullanimDetailComponent } from './sms-kullanim-detail.component';
import { SmsKullanimUpdateComponent } from './sms-kullanim-update.component';

@Injectable({ providedIn: 'root' })
export class SmsKullanimResolve implements Resolve<ISmsKullanim> {
  constructor(private service: SmsKullanimService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISmsKullanim> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((smsKullanim: HttpResponse<SmsKullanim>) => {
          if (smsKullanim.body) {
            return of(smsKullanim.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SmsKullanim());
  }
}

export const smsKullanimRoute: Routes = [
  {
    path: '',
    component: SmsKullanimComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.smsKullanim.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SmsKullanimDetailComponent,
    resolve: {
      smsKullanim: SmsKullanimResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.smsKullanim.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SmsKullanimUpdateComponent,
    resolve: {
      smsKullanim: SmsKullanimResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.smsKullanim.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SmsKullanimUpdateComponent,
    resolve: {
      smsKullanim: SmsKullanimResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'jhipsterDemoApp.smsKullanim.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];

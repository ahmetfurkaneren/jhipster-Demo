import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterDemoSharedModule } from 'app/shared/shared.module';
import { InternetKullanimComponent } from './internet-kullanim.component';
import { InternetKullanimDetailComponent } from './internet-kullanim-detail.component';
import { InternetKullanimUpdateComponent } from './internet-kullanim-update.component';
import { InternetKullanimDeleteDialogComponent } from './internet-kullanim-delete-dialog.component';
import { internetKullanimRoute } from './internet-kullanim.route';

@NgModule({
  imports: [JhipsterDemoSharedModule, RouterModule.forChild(internetKullanimRoute)],
  declarations: [
    InternetKullanimComponent,
    InternetKullanimDetailComponent,
    InternetKullanimUpdateComponent,
    InternetKullanimDeleteDialogComponent,
  ],
  entryComponents: [InternetKullanimDeleteDialogComponent],
})
export class JhipsterDemoInternetKullanimModule {}

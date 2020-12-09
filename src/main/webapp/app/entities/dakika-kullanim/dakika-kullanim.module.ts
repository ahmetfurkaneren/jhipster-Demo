import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterDemoSharedModule } from 'app/shared/shared.module';
import { DakikaKullanimComponent } from './dakika-kullanim.component';
import { DakikaKullanimDetailComponent } from './dakika-kullanim-detail.component';
import { DakikaKullanimUpdateComponent } from './dakika-kullanim-update.component';
import { DakikaKullanimDeleteDialogComponent } from './dakika-kullanim-delete-dialog.component';
import { dakikaKullanimRoute } from './dakika-kullanim.route';

@NgModule({
  imports: [JhipsterDemoSharedModule, RouterModule.forChild(dakikaKullanimRoute)],
  declarations: [
    DakikaKullanimComponent,
    DakikaKullanimDetailComponent,
    DakikaKullanimUpdateComponent,
    DakikaKullanimDeleteDialogComponent,
  ],
  entryComponents: [DakikaKullanimDeleteDialogComponent],
})
export class JhipsterDemoDakikaKullanimModule {}

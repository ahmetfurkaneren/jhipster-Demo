import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterDemoSharedModule } from 'app/shared/shared.module';
import { SmsKullanimComponent } from './sms-kullanim.component';
import { SmsKullanimDetailComponent } from './sms-kullanim-detail.component';
import { SmsKullanimUpdateComponent } from './sms-kullanim-update.component';
import { SmsKullanimDeleteDialogComponent } from './sms-kullanim-delete-dialog.component';
import { smsKullanimRoute } from './sms-kullanim.route';

@NgModule({
  imports: [JhipsterDemoSharedModule, RouterModule.forChild(smsKullanimRoute)],
  declarations: [SmsKullanimComponent, SmsKullanimDetailComponent, SmsKullanimUpdateComponent, SmsKullanimDeleteDialogComponent],
  entryComponents: [SmsKullanimDeleteDialogComponent],
})
export class JhipsterDemoSmsKullanimModule {}

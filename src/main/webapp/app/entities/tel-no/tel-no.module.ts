import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterDemoSharedModule } from 'app/shared/shared.module';
import { TelNoComponent } from './tel-no.component';
import { TelNoDetailComponent } from './tel-no-detail.component';
import { TelNoUpdateComponent } from './tel-no-update.component';
import { TelNoDeleteDialogComponent } from './tel-no-delete-dialog.component';
import { telNoRoute } from './tel-no.route';

@NgModule({
  imports: [JhipsterDemoSharedModule, RouterModule.forChild(telNoRoute)],
  declarations: [TelNoComponent, TelNoDetailComponent, TelNoUpdateComponent, TelNoDeleteDialogComponent],
  entryComponents: [TelNoDeleteDialogComponent],
})
export class JhipsterDemoTelNoModule {}

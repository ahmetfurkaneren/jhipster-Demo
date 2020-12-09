import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterDemoSharedModule } from 'app/shared/shared.module';
import { SozlesmeComponent } from './sozlesme.component';
import { SozlesmeDetailComponent } from './sozlesme-detail.component';
import { SozlesmeUpdateComponent } from './sozlesme-update.component';
import { SozlesmeDeleteDialogComponent } from './sozlesme-delete-dialog.component';
import { sozlesmeRoute } from './sozlesme.route';

@NgModule({
  imports: [JhipsterDemoSharedModule, RouterModule.forChild(sozlesmeRoute)],
  declarations: [SozlesmeComponent, SozlesmeDetailComponent, SozlesmeUpdateComponent, SozlesmeDeleteDialogComponent],
  entryComponents: [SozlesmeDeleteDialogComponent],
})
export class JhipsterDemoSozlesmeModule {}

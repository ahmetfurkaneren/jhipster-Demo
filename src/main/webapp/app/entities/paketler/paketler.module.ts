import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterDemoSharedModule } from 'app/shared/shared.module';
import { PaketlerComponent } from './paketler.component';
import { PaketlerDetailComponent } from './paketler-detail.component';
import { PaketlerUpdateComponent } from './paketler-update.component';
import { PaketlerDeleteDialogComponent } from './paketler-delete-dialog.component';
import { paketlerRoute } from './paketler.route';

@NgModule({
  imports: [JhipsterDemoSharedModule, RouterModule.forChild(paketlerRoute)],
  declarations: [PaketlerComponent, PaketlerDetailComponent, PaketlerUpdateComponent, PaketlerDeleteDialogComponent],
  entryComponents: [PaketlerDeleteDialogComponent],
})
export class JhipsterDemoPaketlerModule {}

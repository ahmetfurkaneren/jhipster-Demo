import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterDemoSharedModule } from 'app/shared/shared.module';
import { FaturaComponent } from './fatura.component';
import { FaturaDetailComponent } from './fatura-detail.component';
import { FaturaUpdateComponent } from './fatura-update.component';
import { FaturaDeleteDialogComponent } from './fatura-delete-dialog.component';
import { faturaRoute } from './fatura.route';

@NgModule({
  imports: [JhipsterDemoSharedModule, RouterModule.forChild(faturaRoute)],
  declarations: [FaturaComponent, FaturaDetailComponent, FaturaUpdateComponent, FaturaDeleteDialogComponent],
  entryComponents: [FaturaDeleteDialogComponent],
})
export class JhipsterDemoFaturaModule {}

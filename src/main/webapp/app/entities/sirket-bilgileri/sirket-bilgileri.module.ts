import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterDemoSharedModule } from 'app/shared/shared.module';
import { SirketBilgileriComponent } from './sirket-bilgileri.component';
import { SirketBilgileriDetailComponent } from './sirket-bilgileri-detail.component';
import { SirketBilgileriUpdateComponent } from './sirket-bilgileri-update.component';
import { SirketBilgileriDeleteDialogComponent } from './sirket-bilgileri-delete-dialog.component';
import { sirketBilgileriRoute } from './sirket-bilgileri.route';

@NgModule({
  imports: [JhipsterDemoSharedModule, RouterModule.forChild(sirketBilgileriRoute)],
  declarations: [
    SirketBilgileriComponent,
    SirketBilgileriDetailComponent,
    SirketBilgileriUpdateComponent,
    SirketBilgileriDeleteDialogComponent,
  ],
  entryComponents: [SirketBilgileriDeleteDialogComponent],
})
export class JhipsterDemoSirketBilgileriModule {}

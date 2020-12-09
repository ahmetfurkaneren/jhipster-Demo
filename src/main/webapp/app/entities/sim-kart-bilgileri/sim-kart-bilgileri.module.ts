import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterDemoSharedModule } from 'app/shared/shared.module';
import { SimKartBilgileriComponent } from './sim-kart-bilgileri.component';
import { SimKartBilgileriDetailComponent } from './sim-kart-bilgileri-detail.component';
import { SimKartBilgileriUpdateComponent } from './sim-kart-bilgileri-update.component';
import { SimKartBilgileriDeleteDialogComponent } from './sim-kart-bilgileri-delete-dialog.component';
import { simKartBilgileriRoute } from './sim-kart-bilgileri.route';

@NgModule({
  imports: [JhipsterDemoSharedModule, RouterModule.forChild(simKartBilgileriRoute)],
  declarations: [
    SimKartBilgileriComponent,
    SimKartBilgileriDetailComponent,
    SimKartBilgileriUpdateComponent,
    SimKartBilgileriDeleteDialogComponent,
  ],
  entryComponents: [SimKartBilgileriDeleteDialogComponent],
})
export class JhipsterDemoSimKartBilgileriModule {}

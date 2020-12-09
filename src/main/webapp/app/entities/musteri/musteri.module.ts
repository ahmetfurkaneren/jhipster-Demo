import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterDemoSharedModule } from 'app/shared/shared.module';
import { MusteriComponent } from './musteri.component';
import { MusteriDetailComponent } from './musteri-detail.component';
import { MusteriUpdateComponent } from './musteri-update.component';
import { MusteriDeleteDialogComponent } from './musteri-delete-dialog.component';
import { musteriRoute } from './musteri.route';

@NgModule({
  imports: [JhipsterDemoSharedModule, RouterModule.forChild(musteriRoute)],
  declarations: [MusteriComponent, MusteriDetailComponent, MusteriUpdateComponent, MusteriDeleteDialogComponent],
  entryComponents: [MusteriDeleteDialogComponent],
})
export class JhipsterDemoMusteriModule {}

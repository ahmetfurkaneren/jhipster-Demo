import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterDemoSharedModule } from 'app/shared/shared.module';
import { SozlesmeninPaketleriComponent } from './sozlesmenin-paketleri.component';
import { SozlesmeninPaketleriDetailComponent } from './sozlesmenin-paketleri-detail.component';
import { SozlesmeninPaketleriUpdateComponent } from './sozlesmenin-paketleri-update.component';
import { SozlesmeninPaketleriDeleteDialogComponent } from './sozlesmenin-paketleri-delete-dialog.component';
import { sozlesmeninPaketleriRoute } from './sozlesmenin-paketleri.route';

@NgModule({
  imports: [JhipsterDemoSharedModule, RouterModule.forChild(sozlesmeninPaketleriRoute)],
  declarations: [
    SozlesmeninPaketleriComponent,
    SozlesmeninPaketleriDetailComponent,
    SozlesmeninPaketleriUpdateComponent,
    SozlesmeninPaketleriDeleteDialogComponent,
  ],
  entryComponents: [SozlesmeninPaketleriDeleteDialogComponent],
})
export class JhipsterDemoSozlesmeninPaketleriModule {}

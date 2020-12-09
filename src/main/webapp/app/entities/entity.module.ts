import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'musteri',
        loadChildren: () => import('./musteri/musteri.module').then(m => m.JhipsterDemoMusteriModule),
      },
      {
        path: 'sirket-bilgileri',
        loadChildren: () => import('./sirket-bilgileri/sirket-bilgileri.module').then(m => m.JhipsterDemoSirketBilgileriModule),
      },
      {
        path: 'paketler',
        loadChildren: () => import('./paketler/paketler.module').then(m => m.JhipsterDemoPaketlerModule),
      },
      {
        path: 'tel-no',
        loadChildren: () => import('./tel-no/tel-no.module').then(m => m.JhipsterDemoTelNoModule),
      },
      {
        path: 'sozlesme',
        loadChildren: () => import('./sozlesme/sozlesme.module').then(m => m.JhipsterDemoSozlesmeModule),
      },
      {
        path: 'sim-kart-bilgileri',
        loadChildren: () => import('./sim-kart-bilgileri/sim-kart-bilgileri.module').then(m => m.JhipsterDemoSimKartBilgileriModule),
      },
      {
        path: 'sozlesmenin-paketleri',
        loadChildren: () =>
          import('./sozlesmenin-paketleri/sozlesmenin-paketleri.module').then(m => m.JhipsterDemoSozlesmeninPaketleriModule),
      },
      {
        path: 'dakika-kullanim',
        loadChildren: () => import('./dakika-kullanim/dakika-kullanim.module').then(m => m.JhipsterDemoDakikaKullanimModule),
      },
      {
        path: 'sms-kullanim',
        loadChildren: () => import('./sms-kullanim/sms-kullanim.module').then(m => m.JhipsterDemoSmsKullanimModule),
      },
      {
        path: 'internet-kullanim',
        loadChildren: () => import('./internet-kullanim/internet-kullanim.module').then(m => m.JhipsterDemoInternetKullanimModule),
      },
      {
        path: 'fatura',
        loadChildren: () => import('./fatura/fatura.module').then(m => m.JhipsterDemoFaturaModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class JhipsterDemoEntityModule {}

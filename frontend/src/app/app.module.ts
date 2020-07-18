import { BrowserModule } from '@angular/platform-browser';
import { APP_INITIALIZER, NgModule } from '@angular/core';
import { FormsModule} from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { BackendService } from './backends-manager/backend.service';
import { DataTemplateService } from './modules/data-templates/components/data-templates/data-template.service';;
import { ModalDialogModule } from './modal-dialog/modal-dialog.module';
import { ApiInterceptor } from "./interceptors/api-interceptor.service";
import { AppConfig } from "./app.config";
import { BackendsManagerComponent } from './backends-manager/backends-manager.component';
import { TestsComponent } from './tests/tests.component';
import { BackendComponent } from './backend/backend.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
//import { DecimalPipe } from '@angular/common';
import { ReportsModule } from './modules/reports/reports.module';
import { DataTemplatesModule } from './modules/data-templates/data-templates.module';

export function initializeApp(appConfig: AppConfig) {
  return () => appConfig.load();
}

@NgModule({
  declarations: [
    AppComponent,
    BackendsManagerComponent,
    BackendComponent,
    TestsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    ModalDialogModule,
    NgbModule,
    DataTemplatesModule,
    ReportsModule
    //TestsModule
  ],
  providers: [
    BackendService,
    DataTemplateService,
    //DecimalPipe,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: ApiInterceptor,
      multi: true
    },
    AppConfig,
    {
      provide: APP_INITIALIZER,
      useFactory: initializeApp,
      deps: [AppConfig],
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { BrowserModule } from '@angular/platform-browser';
import { APP_INITIALIZER, NgModule } from '@angular/core';
import { FormsModule} from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { DataTemplateService } from './services/data-template.service';;
import { ModalDialogModule } from './modal-dialog/modal-dialog.module';
import { ApiInterceptor } from "./interceptors/api-interceptor.service";
import { AppConfig } from "./app.config";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
//import { DecimalPipe } from '@angular/common';
import { ReportsModule } from './modules/reports/reports.module';
import { DataTemplatesModule } from './modules/data-templates/data-templates.module';
import { TestsModule } from './modules/tests/tests.module';
import { TestService } from './services/tests.service';
import { BackendService } from './services/backend.service';

export function initializeApp(appConfig: AppConfig) {
  return () => appConfig.load();
}

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    ModalDialogModule,
    NgbModule,
    DataTemplatesModule,
    ReportsModule,
    TestsModule
  ],
  providers: [
    BackendService,
    DataTemplateService,
    TestService,
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

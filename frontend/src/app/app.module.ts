import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule} from '@angular/forms';

import { AppComponent } from './app.component';
import { BackendsManagerComponent } from './component/backends-manager/backends-manager.component';
import { JavaServerComponent } from './component/java-server/java-server.component';
import {BackendService} from './service/backend.service';
import {HttpClientModule} from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    BackendsManagerComponent,
    JavaServerComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [BackendService],
  bootstrap: [AppComponent]
})
export class AppModule { }

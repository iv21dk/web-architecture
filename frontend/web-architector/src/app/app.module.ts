import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule} from "@angular/forms";

import { AppComponent } from './app.component';
import { BackendsManagerComponent } from './backends-manager/backends-manager.component';
import { JavaServerComponent } from './java-server/java-server.component';

@NgModule({
  declarations: [
    AppComponent,
    BackendsManagerComponent,
    JavaServerComponent
  ],
  imports: [
    BrowserModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { AboutComponent } from "./components/about/about.component";
import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        ReactiveFormsModule,
        NgbModule],
    exports: [AboutComponent],
    declarations: [AboutComponent],
    providers: [],
})
export class AboutModule {
}
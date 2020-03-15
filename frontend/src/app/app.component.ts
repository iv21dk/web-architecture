import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'web-architecture';
  selectedSection: string;

  ngOnInit(): void {
    this.selectedSection = 'TESTS';
  }

  selectSection(selectedSection: string) {
    this.selectedSection = selectedSection;
  }

  isSelected(section: string): boolean {
    return section === this.selectedSection;
  }

}

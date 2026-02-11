import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';

import { CustomerAccountsComponent } from './customer-accounts.component';

describe('CustomerAccountsComponent', () => {
  let component: CustomerAccountsComponent;
  let fixture: ComponentFixture<CustomerAccountsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CustomerAccountsComponent, HttpClientTestingModule]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerAccountsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

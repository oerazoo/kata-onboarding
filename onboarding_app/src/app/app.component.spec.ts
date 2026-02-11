import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';

import { AppComponent } from './app.component';
import { Customer } from './model/customer.model';
import { CustomerService } from './services/customer/customer.service';

describe('AppComponent', () => {
  let fixture: ComponentFixture<AppComponent>;
  let component: AppComponent;
  let mockCustomerService: Partial<CustomerService> & { getCustomers: jasmine.Spy; addCustomer: jasmine.Spy };

  beforeEach(async () => {
    mockCustomerService = {
      getCustomers: jasmine.createSpy('getCustomers').and.returnValue(of([])),
      addCustomer: jasmine.createSpy('addCustomer').and.returnValue(of({}))
    };

    await TestBed.configureTestingModule({
      imports: [AppComponent],
      providers: [{ provide: CustomerService, useValue: mockCustomerService }]
    }).compileComponents();

    fixture = TestBed.createComponent(AppComponent);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('loadCustomers should populate customers from service', () => {
    const customers: Customer[] = [
      { id: 1, fullName: 'Alice', email: 'a@x.com', documentNumber: '123' }
    ];
    (mockCustomerService.getCustomers as jasmine.Spy).and.returnValue(of(customers));

    component.loadCustomers();

    expect(component.customers).toEqual(customers);
  });

  it('onAddCustomer should enable adding mode', () => {
    component.isAddingCustomer = false;
    component.onAddCustomer();
    expect(component.isAddingCustomer).toBeTrue();
  });

  it('onEditCustomer should set customerSelected and enable accounts modification', () => {
    const c: Customer = { id: 2, fullName: 'Bob', email: 'b@x.com', documentNumber: '456' };
    component.onEditCustomer(c);
    expect(component.isModifyingAccounts).toBeTrue();
    expect(component.customerSelected).toEqual(c);
  });

  it('submitCustomerAccounts should reset selection and disable modification mode', () => {
    component.customerSelected = { id: 3, fullName: 'C', email: 'c@x.com', documentNumber: '789' };
    component.isModifyingAccounts = true;
    component.submitCustomerAccounts();
    expect(component.customerSelected).toBeNull();
    expect(component.isModifyingAccounts).toBeFalse();
  });

  it('sendCustomerData with null should cancel adding mode', () => {
    component.isAddingCustomer = true;
    component.sendCustomerData(null);
    expect(component.isAddingCustomer).toBeFalse();
  });

  it('sendCustomerData with event should call addCustomer and reload customers', () => {
    const newCustomer: Customer = { fullName: 'New', email: 'n@x.com', documentNumber: '000' };
    const loadSpy = spyOn(component, 'loadCustomers');

    component.sendCustomerData(newCustomer);

    expect(mockCustomerService.addCustomer).toHaveBeenCalledWith(newCustomer);
    expect(loadSpy).toHaveBeenCalled();
    expect(component.isAddingCustomer).toBeFalse();
  });
});


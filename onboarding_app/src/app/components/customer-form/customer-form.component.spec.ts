import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerFormComponent } from './customer-form.component';

describe('CustomerFormComponent', () => {
  let component: CustomerFormComponent;
  let fixture: ComponentFixture<CustomerFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CustomerFormComponent]
    }).compileComponents();

    fixture = TestBed.createComponent(CustomerFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('submitForm should not emit when form is invalid and should mark controls as touched', () => {
    const emitSpy = spyOn(component.onSubmit, 'emit');

    component.submitForm();

    expect(emitSpy).not.toHaveBeenCalled();
    const fullNameControl = component.customerForm.get('fullName');
    expect(fullNameControl?.touched).toBeTrue();
  });

  it('submitForm should emit form value when valid', () => {
    const emitSpy = spyOn(component.onSubmit, 'emit');

    component.customerForm.setValue({
      fullName: 'Test User',
      email: 'test@example.com',
      documentType: 'ID',
      documentNumber: '12345'
    });

    component.submitForm();

    expect(emitSpy).toHaveBeenCalledWith(component.customerForm.value);
  });

  it('cancelForm should reset the form and emit null', () => {
    const emitSpy = spyOn(component.onSubmit, 'emit');

    component.customerForm.setValue({
      fullName: 'X',
      email: 'x@x.com',
      documentType: 'P',
      documentNumber: '999'
    });

    component.cancelForm();

    expect(emitSpy).toHaveBeenCalledWith(null);
    expect(component.customerForm.value.fullName).toBe('');
  });
});

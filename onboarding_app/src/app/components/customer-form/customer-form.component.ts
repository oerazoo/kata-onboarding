import { CommonModule } from '@angular/common';
import { Component, EventEmitter, inject, Output } from '@angular/core';
import { NonNullableFormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-customer-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './customer-form.component.html',
  styleUrl: './customer-form.component.scss'
})
export class CustomerFormComponent {

  private fb = inject(NonNullableFormBuilder)

  customerForm = this.fb.group({
    fullName: ['', [Validators.required]],
    email: ['', [Validators.required, Validators.email]],
    documentType: ['', [Validators.required]],
    documentNumber: ['', [Validators.required]]
  });

  @Output() onSubmit = new EventEmitter<any>();

  /**
   * Emits the form value when the form is valid, otherwise marks all fields as touched to show validation errors.
   */
  submitForm() {
    if(this.customerForm.invalid){
      this.customerForm.markAllAsTouched();
    }
    if (this.customerForm.valid) {
      this.onSubmit.emit(this.customerForm.value);
      //this.customerForm.reset();
    }
  }

  /**
   * Resets the form and emits null to indicate cancellation of the form submission.
   */
  cancelForm(){
    this.customerForm.reset();
    this.onSubmit.emit(null);
  }
}

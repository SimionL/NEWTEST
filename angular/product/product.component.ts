import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AllService } from '../all.service';
import { Account } from '../account';

@Component({
  selector: 'app-root',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})

export class ProductComponent {

  form: FormGroup;

  constructor(private fb: FormBuilder, private router: Router, private service: AllService) {
    this.form = fb.group({
      'customerID': [null, Validators.required],
      'initialCredit': [null, Validators.required]
    });
  }

  getAccount(): Account {
    return {
      name: '',
      surname: '',
      balance: '',
      customerID: this.form.controls.customerID.value,
      initialCredit: this.form.controls.initialCredit.value,
      isCurrentAccount: true,
      transactions: []
    } 
  }

  save() {

    this.service.saveAccount(this.getAccount()).subscribe(result => {
      this.router.navigate(['/sales']);
    },
      error => {
        window.console.log(error);
      }
    );
  }

  clearForm() {
    this.form.controls.customerID.setValue("");
    this.form.controls.initialCredit.setValue(0);
  }

  goToSales() {
    this.router.navigate(['/sales']);
  }
}
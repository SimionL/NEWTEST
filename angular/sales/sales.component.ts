import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AllService } from '../all.service';
import { Account } from '../account';

@Component({
  selector: 'app-sales',
  templateUrl: './sales.component.html',
  styleUrls: ['./sales.component.css']
})

export class SalesComponent implements OnInit {

  accounts: Account[];

  constructor(public router: Router, private service: AllService) {
    this.accounts = [];
  }

  ngOnInit() {

    this.service.getAccounts().subscribe(result => {
      if(result){
        this.accounts = result;
      }
    },
      error => {
        window.console.log(error);
      }
    );

  }

  goToProduct() {
    this.router.navigate(['/product']);
  }

}


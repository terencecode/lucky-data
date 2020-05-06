import {Component, Directive, Input, OnInit} from '@angular/core';
import {User} from "../../model/user";
import {UserService} from "../../service/user.service";
import {FormBuilder, FormGroup, NgControl} from "@angular/forms";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.sass']
})

export class ProfileComponent implements OnInit {
  user: User;
  form: FormGroup;

  constructor(private userService: UserService){
  }

  ngOnInit(): void {
    this.userService.getUserInfo().subscribe(user => {
      this.user = user;
    });
  }

}

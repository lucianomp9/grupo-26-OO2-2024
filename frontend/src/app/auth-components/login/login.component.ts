import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../auth-services/auth-service/auth.service';
import { Router } from '@angular/router';
import { StorageService } from '../../auth-services/storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  isSpinning: boolean;

  constructor(private authService: AuthService,
              private fb: FormBuilder, 
              private router: Router
  ){}

  ngOnInit(){
    this.loginForm = this.fb.group({
      email: [null, Validators.required],
      password: [null, Validators.required]
    });
  }

  submitForm(){
    this.authService.login(this.loginForm.value).subscribe(
      (res: any) => {
        console.log(res);
        if (res.userId != null) {
          const user = {
            id: res.userId,
            role: res.userRole
          };
          console.log(user);
          StorageService.saveToken(res.jwt);
          StorageService.saveUser(user);
          if (StorageService.isAdminLoggedIn()) {
            this.router.navigateByUrl("admin/dashboard");
          } else if (StorageService.isCustomerLoggedIn()) {
            this.router.navigateByUrl("customer/dashboard");
          } else {
            console.log("Unknown user role");
          }
        } else {
          console.log("Wrong credentials");
        }
      },
      (error) => {
        console.error("Login error:", error);
        // Aquí puedes agregar lógica adicional para manejar errores de inicio de sesión
      }
    );
  }

}

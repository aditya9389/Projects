import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { provideHttpClient } from '@angular/common/http';
import { importProvidersFrom } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { provideRouter } from '@angular/router';
import { routes } from './app/app.routes'; // ✅ Import routes from app.routes.ts

bootstrapApplication(AppComponent, {
  providers: [
    provideHttpClient(),  
    importProvidersFrom(FormsModule),
    provideRouter(routes) // ✅ Use routes from app.routes.ts
  ]
}).catch(err => console.error(err));

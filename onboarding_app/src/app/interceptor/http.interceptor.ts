import { HttpErrorResponse, HttpInterceptorFn, HttpStatusCode } from '@angular/common/http';
import { inject } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { NotificationService } from '../services/notification/notification.service';

export const httpInterceptor: HttpInterceptorFn = (req, next) => {

    const notify = inject(NotificationService);


    return next(req).pipe(
    catchError((error: HttpErrorResponse) => {

      console.log(error);

      let message = 'Error inesperado';

      // Manejar errores de conexión / CORS (status 0) y distintos formatos de error.error
      if (error?.status === 0) {
        message = 'No se pudo conectar con el servidor';
      } else if (error?.error) {
        if (typeof error.error === 'string') {
          message = error.error;
        } else if ((error.error as any)?.message) {
          message = (error.error as any).message;
        }
      }

      if (
        error?.status === HttpStatusCode.BadRequest ||
          error?.status === HttpStatusCode.NotFound ||
          error?.status === HttpStatusCode.NotAcceptable
       ) {
        notify.warning(message);
      }

      if (
        error?.status === HttpStatusCode.InternalServerError ||
        error?.status === HttpStatusCode.Conflict ||
        error?.status === 0
       ) {
        notify.error(message);
      }


      return throwError(() => error);
    })
  );
};

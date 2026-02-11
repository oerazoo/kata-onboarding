import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Notification } from '../../model/notification.model';

export type NotificationType = 'success' | 'error' | 'info' | 'warning';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  private notifications$ = new BehaviorSubject<
    Notification[] | null
  >([]);
  private counter = 0;

  get stream$() {
    return this.notifications$.asObservable();
  }

  success(message: string, duration: number = 3000) {
    this.showNotification(message, 'success', duration);
  }

  warning(message: string, duration: number = 4000) {
    this.showNotification(message, 'warning', duration);
  }

  info(message: string, duration: number = 3000) {
    this.showNotification(message, 'info', duration);
  }

  error(message: string, duration: number = 5000) {
    this.showNotification(message, 'error', duration);
  }

  private showNotification(
    message: string,
    type: NotificationType,
    duration: number,
  ) {
    const notification: Notification = { id: this.counter++, message, type, duration };
    const currentNotifications: Notification[] = this.notifications$.getValue() || [];

    this.notifications$.next([...currentNotifications, notification]);

    setTimeout(() => {
      this.remove(notification.id);
    }, duration);
  }

  remove(id: number) {
    this.notifications$.next(
      (this.notifications$.getValue() || []).filter((n) => n.id !== id),
    );
  }

  clear() {
    this.notifications$.next([]);
  }

}

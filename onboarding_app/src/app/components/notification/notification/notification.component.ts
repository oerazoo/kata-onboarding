import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { NotificationService } from '../../../services/notification/notification.service';
import { Notification } from '../../../model/notification.model';

@Component({
  selector: 'app-notification',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './notification.component.html',
  styleUrl: './notification.component.scss'
})
export class NotificationComponent {

    notifications : Notification[] = [];

  constructor(private notify: NotificationService) {}

  ngOnInit(): void {
    this.notify.stream$.subscribe((notifications: Notification[] | null) => {
      this.notifications = notifications || [];
    });
  }

  close(id: number) {
    this.notify.remove(id);
  }

}

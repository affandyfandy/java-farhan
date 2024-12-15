import { Component } from '@angular/core';
import { ICellRendererAngularComp } from 'ag-grid-angular';
import { ICellRendererParams } from 'ag-grid-community';
import { Status } from '../../../core/interfaces/status.type';

@Component({
  selector: 'app-list-products-action',
  standalone: true,
  templateUrl: './list-products-action.component.html',
})
export class ListProductsActionComponent implements ICellRendererAngularComp {
  private params!: ICellRendererParams;
  // Public property to expose the status
  public status: Status = Status.Deactive;
  public Status = Status;

  agInit(params: ICellRendererParams): void {
    this.params = params;
    this.status = params.data.status; // Set status based on data
  }

  refresh(params: ICellRendererParams): boolean {
    this.params = params;
    this.status = params.data.status; // Update status when refreshing
    return true;
  }

  onOpen(): void {
    const parentComponent = this.params.context as any;
    if (parentComponent && parentComponent.setActiveProduct) {
      parentComponent.setActiveProduct(
        this.params.data,
        this.params.data.index
      );
    } else {
      console.error('Parent component or setActiveProduct method not found.');
    }
  }

  onDelete(): void {
    if (confirm('Are you sure you want to delete this product?')) {
      const parentComponent = this.params.context as any; // Cast context to 'any' to access parent
      if (parentComponent && parentComponent.deleteProduct) {
        parentComponent.deleteProduct(this.params.data.id); // Call deleteProduct on parent component
      } else {
        console.error('Parent component or deleteProduct method not found.');
      }
    }
  }

  onToggleStatus(event: Event | undefined): void {
    const input = (event?.target as HTMLInputElement) || null;
    if (input) {
      // Get current status from input (checked/unchecked)
      const currentStatus = this.params.data.status;
      if (
        confirm('Are you sure you want to change the status of this product?')
      ) {
        const parentComponent = this.params.context as any;
        if (parentComponent && parentComponent.updateProductStatus) {
          parentComponent.updateProductStatus(
            this.params.data.id,
            currentStatus
          );
        } else {
          console.error(
            'Parent component or updateProductStatus method not found.'
          );
        }
      }
    } else {
      console.error('Event or input element is not available.');
    }
  }
}

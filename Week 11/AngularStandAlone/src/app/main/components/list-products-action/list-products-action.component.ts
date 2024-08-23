import { Component } from '@angular/core';
import { ICellRendererAngularComp } from 'ag-grid-angular';
import { ICellRendererParams } from 'ag-grid-community';

@Component({
  selector: 'app-list-products-action',
  standalone: true,
  template: `
    <div class="flex gap-4">
      <button (click)="onOpen()" class="border border-black px-3 z-50">
        Open
      </button>
      <button (click)="onDelete()" class="border border-black px-3 z-50">
        Delete
      </button>
    </div>
  `,
})
export class ListProductsActionComponent implements ICellRendererAngularComp {
  private params!: ICellRendererParams;

  agInit(params: ICellRendererParams): void {
    this.params = params;
  }

  refresh(params: ICellRendererParams): boolean {
    this.params = params;
    return true;
  }

  onOpen(): void {
    alert('clicked open');
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
}

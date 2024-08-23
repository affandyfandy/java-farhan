import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListProductsActionComponent } from './list-products-action.component';

describe('ListProductsActionComponent', () => {
  let component: ListProductsActionComponent;
  let fixture: ComponentFixture<ListProductsActionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListProductsActionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListProductsActionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

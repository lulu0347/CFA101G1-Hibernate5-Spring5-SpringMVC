package com.productKind.model;

import java.util.*;

public interface ProductKindDAO_interface {
          public void insert(ProductKindVO productKindVO);
          public void update(ProductKindVO productKindVO);
          public ProductKindVO findByPrimaryKey(Integer kindNo);
          public List<ProductKindVO> getAll();
}

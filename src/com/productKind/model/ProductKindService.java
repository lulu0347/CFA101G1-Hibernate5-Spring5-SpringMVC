package com.productKind.model;

import java.sql.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.item.model.ItemDAO_interface;

public class ProductKindService {
	private static ProductKindDAO_interface dao;
	
	static {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("model-config2-JndiObjectFactoryBean.xml");
		dao =(ProductKindDAO_interface) context.getBean("ProductKindDAO");
	}
	
	
	public ProductKindVO addProductKind(String kindName) {
		ProductKindVO productKindVO = new ProductKindVO();
		productKindVO.setKindName(kindName);
		dao.insert(productKindVO);
		return productKindVO;
	}
	
	public ProductKindVO updateProductKind(Integer kindNo, String kindName) {
		ProductKindVO productKindVO = new ProductKindVO();
		productKindVO.setKindNo(kindNo);
		productKindVO.setKindName(kindName);
		dao.insert(productKindVO);
		return productKindVO;
	}
	
	public ProductKindVO getOneProductKind(Integer kindNo) {
		return dao.findByPrimaryKey(kindNo);		
	}
	
	public List<ProductKindVO> getAllProductKind() {
		return dao.getAll();		
	}
}

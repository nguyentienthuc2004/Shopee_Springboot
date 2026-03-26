package org.thuc.shoppe.proxy;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.thuc.shoppe.repo.ProductVariantRepository;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

@Service
public class ProductVariantServiceProxyImpl extends ProductVariantServiceProxy {
    
    public TransactionInterceptor transactionInterceptor;
    public ProductVariantServiceProxy productVariantServiceProxy;
    public ProductVariantServiceProxyImpl(
            ProductVariantRepository productVariantRepository,
            TransactionInterceptor transactionInterceptor,
            ProductVariantServiceProxy productVariantServiceProxy) {
        super(productVariantRepository);
        this.transactionInterceptor = transactionInterceptor;
        this.productVariantServiceProxy = productVariantServiceProxy;
    }
    @Override
    public void updateStock(Long productVariantId, int newStock) throws NoSuchMethodException {
        Method method = ProductVariantServiceProxy.class.getMethod("updateStock", Long.class, int.class);
        MethodInvocation methodInvocation = new MethodInvocation() {
            @Override
            public Method getMethod() {
                return method;
            }

            @Override
            public Object[] getArguments() {
                return new Object[]{productVariantId, newStock};
            }

            @Override
            public Object proceed() throws Throwable {
                productVariantServiceProxy.updateStock(productVariantId, newStock);
                return null;
            }

            @Override
            public Object getThis() {
                return productVariantServiceProxy;
            }

            @Override
            public AccessibleObject getStaticPart() {
                return null;
            }
        };
        try{
            transactionInterceptor.invoke(methodInvocation);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

    }

}

package com.pro.social.media.agent.multitenancy;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

import com.pro.social.media.agent.utills.IsEmptyUtil;

@Component
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {
	private static final String DEFAULT_TENANT_ID = "DEFAULT";
	/*
     * (non-Javadoc)
     * 
     * @see org.hibernate.context.spi.CurrentTenantIdentifierResolver#
     * resolveCurrentTenantIdentifier()
     */
	@Override
	public String resolveCurrentTenantIdentifier() {
		// TODO Auto-generated method stub
		 // The tenant is stored in a ThreadLocal before the end user's login information
        // is submitted for spring security authentication mechanism. Refer to
        // CustomAuthenticationFilter
		String tenant = TenantContext.getCurrentTenant();
		return IsEmptyUtil.isNotBlank(tenant) ? tenant : DEFAULT_TENANT_ID;
	}
	/*
     * (non-Javadoc)
     * 
     * @see org.hibernate.context.spi.CurrentTenantIdentifierResolver#
     * validateExistingCurrentSessions()
     */
	@Override
	public boolean validateExistingCurrentSessions() {
		// TODO Auto-generated method stub
		return true;
	}

}

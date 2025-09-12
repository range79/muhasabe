package com.range.muhasebe.common.config

import com.range.muhasebe.common.config.tenant.CurrentTenantIdentifierResolverImpl
import com.range.muhasebe.common.config.tenant.SchemaMultiTenantConnectionProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
class HibernateConfig(
    private val dataSource: DataSource
) {

    @Bean
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        val vendorAdapter = HibernateJpaVendorAdapter()
        val emf = LocalContainerEntityManagerFactoryBean()
        emf.dataSource = dataSource
        emf.setPackagesToScan("com.range.muhasebe")
        emf.jpaVendorAdapter = vendorAdapter

        val properties = HashMap<String, Any>()
        properties["hibernate.multiTenancy"] = "SCHEMA"
        properties["hibernate.multi_tenant_connection_provider"] = SchemaMultiTenantConnectionProvider(dataSource)
        properties["hibernate.tenant_identifier_resolver"] = CurrentTenantIdentifierResolverImpl()
        properties["hibernate.hbm2ddl.auto"] = "update"
        emf.setJpaPropertyMap(properties)

        return emf
    }
}

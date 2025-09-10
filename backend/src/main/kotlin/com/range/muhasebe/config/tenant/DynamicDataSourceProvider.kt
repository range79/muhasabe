package com.range.muhasebe.config.tenant


import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import javax.sql.DataSource

@Component
class DynamicDataSourceProvider {

    private val dataSources: MutableMap<String, DataSource> = mutableMapOf()

    @Value("\${spring.datasource.url}")
    lateinit var url: String
    @Value("\${spring.datasource.username}")
    lateinit var username: String
    @Value("\${spring.datasource.password}")
    lateinit var password: String
    @Value("\${spring.datasource.driver-class-name}")
    lateinit var driver: String

    fun getDataSource(tenantId: String): DataSource {
        return dataSources.computeIfAbsent(tenantId) {

            val masterDataSource = createDataSource(url)
            createSchemaIfNotExists(masterDataSource, tenantId)


            createDataSource("$url?currentSchema=$tenantId")
        }
    }

    private fun createDataSource(url: String): DataSource {
        return DataSourceBuilder.create()
            .url(url)
            .username(username)
            .password(password)
            .driverClassName(driver)
            .build()
    }

    private fun createSchemaIfNotExists(dataSource: DataSource, tenantId: String) {
        val jdbcTemplate = JdbcTemplate(dataSource)
        jdbcTemplate.execute("CREATE SCHEMA IF NOT EXISTS $tenantId")
    }
}



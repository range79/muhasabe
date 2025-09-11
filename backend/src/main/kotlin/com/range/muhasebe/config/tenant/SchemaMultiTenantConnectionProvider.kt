package com.range.muhasebe.config.tenant

import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider
import java.sql.Connection
import javax.sql.DataSource

class SchemaMultiTenantConnectionProvider(
    private val dataSource: DataSource
) : MultiTenantConnectionProvider<String> {

    override fun getAnyConnection(): Connection = dataSource.connection

    override fun releaseAnyConnection(connection: Connection) {
        connection.close()
    }

    override fun getConnection(tenantIdentifier: String): Connection {
        val conn = dataSource.connection
        conn.createStatement().use { stmt ->
            stmt.execute("SET search_path TO \"$tenantIdentifier\"")
        }
        return conn
    }

    override fun releaseConnection(tenantIdentifier: String, connection: Connection) {
        connection.createStatement().use { stmt ->
            stmt.execute("SET search_path TO public")
        }
        connection.close()
    }

    override fun supportsAggressiveRelease() = false


    override fun isUnwrappableAs(unwrapType: Class<*>):Boolean {
        return false
    }

    override fun <T : Any?> unwrap(
        unwrapType:  Class<T?>
    ): T? {
    return null
    }
}

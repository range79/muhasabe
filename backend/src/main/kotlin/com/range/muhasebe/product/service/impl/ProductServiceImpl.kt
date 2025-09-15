package com.range.muhasebe.product.service.impl

import com.range.muhasebe.product.domain.model.Product
import com.range.muhasebe.product.domain.model.ProductVariant
import com.range.muhasebe.product.domain.repository.ProductRepository
import com.range.muhasebe.product.domain.repository.ProductVariantRepository
import com.range.muhasebe.product.dto.request.ProductRequest
import com.range.muhasebe.product.dto.response.ProductResponse
import com.range.muhasebe.product.exception.ProductNotFoundException
import com.range.muhasebe.product.service.CategoryService
import com.range.muhasebe.product.service.ProductService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository,
    private val categoryService: CategoryService,
    private val productVariantRepository: ProductVariantRepository,
): ProductService {
    override fun createProduct(request: ProductRequest) {
        val product= request.toProduct()

        request.variants.forEach { variantReq ->
            val variant = ProductVariant(
                id = null,
                name = variantReq.name,
                price = variantReq.price,
                quantity = variantReq.quantity,
                product = product
            )
            product.variants.add(variant)
            productVariantRepository.save(variant)
        }
        productRepository.save(product)
    }

    override fun updateProduct(
        id: UUID,
        request: ProductRequest
    ): ProductResponse {
        var product= findProduct(id)
        val variants =product.variants

        variants.forEach { variant ->
            productVariantRepository.delete(variant)
        }
        val productVariantList= mutableListOf<ProductVariant>()
        request.variants.forEach { variantReq ->
            val variant = ProductVariant(
                id = null,
                name = variantReq.name,
                price = variantReq.price,
                quantity = variantReq.quantity,
                barcode = variantReq.barcode,
                product = product
            )

            productVariantList.add(productVariantRepository.save(variant))
        }
        product.update(request,productVariantList)
        product= productRepository.save(product)
        return product.toResponse()

    }

    override fun deleteProduct(id: UUID) {
        val product =findProduct(id)
        return productRepository.delete(product)
    }

    override fun getProductById(id: UUID): Product {
        return findProduct(id)
    }

    override fun getAllProducts(): Page<ProductResponse> {
        return productRepository.findAll(Pageable.unpaged())
            .map { it.toResponse() }
    }

    override fun getProductsByCategory(categoryId: UUID,pageable: Pageable): Page<ProductResponse> {
        return productRepository.findAllByCategoryId(categoryId,pageable)
            .map { it.toResponse() }
    }


    fun Product.toResponse():ProductResponse{
        return ProductResponse(
            id= this.id,
            name=this.name,

            )
    }


    fun ProductRequest.toProduct(): Product{
        val category=  categoryService.findCategory(this.categoryId)
        return Product(
            id = null,
            name =this.name,
            description =this.description,
            category = category
        )
    }
    fun Product.update(request: ProductRequest,productVariants: MutableList<ProductVariant>){
        this.name=request.name
        this.description=request.description
        this.category=categoryService.findCategory(request.categoryId)
        this.variants.clear()
        this.variants.addAll(productVariants)
    }


    fun findProduct(id: UUID): Product{
        return productRepository.findById(id).orElseThrow{
            ProductNotFoundException(null)
        }
    }
}

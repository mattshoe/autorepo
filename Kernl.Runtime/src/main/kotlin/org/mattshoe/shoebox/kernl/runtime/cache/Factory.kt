package org.mattshoe.shoebox.kernl.runtime.cache

import org.mattshoe.shoebox.kernl.runtime.cache.associativecache.inmemory.BaseAssociativeCacheKernl
import org.mattshoe.shoebox.kernl.runtime.cache.nocache.BaseNoCacheKernl
import org.mattshoe.shoebox.kernl.runtime.cache.nocache.NoCacheKernl
import org.mattshoe.shoebox.kernl.runtime.cache.singlecache.inmemory.BaseSingleCacheKernl
import org.mattshoe.shoebox.kernl.runtime.cache.singlecache.SingleCacheKernl
import org.mattshoe.shoebox.kernl.runtime.cache.associativecache.AssociativeMemoryCacheKernl
import kotlin.reflect.KClass

fun <TParams: Any, TData: Any> NoCacheKernl(
    fetchData: suspend (TParams) -> TData
): NoCacheKernl<TParams, TData> {
    return object : BaseNoCacheKernl<TParams, TData>() {
        override suspend fun fetchData(params: TParams): TData = fetchData(params)
    }
}

fun <TParams: Any, TData: Any> SingleCacheKernl(
    clazz: KClass<TData>,
    fetchData: suspend (TParams) -> TData
): SingleCacheKernl<TParams, TData> {
    return object : BaseSingleCacheKernl<TParams, TData>() {
        override val dataType = clazz
        override suspend fun fetchData(params: TParams): TData = fetchData(params)
    }
}

fun <TParams: Any, TData: Any> multiSourceLiveRepository(
    clazz: KClass<TData>,
    fetchData: suspend (TParams) -> TData
): AssociativeMemoryCacheKernl<TParams, TData> {
    return object : BaseAssociativeCacheKernl<TParams, TData>() {
        override val dataType = clazz
        override suspend fun fetchData(params: TParams): TData = fetchData(params)
    }
}
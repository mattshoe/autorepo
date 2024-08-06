package associativememorycache.impl

import org.mattshoe.shoebox.kernl.runtime.cache.associativecache.AssociativeMemoryCacheKernl
import org.mattshoe.shoebox.kernl.models.ServiceResponse
import kernl.org.mattshoe.shoebox.kernl.associativememorycache.MultiSameParamComplexReturn
import associativememorycache.AssociativeMemoryCacheScenariosTest

class MultiSameParamComplexReturnTest : AssociativeMemoryCacheScenariosTest<MultiSameParamComplexReturn.Params, ServiceResponse>() {
    override fun repository(): AssociativeMemoryCacheKernl<MultiSameParamComplexReturn.Params, ServiceResponse> {
        return MultiSameParamComplexReturn.Factory { id, bar ->
            onFetch(MultiSameParamComplexReturn.Params(id, bar))
            ServiceResponse(id.toInt() + bar.code)
        }
    }

    override val testData = mapOf(
        MultiSameParamComplexReturn.Params("42", ServiceResponse(58)) to ServiceResponse(100),
        MultiSameParamComplexReturn.Params("96", ServiceResponse(4)) to ServiceResponse(100),
        MultiSameParamComplexReturn.Params("1", ServiceResponse(2)) to ServiceResponse(3)
    )
}
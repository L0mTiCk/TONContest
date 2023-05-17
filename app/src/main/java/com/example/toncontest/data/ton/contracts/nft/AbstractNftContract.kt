package com.example.toncontest.data.ton.contracts.nft

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.toncontest.data.ton.constants.OpCodes
import com.example.toncontest.data.ton.contracts.LiteContract
import com.example.toncontest.data.ton.extension.utcLongNow
import org.ton.block.AddrNone
import org.ton.block.AddrStd
import org.ton.block.Coins
import org.ton.block.MsgAddress
import org.ton.cell.Cell
import org.ton.cell.CellBuilder
import org.ton.lite.client.LiteClient
import org.ton.tlb.storeTlb

abstract class AbstractNftContract(
    override val liteClient: LiteClient,
    val ownerAddress: AddrStd,
    val metadataUrl: String,
    open val collectionAddress: AddrStd? = null,
    open val index: Int? = null,
    open val explicitAddress: AddrStd? = null
): LiteContract(liteClient) {

    open val commonMetadataUrl: String? = null

    abstract override val address: AddrStd

    // init storage
    override fun createDataInit(): Cell = CellBuilder.createCell {
        storeUInt(0, 64) // index
        storeTlb(MsgAddress, collectionAddress ?: AddrNone)
        storeTlb(MsgAddress, ownerAddress) // owner address
        storeRefs(packOffChainMetadata(metadataUrl))
    }


    companion object {
        data class Content(
            var metadataUrl: String? = null,
            var contentRaw: Cell = Cell()
        )

        @RequiresApi(Build.VERSION_CODES.O)
        fun packTransferOwnership(newOwner: AddrStd, forwardAmount: Long, queryId: Long? = null, currentOwner: AddrStd? = null) =
            CellBuilder.createCell {
                storeUInt(OpCodes.OP_NFT_TRANSFER, 32)
                storeUInt(queryId ?: utcLongNow(), 64)
                storeTlb(MsgAddress, newOwner)
                storeTlb(MsgAddress, currentOwner ?: AddrNone) // response destination
                storeInt(0, 1) // custom payload flag
                storeTlb(Coins, Coins.ofNano(forwardAmount))
        }
    }
}

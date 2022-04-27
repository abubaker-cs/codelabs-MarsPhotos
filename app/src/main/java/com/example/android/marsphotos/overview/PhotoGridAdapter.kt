package com.example.android.marsphotos.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsphotos.databinding.GridViewItemBinding
import com.example.android.marsphotos.network.MarsPhoto

/**
 * The advantage of using DiffUtil:
 * Is every time some item in the RecyclerView is added, removed or changed, the whole list doesn't
 * get refreshed. Only the items that have been changed are refreshed.
 */

class PhotoGridAdapter : ListAdapter<MarsPhoto,
        PhotoGridAdapter.MarsPhotoViewHolder>(DiffCallback) {


    /**
     * The onCreateViewHolder() method needs to return a new MarsPhotoViewHolder, created by
     * inflating the GridViewItemBinding and using the LayoutInflater from your parent ViewGroup context.
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoGridAdapter.MarsPhotoViewHolder {

        return MarsPhotoViewHolder(
            GridViewItemBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )

    }

    override fun onBindViewHolder(holder: PhotoGridAdapter.MarsPhotoViewHolder, position: Int) {

        // Here you call getItem() to get the MarsPhoto object associated with the current RecyclerView position
        val marsPhoto = getItem(position)

        // pass that property to the bind() method in the MarsPhotoViewHolder
        holder.bind(marsPhoto)

    }


    class MarsPhotoViewHolder(
        private var binding:
        GridViewItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(MarsPhoto: MarsPhoto) {

            //
            binding.photo = MarsPhoto

            //  causes the update to execute immediately
            binding.executePendingBindings()
        }

    }

    /**
     * The DiffCallback object extends DiffUtil.ItemCallback with the generic type of object you
     * want to compare—MarsPhoto. You will compare two Mars photo objects inside this implementation.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<MarsPhoto>() {

        // This method is called by DiffUtil to decide whether two objects represent the same Item.
        override fun areItemsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {

            // The ID of every item(MarsPhoto object) is unique. Compare the IDs of oldItem and newItem, and return the result.
            return oldItem.id == newItem.id

        }

        // This method is called by DiffUtil when it wants to check whether two items have the same data
        override fun areContentsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {

            // The important data in the MarsPhoto is the image URL.
            // Compare the URLs of oldItem and newItem, and return the result.
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }
    }


}
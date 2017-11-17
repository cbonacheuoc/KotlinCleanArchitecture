package uoc.cbonache.tfg.ui.shippingList

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uoc.cbonache.tfg.R
import uoc.cbonache.tfg.ui.model.ShippingViewEntity
import uoc.cbonache.tfg.ui.setPrefixTextBold
import kotlinx.android.synthetic.main.item_shipping.view.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * @author cbonache
 */
class ShippingAdapter  @Inject constructor(val shippingListPresenter: ShippingListPresenter) : RecyclerView.Adapter<ShippingViewHolder>() {

    var shippingList: List<ShippingViewEntity> by Delegates.observable(emptyList()) {
        _, old, new->
        autoNotify(old, new) {
            old, new -> old.id == new.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShippingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shipping, parent, false)
        return ShippingViewHolder(view)
    }

    override fun getItemCount(): Int = shippingList.size

    override fun onBindViewHolder(holder: ShippingViewHolder, position: Int) {
        holder.bind(shippingList[position], shippingListPresenter)
    }

    fun autoNotify(oldList: List<ShippingViewEntity>, newList: List<ShippingViewEntity>, compare: (ShippingViewEntity, ShippingViewEntity) -> Boolean) {

        val diff = DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldList[oldItemPosition] == newList[newItemPosition]
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return compare(oldList[oldItemPosition], newList[newItemPosition])
            }

            override fun getOldListSize() = oldList.size

            override fun getNewListSize() = newList.size

        })
        diff.dispatchUpdatesTo(this)
    }
}

class ShippingViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(shippingToShow: ShippingViewEntity, presenter: ShippingListPresenter) {

        changeFragileIconVisibility(shippingToShow.fragile, itemView.fragile_icon)

        val context = itemView.context

        itemView.address.setPrefixTextBold(context.getString(R.string.address),shippingToShow.address," ")
        itemView.number_packages.setPrefixTextBold(context.getString(R.string.number),shippingToShow.number," ")
        itemView.hour.setPrefixTextBold(context.getString(R.string.hour),getHourInProperFormat(shippingToShow.updated_at)," ")
        itemView.setOnClickListener{
            presenter.onShippingPressed(shippingToShow.id)
        }
    }

    private fun getHourInProperFormat(hour: String) : String {
        val date = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).parse(hour)
        return  SimpleDateFormat("HH:mm", Locale.getDefault()).format(date)
    }

    private fun changeFragileIconVisibility(isFragile: String, iconFragile: View){
        if(isFragile == "1") iconFragile.visibility = View.VISIBLE
        else iconFragile.visibility = View.GONE
    }
}
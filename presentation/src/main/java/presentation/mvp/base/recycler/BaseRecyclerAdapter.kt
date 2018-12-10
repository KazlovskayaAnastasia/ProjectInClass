package presentation.mvp.base.recycler

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import io.reactivex.subjects.PublishSubject

abstract class BaseRecyclerAdapter <
        Entity,
        VM: BaseItemViewModel<Entity>>
    (private val itemList : MutableList<Entity> = mutableListOf())
    : RecyclerView.Adapter<BaseViewHolder<Entity, VM, *>>() {

    private val clickItemSubject = PublishSubject.create<ItemClick<Entity>>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): BaseViewHolder<Entity, VM, *> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Entity, VM, *>, position: Int) {

        holder.bind(itemList[position], position)
    }

    override fun getItemCount(): Int = itemList.size

    fun addItems(items:List<Entity>){
        val startPos = itemList.size
        itemList.addAll(items)
        //notifyDataSetChanged()
        notifyItemRangeChanged(startPos, items.size)
    }

    fun cleanItems(){
        itemList.clear()
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: BaseViewHolder<Entity, VM, *>) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            val pos = holder.adapterPosition
            clickItemSubject.onNext(ItemClick(itemList[pos], pos))
            holder.viewModel.onItemClick()
        }
    }

    override fun onViewDetachedFromWindow(holder: BaseViewHolder<Entity, VM, *>) {
        super.onViewDetachedFromWindow(holder)
        holder.itemView.setOnClickListener(null)
    }


}
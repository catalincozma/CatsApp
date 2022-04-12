package com.example.catapplication.presentation.main.login.cats.model

import android.content.res.ColorStateList
import android.view.View
import androidx.core.content.ContextCompat
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.catapplication.R
import com.google.android.material.chip.Chip

@EpoxyModelClass(layout = R.layout.cat_header)
abstract class HeaderModel : EpoxyModelWithHolder<HeaderModel.Holder?>() {

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash, EpoxyAttribute.Option.DoNotUseInToString)
    var clickCallback: View.OnClickListener? = null


    @EpoxyAttribute
    var originHeader: CatsHomeUiModel.HomeOriginHeader? = null


    override fun bind(holder: Holder) {
        holder.chip.setOnClickListener(clickCallback)
        val selected = originHeader?.isSelected ?: false
        holder.chip.text = originHeader?.headerTitle ?: ""
        holder.chip.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(holder.chip.context, if (selected)R.color.dark_gray else R.color.gray))
        holder.chip.setTextColor(ColorStateList.valueOf(ContextCompat.getColor(holder.chip.context, if (selected)R.color.white else R.color.black)))

        if (holder.chip.isChecked && selected) {
            holder.chip.isCheckable = true
            holder.chip.isChecked = false
        } else {
            holder.chip.isCheckable = selected
            holder.chip.isChecked = selected
        }
    }

    class Holder : EpoxyHolder() {
        lateinit var chip: Chip
        lateinit var view: View


        override fun bindView(itemView: View) {
            chip = itemView.findViewById(R.id.filter)
            view = itemView
        }
    }
}
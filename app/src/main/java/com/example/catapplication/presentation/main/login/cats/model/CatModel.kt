package com.example.catapplication.presentation.main.login.cats.model

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.catapplication.R
import com.example.catapplication.data.locale.model.CatCharacteristics
import com.example.catapplication.utils.loadFromUrl

@EpoxyModelClass(layout = R.layout.cat_model)
abstract class CatModel : EpoxyModelWithHolder<CatModel.Holder?>() {
    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash, EpoxyAttribute.Option.DoNotUseInToString)
    var clickCallback: View.OnClickListener? = null

    @EpoxyAttribute
    lateinit var cat :CatCharacteristics


    override fun bind(holder: Holder) {
        holder.view.setOnClickListener(clickCallback)
        holder.description.text = cat.description ?: ""
        holder.name.text =cat.name
        holder.image.loadFromUrl(cat.image.url)
    }

    class Holder : EpoxyHolder(){
        lateinit var name : TextView
        lateinit var description : TextView
        lateinit var image : ImageView
        lateinit var view: ConstraintLayout



        override fun bindView(itemView: View) {
            name = itemView.findViewById(R.id.name)
            description = itemView.findViewById(R.id.description)
            image = itemView.findViewById(R.id.image)
            view = itemView.findViewById(R.id.constraint)
        }
    }
}
package com.example.catapplication.presentation.main.login.cats

import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyController
import com.example.catapplication.presentation.main.login.cats.model.CatModel_
import com.example.catapplication.presentation.main.login.cats.model.CatsHomeUiModel
import com.example.catapplication.presentation.main.login.cats.model.HeaderModel_
import com.example.catapplication.utils.UiEvent
import io.reactivex.rxjava3.subjects.PublishSubject

class CatsHomeController : EpoxyController() {
    val uiEvents: PublishSubject<UiEvent> = PublishSubject.create()
    private val catsMap = mutableListOf<CatsHomeUiModel>()

    fun setModels(catsHomeUi: CatsHomeUiModel) {
        catsMap.clear()
        catsMap.add(catsHomeUi)
        requestModelBuild()
    }

    override fun buildModels() {
        val carousel: MutableList<HeaderModel_> = mutableListOf()

        catsMap.forEach {
            it.originHeader.forEachIndexed { index, homeHeader ->

                carousel.add(
                    HeaderModel_()
                        .id("_${homeHeader.headerTitle}_")
                        .clickCallback { _, _, _, _ ->
                            uiEvents.onNext(UiEvent.FilterClick(filter = homeHeader.headerTitle))
                        }
                        .originHeader(homeHeader)
                )
            }



            CarouselModel_()
                .id("carousel_events")
                .models(carousel)
                .addTo(this)
            it.cats.forEachIndexed { index, catsModel ->
                CatModel_()
                    .id("cats_${catsModel.name}_${index}")
                    .clickCallback {   _, _, _, _ ->
                        uiEvents.onNext(UiEvent.CatDetailsClick(id = catsModel.name))
                    }
                    .cat(catsModel).addTo(this)

//            }
            }
        }


    }


}



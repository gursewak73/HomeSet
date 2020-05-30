package com.wallpaper.homeset.model

class FeatureModel {

    enum class Action {
        TOAST,
        PROGRESS
    }

    var action : Action? = null
    var show = false
    var msg = ""

    fun setToast(msg : String) : FeatureModel{
        action = Action.TOAST
        this.msg = msg
        return this
    }

    fun setProgress(show : Boolean) : FeatureModel{
        action = Action.PROGRESS
        this.show = show
        return this
    }

}
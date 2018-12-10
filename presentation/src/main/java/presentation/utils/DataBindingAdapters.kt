package presentation.utils

import android.databinding.BindingAdapter
import android.view.View
import io.reactivex.subjects.PublishSubject

@BindingAdapter("visibility")

// тоже самое, если написать метод в классе View
fun View.visibility(visibility:Boolean){
    this.visibility = if(visibility) View.VISIBLE else View.GONE
}

@BindingAdapter("OnClick")
fun View.onClick(subject: PublishSubject<Boolean>){
    setOnClickListener{
        subject.onNext(true)
    }
}

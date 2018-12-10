package presentation.screen.student

import android.databinding.ObservableField
import android.util.Log
import entity.StudentSearch
import factories.UseCaseProvider
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.ReplaySubject
import presentation.base.BaseViewModel
import java.util.concurrent.TimeUnit

class StudentViewModel : BaseViewModel<StudentRouter>() {

    private val getStudentUseCase = UseCaseProvider.provideGetStudentUseCase()
    private val searchStudentsUseCase = UseCaseProvider.provideSearchStudentUseCase()

    val studentSize = ObservableField<String>("No data")
    var timer = ObservableField<String>(" ")

    val url = ObservableField<String>("No data")
    val name = ObservableField<String>("No data")
    val surname = ObservableField<String>("No data")

    private val subscription : Disposable


    //private var disposable: Disposable? = null

    //private val publishSubject: PublishSubject<String> = PublishSubject.create() //можно отправить и получить данные, показывает только элементы после подписки

    //private val publishSubject: BehaviorSubject<String> = BehaviorSubject.createDefault("default") //сохраняет последний записанный

    private val publishSubject: ReplaySubject<String> = ReplaySubject.create() // сохраняет все значения

    init {

//        lateinit var context: Context

        Log.e("AAA", "init()")

        val disposable = getStudentUseCase.get().subscribeBy(
                onNext = {
                    studentSize.set("student.size()= ${it.size}")

                    name.set(it[0].name)
                },
                onError = {
                    studentSize.set("onError()" + it.toString())
                    router?.showError(it)

                })
        addToDisposible(disposable)

        subscription = Observable.interval(1, TimeUnit.SECONDS)
                .filter{ t -> t%2<1 }
                .subscribeBy(
                onNext = {
                    timer.set(it.toString())
                })
        addToDisposible(subscription)

        fun search(search:String){
            val studentSearch = StudentSearch(search)
            val disposable = searchStudentsUseCase.search(studentSearch).subscribeBy(
                    onNext = {
                        //ToDo передать данные в адаптер
                    },
                    onError = {
                        studentSize.set("onError()" + it.toString())
                        router?.showError(it)

                    })
            addToDisposible(disposable)
        }




//        publishSubject.onNext("Item 1")
//        publishSubject.onNext("Item 2")
//
//        disposable = publishSubject
//                .subscribeBy {
//                    Log.e("AAA", "publishSubject()" + it)
//                }
//        publishSubject.onNext("Item 3")
//        publishSubject.onNext("Item 4")
//        publishSubject.onNext("Item 5")

//        disposable = Observable
//                .just("123", "90", "250")
//                .map {
//                    Integer.valueOf(it)
//                }
//                .filter {
//                    it > 200
//                }
//                .observeOn(AndroidSchedulers.mainThread()) //говорит, в каком потоке выполнять действия,
//                                                                // написанные ниже вызова
//                .subscribeOn(Schedulers.io()) // отдельный поток
//                .subscribeBy(onError = {
//                    Log.e("AAA", "onError()" + it.toString())
//                },onComplete = {
//                    Log.e("AAA", "onComplete()")
//
//                },onNext = {
//                    Log.e("AAA", "onNext()" + it)
//                })
//                .subscribe(object : Observer<Int> {
//                    override fun onComplete() {
//                        Log.e("AAA", "onComplete()")
//                    }
//
//                    override fun onSubscribe(d: Disposable) {
//                        Log.e("AAA", "onSubscribe")
//                        disposable = d
//                    }
//
//                    override fun onNext(t: Int) {
//                        Log.e("AAA", "onNext()" + t)
//                    }
//
//                    override fun onError(e: Throwable) {
//                        Log.e("AAA", "onError()" + e.toString())
//                    }
//                })
    }

    fun onTextClick(){

    }

    override fun onResume(){
        addToDisposible(subscription)
    }

//    override fun onPauseSubscription(){
//        subscription.dispose()
//    }

}
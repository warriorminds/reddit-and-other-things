package com.warriorminds.redditsampleapp.viewmodels

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.support.annotation.MainThread
import java.util.concurrent.atomic.AtomicBoolean

/**
 * A lifecycle-aware observable that sends only new updates after subscription, used for events like
 * navigation and Snackbar messages.
 * <p>
 * This avoids a common problem with events: on configuration change (like rotation) an update
 * can be emitted if the observer is active. This LiveData only calls the observable if there's an
 * explicit call to setValue() or call().
 * <p>
 * Note that this is slightly different implementation than the single live event implmented by the sample google app,
 * this aims to solve the only one observer being notified issue.
 */
class SingleLiveEvent<T> : MutableLiveData<T>() {
    private val mPendingObservers = mutableMapOf<Observer<T>, AtomicBoolean>()

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<T>) {

        val interceptor = object : Observer<T> {
            override fun onChanged(t: T?) {
                synchronized(mPendingObservers) {
                    mPendingObservers[this]?.let {
                        if (it.compareAndSet(true, false)) {
                            observer.onChanged(t)
                        }
                    }
                }
            }
        }

        synchronized(mPendingObservers) {
            mPendingObservers[interceptor] = AtomicBoolean(false)
            super.observe(owner, interceptor)
        }
    }

    @MainThread
    override fun removeObserver(observer: Observer<T>) {
        synchronized(mPendingObservers) {
            mPendingObservers.remove(observer)
            super.removeObserver(observer)
        }
    }

    @MainThread
    override fun setValue(t: T?) {
        synchronized(mPendingObservers) {
            for (pending in mPendingObservers.values) {
                pending.set(true)
            }
        }
        super.setValue(t)
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    fun call() {
        value = null
    }
}
package global.x.weather.infrastructure.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun LiveData<Boolean>.observeAsAction(
    lifecycleOwner: LifecycleOwner,
    action: () -> Unit,
    onActionResolved: () -> Unit
) {
    // LiveData will only keep one subscription per-LifecycleOwner. If one is already present the
    // new LiveData.observe() call will have no effect. See implementation of LiveData.observe() for
    // reference. If action relies on variable parameters subsequent calls would be ignored and the
    // old set of parameters will be used.
    //
    // The most common example is action using a CoroutineScope provided by a Composable. The
    // CoroutineScope can change if the Composable is recreated (not just recomposed). By default
    // the old scope would be used instead of the new one.
    //
    // To resolve this problem we remove previous observers on the provided LifecycleOwner is
    // BEFORE calling LiveData.observe() to ensure a new subscription with new parameters.
    this.removeObservers(lifecycleOwner)
    this.observe(lifecycleOwner) { isActionRequested ->
        if (isActionRequested == true) {
            action.invoke()
            onActionResolved.invoke()
        }
    }
}

fun <T> LiveData<T?>.observeAsAction(
    lifecycleOwner: LifecycleOwner,
    action: (T) -> Unit,
    onActionResolved: () -> Unit
) {
    // LiveData will only keep one subscription per-LifecycleOwner. If one is already present the
    // new LiveData.observe() call will have no effect. See implementation of LiveData.observe() for
    // reference. If action relies on variable parameters subsequent calls would be ignored and the
    // old set of parameters will be used.
    //
    // The most common example is action using a CoroutineScope provided by a Composable. The
    // CoroutineScope can change if the Composable is recreated (not just recomposed). By default
    // the old scope would be used instead of the new one.
    //
    // To resolve this problem we remove previous observers on the provided LifecycleOwner is
    // BEFORE calling LiveData.observe() to ensure a new subscription with new parameters.
    this.removeObservers(lifecycleOwner)
    this.observe(lifecycleOwner) { actionParams ->
        if (actionParams != null) {
            action.invoke(actionParams)
            onActionResolved.invoke()
        }
    }
}
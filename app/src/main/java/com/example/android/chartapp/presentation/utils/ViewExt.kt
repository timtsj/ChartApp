package com.example.android.chartapp.presentation.utils

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.text.Editable
import android.text.InputFilter
import android.text.TextWatcher
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.example.android.chartapp.presentation.base.adapter.base.BaseCompositeDelegateAdapter

/**
 * Set on [EditText] listener on enter button click.
 *
 * @param targetEditText editText that will listen
 * @param runnable callback for enter action
 */
fun EditText.setOnEnterKeyListener(onEnterClicked: () -> Boolean) = setOnEditorActionListener { _, actionId, _ ->
    if (actionId == EditorInfo.IME_ACTION_DONE) {
        onEnterClicked()
    } else false
}

/**
 * Simple way to show SnackBar
 */

fun Any.showSnackBar(view: View, message: String, length: Int = Snackbar.LENGTH_SHORT) {
    val snack = Snackbar.make(view, message, length)
    setSnackbarStyle(snack)
    snack.show()
}

fun Any.showSnackBar(view: View, @StringRes message: Int, length: Int = Snackbar.LENGTH_SHORT) {
    val snack = Snackbar.make(view, message, length)
    setSnackbarStyle(snack)
    snack.show()
}

private fun setSnackbarStyle(snack: Snackbar) {
    val tv = snack.view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
    tv.setTextColor(Color.WHITE)
}

/**
 * Method that open keyboard.
 *
 * @param targetView view where need open keyboard
 */
fun View.openKeyboard(method: Int = InputMethodManager.SHOW_IMPLICIT) {
    requestFocus()
    val inputMethodManager = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(this, 0)
//    inputMethodManager.toggleSoftInputFromWindow(this.applicationWindowToken, method, 0)
}

/**
 * Method that userNotAuthorized keyboard.
 *
 * @param targetView focused view
 */
fun View.closeKeyboard(method: Int? = null) {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    this.clearFocus()
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

inline fun inAnimationTransaction(view: View, block: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && view.parent is ViewGroup) {
        val autoTransition = AutoTransition()
        autoTransition.duration = 200
        TransitionManager.beginDelayedTransition(view.parent as ViewGroup, autoTransition)
    }
    block()
}

fun View.setOnGlobalLayoutListener(callback: () -> Unit) {
    this.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            viewTreeObserver.removeOnGlobalLayoutListener(this)
            callback()
        }
    })
}

fun TextView.textPost(text: String) {
    post {
        this.text = text
    }
}

fun ViewGroup.forEach(block: (index: Int, child: View) -> Unit) {
    for (i in 0 until this.childCount) {
        block(i, getChildAt(i))
    }
}

fun ViewGroup.findOnEach(block: (index: Int, child: View) -> View?): View? {
    for (i in 0 until this.childCount) {
        val item = block(i, getChildAt(i))
        if (item != null) {
            return item
        }
    }

    return null
}

fun ViewGroup.inflateChild(restId: Int, view: (View) -> Unit = {}) {
    LayoutInflater.from(context).inflate(restId, this, true).apply {
        view(this)
    }
}

internal fun View?.findSuitableParent(): ViewGroup? {
    var view = this
    var fallback: ViewGroup? = null
    do {
        when (view) {
            is CoordinatorLayout -> return view
            is FrameLayout -> {
                if (view.id == android.R.id.content) return view else fallback = view
            }
        }
        view?.let {
            val parent = it.parent
            view = if (parent is View) parent else null
        }
    } while (view != null)

    return fallback
}

fun EditText.setMaxLength(maxLength: Int): EditText {
    setFilters(filters.toMutableList().apply {
        removeAll { it is InputFilter.LengthFilter }
        add(InputFilter.LengthFilter(maxLength))
    }.toTypedArray())
    return this
}

fun ViewGroup.children(): List<View> {
    return mutableListOf<View>().apply {
        for (i in 0 until childCount) {
            add(getChildAt(i))
        }
    }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, root: ViewGroup = this, attachToRoot: Boolean = false): View = LayoutInflater.from(this.context).inflate(layoutRes, root, attachToRoot)

fun <T> RecyclerView.setItems(items: List<T>) {
    if (adapter !is BaseCompositeDelegateAdapter<*>) {
        return
    }

    (adapter as BaseCompositeDelegateAdapter<T>).setItems(items)
}

fun <T> RecyclerView.getItems(): List<T> {
    if (adapter !is BaseCompositeDelegateAdapter<*>) {
        return listOf()
    }

    return (adapter as BaseCompositeDelegateAdapter<T>).getItems()
}

fun View.addRipple() = with(TypedValue()) {
    context.theme.resolveAttribute(android.R.attr.selectableItemBackground, this, true)
    setBackgroundResource(resourceId)
}

fun View.setMargins(left: Int, top: Int, right: Int, bottom: Int) {
    if (layoutParams is ViewGroup.MarginLayoutParams) {
        val params = layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(left, top, right, bottom)
        layoutParams = params
    }
}

fun View.setZeroPadding() {
    setPadding(0, 0, 0, 0)
}
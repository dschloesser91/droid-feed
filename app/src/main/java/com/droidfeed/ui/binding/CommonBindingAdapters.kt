package com.droidfeed.ui.binding

import android.text.format.DateUtils
import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.droidfeed.R
import com.droidfeed.util.extention.fadeIn
import com.droidfeed.util.extention.fadeOut
import com.droidfeed.util.glide.GlideApp
import com.google.android.material.textfield.TextInputLayout
import java.util.*

@BindingAdapter("app:avdImageResource")
fun avdImageResource(
    imageView: ImageView,
    avdImageResource: Int
) {
    imageView.setImageResource(avdImageResource)
}

@BindingAdapter("app:isVisible")
fun isVisible(
    view: View,
    isVisible: Boolean
) {
    if (view.isVisible != isVisible)
        view.isVisible = isVisible
}

@BindingAdapter("app:isSelected")
fun isSelected(
    view: View,
    isSelected: Boolean
) {
    if (view.isSelected != isSelected) {
        view.isSelected = isSelected
    }
}

@BindingAdapter("app:isEnabled")
fun isEnabled(
    view: View,
    isEnabled: Boolean
) {
    if (view.isEnabled != isEnabled) {
        view.isEnabled = isEnabled

        if (isEnabled) {
            view.fadeIn()
        } else {
            view.fadeOut(0.5f)
        }
    }
}

@BindingAdapter("app:errorText")
fun errorText(
    view: TextInputLayout,
    stringId: Int
) {
    val string = view.context.getString(stringId)
    view.error = string
    view.isErrorEnabled = string.isNotBlank()
}

@BindingAdapter("app:loadImage")
fun loadImage(
    imageView: ImageView,
    resId: Int
) {
    GlideApp.with(imageView)
        .load(resId)
        .into(imageView)
}

@BindingAdapter("app:displayUrl")
fun displayUrl(
    webView: WebView,
    url: String?
) {
    url?.let { webView.loadUrl(it) }
}

@BindingAdapter("app:relativeTimestamp")
fun setRelativeTimestamp(
    view: TextView,
    date: Date?
) {
    if (date != null) {
        val relativeDate = DateUtils.getRelativeTimeSpanString(
            date.time,
            Calendar.getInstance(TimeZone.getDefault()).timeInMillis,
            DateUtils.SECOND_IN_MILLIS
        )

        view.text = relativeDate.toString()
    }
}

@BindingAdapter(
    value = ["app:publisher",
        "app:timestamp"],
    requireAll = true
)
fun setRelativeDate(
    view: TextView,
    publisher: String?,
    timestamp: Long?
) {
    val date = if (timestamp == null) {
        ""
    } else {
        DateUtils.getRelativeTimeSpanString(
            timestamp,
            Calendar.getInstance(TimeZone.getDefault()).timeInMillis,
            android.text.format.DateUtils.SECOND_IN_MILLIS,
            DateUtils.FORMAT_ABBREV_ALL
        )
    }

    view.text = view.context.getString(
        R.string.publisher_time,
        publisher ?: "",
        date.toString()
    )
}
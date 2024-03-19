package com.udacity.asteroidradar

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.udacity.asteroidradar.db.AsteroidEntity
import com.udacity.asteroidradar.utils.Logger

@BindingAdapter("statusIcon")
fun ImageView.bindAsteroidStatusImage(isHazardous: Boolean) {
    if (isHazardous) {
        this.setImageResource(R.drawable.ic_status_potentially_hazardous)
        this.contentDescription =
            this.context.getString(R.string.potentially_hazardous_asteroid_icon)
    } else {
        this.setImageResource(R.drawable.ic_status_normal)
        this.contentDescription =
            this.context.getString(R.string.not_potentially_hazardous_asteroid_icon)
    }
}

@BindingAdapter("statusIconLoading")
fun View.statusIconLoading(data: List<AsteroidEntity>?) {
    this.visibility = if (!data.isNullOrEmpty()) {
        View.GONE
    } else {
        View.VISIBLE
    }
}

@BindingAdapter("asteroidStatusImage")
fun ImageView.bindDetailsStatusImage(isHazardous: Boolean) {
    if (isHazardous) {
        this.setImageResource(R.drawable.asteroid_hazardous)
        this.contentDescription = this.context.getString(
            R.string.potentially_hazardous_asteroid_image
        )
    } else {
        this.setImageResource(R.drawable.asteroid_safe)
        this.contentDescription = this.context.getString(
            R.string.not_hazardous_asteroid_image
        )
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}

@BindingAdapter("bindPictureFromUrl")
fun bindUriToImageView(imageView: ImageView, strUrl: String?) {
    Logger.d("_pictureOfDay: has valueUrl of image: $strUrl")
    Glide.with(imageView.context)
        .load(strUrl)
        .placeholder(R.drawable.placeholder_picture_of_day)
        .error(R.drawable.placeholder_picture_of_day)
        .into(imageView)
}

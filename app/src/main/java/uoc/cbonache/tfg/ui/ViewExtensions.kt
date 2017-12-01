package uoc.cbonache.tfg.ui

import android.content.Context
import android.graphics.Typeface
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.TypedValue
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

/**
 * Created by Borja on 6/6/17.
 */
/**
 * @author cbonache
 */
fun ImageView.bind(url: String) {
    Picasso.with(this.context).load(url).into(this)
}

fun Int.toDp(context: Context): Int {
    val metrics = context.resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), metrics).toInt()
}

fun TextView.setPrefixTextBold(prefix: String, text: String, separator: String = " ") {
    val styledText = SpannableString(prefix)
    styledText.setSpan(StyleSpan(Typeface.BOLD),0,prefix.length,0)
    this.append(styledText)
    this.append(separator)
    this.append(text)
}

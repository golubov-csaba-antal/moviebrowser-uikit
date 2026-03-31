package com.zappyware.moviebrowser.common.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage
import com.zappyware.moviebrowser.common.ui.ContentRating
import com.zappyware.moviebrowser.common.ui.FavoriteIcon
import com.zappyware.moviebrowser.common.ui.LocalColorProvider
import com.zappyware.moviebrowser.common.ui.dropShadow
import com.zappyware.moviebrowser.common.ui.withShadow
import com.zappyware.moviebrowser.data.widget.MovieWidget
import com.zappyware.moviebrowser.data.widget.Widget

@Composable
fun MovieWidgetPortraitComposable(
    modifier: Modifier,
    widget: MovieWidget,
    castShadow: Boolean = false,
    onDetailsClicked: (Widget) -> Unit,
) {
    val colorProvider = LocalColorProvider.current

    Box(
        modifier = modifier
            .dropShadow(castShadow, colorProvider.shadowColor, 24.dp, 16.dp)
            .clickable {
                onDetailsClicked(widget)
            },
    ) {
        AsyncImage(
            model = widget.smallPosterUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .zIndex(1.0f)
                .clip(RoundedCornerShape(24.dp))
                .background(colorProvider.imageBackgroundColor),
        )
        FavoriteIcon(
            modifier = Modifier
                .padding(all = 16.dp)
                .zIndex(2.0f)
                .align(Alignment.TopStart),
            contentId = widget.id,
            hideIfNotFavorite = true,
        )
        Column(
            modifier = Modifier
                .zIndex(2.0f)
                .padding(16.dp)
                .align(Alignment.BottomEnd),
        ) {
            Text(
                text = widget.title,
                style = MaterialTheme.typography.labelLarge.withShadow(castShadow = true, colorProvider.shadowColor),
                color = colorProvider.textColorLight,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = widget.genre,
                style = MaterialTheme.typography.labelSmall.withShadow(castShadow = true, colorProvider.shadowColor),
                color = colorProvider.textColorLight,
            )
            Spacer(modifier = Modifier.height(8.dp))
            ContentRating(
                modifier = Modifier.fillMaxWidth(),
                rating = widget.voteAverage,
                castShadow = true,
            )
        }
    }
}

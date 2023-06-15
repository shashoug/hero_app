package shashoug.com.tracker_presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import shashoug.com.core.R
import shashoug.com.coreui.LocalSpacing
import shashoug.com.tracker_domain.model.TrackedFood


@ExperimentalCoilApi
@Composable
fun TrackedFoodItem(
    trackedFood: TrackedFood,
    onDeleteClick : () -> Unit,
    modifier: Modifier = Modifier
){
    val spacing = LocalSpacing.current

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .padding(spacing.spaceExtraSmall)
            .shadow(
                shape = RoundedCornerShape(5.dp),
                elevation = 1.dp
            )
            .background(MaterialTheme.colors.surface)
            .padding(end = spacing.spaceMedium)
            .height(100.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
      Image(
          painter = rememberImagePainter(
              data = trackedFood.imageUrl,
              builder = {
                  crossfade(true)
                  error(R.drawable.ic_burger)
                  fallback(R.drawable.ic_burger)
              }
          ),
          contentDescription = trackedFood.name,
          contentScale = ContentScale.Crop,
          modifier = Modifier
              .fillMaxHeight()
              .aspectRatio(1f)
              .clip(
                  RoundedCornerShape(
                      topStart = 5.dp,
                      bottomStart = 5.dp
                  )
              )
      )
        Spacer(modifier = Modifier.width(spacing.spaceMedium))
        Column (
            modifier = Modifier
                .weight(1f)

                ) {
            Text(
                text = trackedFood.name,
                style = MaterialTheme.typography.body1,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
            Text(
                text = "${trackedFood.amount}"  + " " +"${trackedFood.calories}",
                style = MaterialTheme.typography.body2,
            )

        }
        Spacer(modifier = Modifier.width(spacing.spaceMedium))
        Column (
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center
                ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Delete",
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable { onDeleteClick() }
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Row (
                verticalAlignment = Alignment.CenterVertically
                    ) {
                NutrientInfo(
                    amount = trackedFood.carbs,
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp,
                    nameTextStyle = MaterialTheme.typography.body2,
                    unit =  "g",
                    name = "Carbs")
                Spacer(modifier = Modifier.width(spacing.spaceSmall))
                NutrientInfo(
                    amount = trackedFood.protein,
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp,
                    nameTextStyle = MaterialTheme.typography.body2,
                    unit =  "g",
                    name = "Protein")
                Spacer(modifier = Modifier.width(spacing.spaceSmall))
                NutrientInfo(
                    amount = trackedFood.fat,
                    amountTextSize = 16.sp,
                    unitTextSize = 12.sp,
                    nameTextStyle = MaterialTheme.typography.body2,
                    unit =  "g",
                    name = "Fat")
            }




        }


    }
}
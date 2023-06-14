package shashoug.com.tracker_presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import shashoug.com.coreui.LocalSpacing
import shashoug.com.tracker_presentation.tracker_overview.Meal

@Composable
fun ExpandableMeal(
    meal : Meal,
    onToggleClick : () ->Unit,
    content : @Composable () -> Unit,
    modifier: Modifier
){
             val spacing = LocalSpacing.current
    val context = LocalContext.current
    Column (
        modifier = modifier
            ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onToggleClick() }
                .padding(spacing.spaceMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
       Image(
           painter = painterResource(id = meal.drawableRes),
           contentDescription = meal.name.asString(context)
       )
            Spacer(modifier = Modifier.width(spacing.spaceMedium))
            Column (
                modifier = Modifier
                    .weight(1f)
                    ) {
                Row (
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = meal.name.asString(context),
                        style = MaterialTheme.typography.h3
                    )
                    Icon(
                        imageVector = if(meal.isExpanded) { Icons.Default.KeyboardArrowUp} else Icons.Default.KeyboardArrowDown,
                        contentDescription = if(meal.isExpanded) {"collapse"} else "extend" )
                }
                Spacer(modifier = Modifier.height(spacing.spaceSmall))
                Row (
                    modifier  = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement =  Arrangement.SpaceBetween
                        ) {
                    UnitDisplay(amount = meal.calories, unit = "kcal", amountTextSize = 30.sp )
                    Row {
                        NutrientInfo(
                            amount = meal.calories , 
                            unit = "g" ,
                            name = "Carbs"
                        )
                        Spacer(modifier = Modifier.width(spacing.spaceSmall))
                        NutrientInfo(
                            amount = meal.protein ,
                            unit = "g" ,
                            name = "Protein"
                        )
                        Spacer(modifier = Modifier.width(spacing.spaceSmall))
                        NutrientInfo(
                            amount = meal.fat ,
                            unit = "g" ,
                            name = "Fats"
                        )
                    }

                }
            }
        }
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        AnimatedVisibility(visible = meal.isExpanded) {
            content()
        }
    }
            }
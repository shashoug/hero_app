package shashoug.com.tracker_presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import shashoug.com.coreui.LocalSpacing

@Composable
fun NutrientInfo(
    amount : Int,
    unit : String,
    modifier: Modifier = Modifier,
    name : String,
    amountTextSize : TextUnit = 20.sp,
    nameTextStyle : TextStyle = MaterialTheme.typography.body1,
    amountColor : Color = MaterialTheme.colors.onBackground,
    unitTextSize: TextUnit =  14.sp,
    unitColor : Color = MaterialTheme.colors.onBackground
){
Column (
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally
        ) {
    UnitDisplay(
        amount = amount,
        unit = unit,
        amountTextSize = amountTextSize,
        amountColor = amountColor,
        unitColor = unitColor,
        unitTextSize = unitTextSize,
    )
    Text(
        text = name,
        color = MaterialTheme.colors.onBackground,
        style = nameTextStyle
    )

}
}
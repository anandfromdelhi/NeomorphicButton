package com.anandfromdelhi.swpetologinbutton


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.anandfromdelhi.swpetologinbutton.ui.theme.SwpeToLoginButtonTheme
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SwpeToLoginButtonTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SwipeToLoginButton {

                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SwipeToLoginButton(
    onClick: () -> Unit
) {
    val swipeableState = rememberSwipeableState(0)
    val targetPx = with(LocalDensity.current) { (130.dp.toPx()) }
    val anchors = mapOf(00f to 0, targetPx to 1)
    var clickState by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

    var swipeState by remember { mutableStateOf(false) }
    Column(Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .weight(1f),
            contentAlignment = Alignment.Center,
        ) {
            Card(
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .width(200.dp)
                    .height(70.dp)
                    .advancedShadow(
                        color = Color.White,
                        alpha = 0.6f,
                        cornersRadius = 50.dp,
                        shadowBlurRadius = 10.dp,
                        offsetX = 0.dp,
                        offsetY = -5.dp
                    )
            ) {

            }

            Card(
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .width(200.dp)
                    .height(70.dp)
                    .swipeable(
                        state = swipeableState,
                        anchors = anchors,
                        thresholds = { _, _ -> FractionalThreshold(0.3f) },
                        orientation = Orientation.Horizontal
                    )
                    .shadow(
                        elevation = 15.dp,
                        shape = RoundedCornerShape(50),
                        spotColor = Color.Black,
                        ambientColor = Color.DarkGray
                    ),
                colors = CardDefaults.cardColors(containerColor = Color.LightGray)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp)
                        .offset {
                            IntOffset(swipeableState.offset.value.roundToInt(), y = 0)
                        },
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Card(
                        shape = RoundedCornerShape(50),
                        modifier = Modifier
                            .size(50.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Red
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
                    ) {}

                }
            }

        }
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            if (!clickState){
                Card(
                    shape = RoundedCornerShape(50),
                    modifier = Modifier
                        .width(200.dp)
                        .height(70.dp)
                        .advancedShadow(
                            color = if (!clickState) {
                                Color.White
                            } else {
                                Color.Transparent
                            },
                            alpha = 0.6f,
                            cornersRadius = 50.dp,
                            shadowBlurRadius = 20.dp,
                            offsetX = 0.dp,
                            offsetY = -10.dp
                        )
                ) {

                }
            }

            Card(
                shape = RoundedCornerShape(50),
                colors = CardDefaults.cardColors(containerColor = Color.LightGray),
                modifier = Modifier
                    .width(200.dp)
                    .height(70.dp)
                    .clickable(
                        indication = null,
                        interactionSource = interactionSource
                    ) {

                        clickState = !clickState
                    }
                    .shadow(
                        elevation = if (!clickState){10.dp}else{0.dp},
                        shape = RoundedCornerShape(50),
                        spotColor = Color.Black,
                        ambientColor = Color.DarkGray
                    )
            ) {
                Box(
                    contentAlignment =Alignment.Center, modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = "Zapp")
                }

            }
        }
    }
}


fun Modifier.advancedShadow(
    color: Color = Color.Black,
    alpha: Float = 1f,
    cornersRadius: Dp = 0.dp,
    shadowBlurRadius: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp
) = drawBehind {
    val shadowColor = color.copy(alpha = alpha).toArgb()
    val transparentColor = color.copy(alpha = 0f).toArgb()
    drawIntoCanvas {
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()
        frameworkPaint.color = transparentColor
        frameworkPaint.setShadowLayer(
            shadowBlurRadius.toPx(),
            offsetX.toPx(),
            offsetY.toPx(),
            shadowColor
        )

        it.drawRoundRect(
            0f,
            0f,
            this.size.width,
            this.size.height,
            cornersRadius.toPx(),
            cornersRadius.toPx(),
            paint
        )
    }
}

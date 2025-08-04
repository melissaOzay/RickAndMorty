package com.developer.rickandmorty.ui.bottom_menu

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PathMeasure
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

fun Float.toRadians(): Float = this * Math.PI.toFloat() / 180f

@Composable
fun ExpandableFabMenu(
    items: List<FabMenuItem>,
    modifier: Modifier = Modifier,
    radius: Dp = 60.dp,
    selectedIcon: ImageVector? = null
) {
    var isExpanded by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (isExpanded) 45f else 0f,
        animationSpec = tween(durationMillis = 200), label = ""
    )

    val animationProgressList = remember {
        items.map { Animatable(0f) }
    }

    LaunchedEffect(isExpanded) {
        animationProgressList.forEachIndexed { index, animatable ->
            animatable.animateTo(
                targetValue = if (isExpanded) 1f else 0f,
                animationSpec = spring(
                    dampingRatio = 0.6f,
                    stiffness = 350f
                )
            )
        }
    }

    var mainFabCenter by remember { mutableStateOf(Offset.Zero) }
    val iconCenters = remember { mutableStateMapOf<Int, Offset>() }

    Box(
        modifier = modifier.wrapContentSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        val density = LocalDensity.current
        val radiusPx = with(density) { radius.toPx() }

        val customAngles = listOf(-190f, -140f, -90f)

        Canvas(modifier = Modifier.fillMaxSize()) {
            if (isExpanded && mainFabCenter != Offset.Zero) {
                val pathMeasure = PathMeasure()
                items.forEachIndexed { index, _ ->
                    val endCenter = iconCenters[index]
                    if (endCenter != null) {
                        val progress = animationProgressList.getOrNull(index)?.value ?: 0f

                        val path = Path().apply {
                            moveTo(mainFabCenter.x, mainFabCenter.y)
                            cubicTo(
                                x1 = mainFabCenter.x, y1 = mainFabCenter.y - (radiusPx * progress),
                                x2 = endCenter.x, y2 = endCenter.y,
                                x3 = endCenter.x, y3 = endCenter.y
                            )
                        }

                        pathMeasure.setPath(path, false)
                        val pathLength = pathMeasure.length

                        if (pathLength > 0) {
                            drawPath(
                                path = path,
                                color = Color(0xFF8BC34A),
                                style = Stroke(
                                    width = 5.dp.toPx(),
                                    pathEffect = PathEffect.dashPathEffect(
                                        intervals = floatArrayOf(pathLength, pathLength),
                                        phase = pathLength * (1f - progress)
                                    )
                                )
                            )
                        }
                    }
                }
            }
        }

        items.forEachIndexed { index, item ->
            val progress = animationProgressList.getOrNull(index)?.value ?: 0f

            val angle = if (index < customAngles.size) customAngles[index] else -90f

            val x = (radiusPx * cos(angle.toRadians())) * progress
            val y = (radiusPx * sin(angle.toRadians())) * progress

            SmallFloatingActionButton(
                onClick = {
                    item.onClick()
                    isExpanded = false
                },
                modifier = Modifier
                    .offset {
                        IntOffset(x.toInt(), y.toInt())
                    }
                    .graphicsLayer {
                        alpha = progress
                        scaleX = progress
                        scaleY = progress
                    }
                    .onGloballyPositioned { coordinates ->
                        iconCenters[index] = coordinates.positionInParent() + Offset(coordinates.size.width / 2f, coordinates.size.height / 2f)
                    },
                containerColor = Color.Green,
                contentColor = Color.White
            ) {
                Icon(imageVector = item.icon, contentDescription = item.title, modifier = Modifier.size(20.dp))
            }
        }

        FloatingActionButton(
            onClick = { isExpanded = !isExpanded },
            containerColor= Color(0xFF8BC34A),
            modifier = Modifier.onGloballyPositioned { coordinates ->
                mainFabCenter = coordinates.positionInParent() + Offset(coordinates.size.width / 2f, coordinates.size.height / 2f)
            }
        ) {
            Icon(
                imageVector = selectedIcon ?: Icons.Default.Add,
                contentDescription = "Menu",
                modifier = Modifier.rotate(if (isExpanded) rotation else 0f)
            )
        }
    }
}
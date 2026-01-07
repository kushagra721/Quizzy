package com.quizzy.`in`.util
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.Density

class BottomCutoutShape(
    private val cutoutWidthPercent: Float = 0.30f,
    private val cutoutHeightPercent: Float = 0.35f
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val w = size.width
        val h = size.height

        val radius = 32f
        val cutoutWidth = w * cutoutWidthPercent
        val cutoutHeight = h * cutoutHeightPercent

        val cutoutStart = (w - cutoutWidth) / 2f
        val cutoutEnd = cutoutStart + cutoutWidth

        return Outline.Generic(
            Path().apply {
                // top-left corner
                moveTo(0f, radius)
                quadraticTo(0f, 0f, radius, 0f)
                // top edge
                lineTo(w - radius, 0f)
                // top-right corner
                quadraticTo(w, 0f, w, radius)
                // right edge
                lineTo(w, h - radius)
                // bottom-right corner
                quadraticTo(w, h, w - radius, h)

                // bottom edge until cutout start
                lineTo(cutoutEnd, h)

                // cutout curve (inward)
                val midX = w / 2f
                val bottomY = h
                val controlY = h + cutoutHeight

                cubicTo(
                    cutoutEnd - cutoutWidth * 0.25f, bottomY,
                    midX + cutoutWidth * 0.15f, controlY,
                    midX, controlY
                )
                cubicTo(
                    midX - cutoutWidth * 0.15f, controlY,
                    cutoutStart + cutoutWidth * 0.25f, bottomY,
                    cutoutStart, bottomY
                )

                // bottom edge to left
                lineTo(radius, h)
                // bottom-left corner
                quadraticTo(0f, h, 0f, h - radius)
                // left edge
                lineTo(0f, radius)

                close()
            }
        )
    }
}

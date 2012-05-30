package org.waman.functionplotter

import org.jfree.chart.plot.PlotOrientation as PO

xylinechart(XAxisLabel:'X', YAxisLabel:'Y', orientation:PO.VERTICAL, legend:true) {
    xyplot {
        foregroundAlpha 1.0f
    }
}

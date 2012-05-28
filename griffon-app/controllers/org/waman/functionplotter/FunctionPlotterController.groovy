package org.waman.functionplotter

import java.awt.Color
import javax.swing.JOptionPane
//import griffon.lookandfeel.LookAndFeelManager as LAF
import org.jfree.data.general.DatasetUtilities
import com.thecoderscorner.groovychart.chart.ChartBuilder

class FunctionPlotterController {

    def model
    def view

    def paintGraph = { event = null ->
        def props = null
        edt{
            props = model.copyProperties()
        }

        def data = createDataset(props)
        def chart = createChart(CoordinateChart)
        chart.plot.with{
            dataset = data
            rangeAxis.with{
                lowerBound = props.min
                upperBound = props.max
            }
        }

        doLater{
            view.coordinate.chart = chart
        }
    }

    static createDataset(Map props){
        def func = new ScriptFunction2D(props.function)
        return DatasetUtilities.sampleFunction2D(func, props.from, props.to, props.samples, 'f(x)')
    }

    static createChart(Class chartScriptClass){
        def chartScript = chartScriptClass.newInstance()
        def builder = new ChartBuilder()
        chartScript.metaClass.methodMissing = { String name, args -> builder.invokeMethod(name, args) }
        return chartScript.run().chart
    }

    def adjustRange = { event = null ->
        edt{
            view.coordinate.chart.plot.plot.rangeAxis.with{
                lowerBound = model.min
                upperBound = model.max
            }
        }
    }

//    def showLaf = { event = null ->
//        edt{
//            LAF.instance.showLafDialog(app)
//        }
//    }

    def showAbout = { event = null ->
        edt{
            JOptionPane.showMessageDialog(app.windowManager.windows[0],
                '''A Function Plotter
that serves as a SwingBuilder example for
Groovy in Action''')
        }
    }
}

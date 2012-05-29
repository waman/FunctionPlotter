package org.waman.functionplotter

import java.awt.Color
import javax.swing.JOptionPane
//import griffon.lookandfeel.LookAndFeelManager as LAF
import org.jfree.data.general.DatasetUtilities
import com.thecoderscorner.groovychart.chart.ChartBuilder

class FunctionPlotterController {

    def model
    def view
    def datasetService

    void mvcGroupInit(Map args) {
        def func = buildMVCGroup('monolineFunction', 'f', name:'f(x)')
        view.functionSection.add(func.view.content)
    }

    def paintAll = { event = null ->
        def fdata = null, domain = null, range = null
        edt{
            fdata = app.models.f.createFunctionData()
            domain = model.domain
            range = model.range
        }

        def data = datasetService.createXYDataset(fdata, domain)
        def chart = createChart(CoordinateChart)
        chart.plot.with{
            dataset = data
            rangeAxis.with{
                lowerBound = range[0]
                upperBound = range[1]
            }
        }

        doLater{
            view.coordinate.chart = chart
        }
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

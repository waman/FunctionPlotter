package org.waman.functionplotter

import java.awt.Color
import javax.swing.JOptionPane
//import griffon.lookandfeel.LookAndFeelManager as LAF
import org.jfree.data.general.DatasetUtilities

class FunctionPlotterController {

    def model
    def view

    def paintGraph = { event = null ->
        edt{
            def func = new ScriptFunction2D(model.function)
            def dataset = DatasetUtilities.sampleFunction2D(func, model.from, model.to, model.samples, 'f(x)')
            view.coordinate.chart.plot.dataset = dataset
        }
    }

    def adjustRange = { event = null ->
        view.coordinate.chart.plot.plot.rangeAxis.with{
            lowerBound = model.min
            upperBound = model.max
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

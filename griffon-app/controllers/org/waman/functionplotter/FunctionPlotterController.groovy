package org.waman.functionplotter

import java.awt.Color
import javax.swing.JOptionPane
//import griffon.lookandfeel.LookAndFeelManager as LAF

class FunctionPlotterController {

    def model
    def view

    def paintGraph = { event = null ->
        def calc = new Dynamo(model.function)

        edt{
            def canvas = view.canvas
            def g = canvas.graphics
            int w = canvas.size.width
            int h = canvas.size.height

            g.color = Color.WHITE
            g.fillRect(0, 0, w, h)
            g.color = Color.BLUE

            def (dx, dy) = [(model.to - model.from) / w, h / (model.max - model.min)]
            int ceiling = h + model.min * dy
            int lastY = calc.f(model.from) * dy
            for(x in (1..w)){
                int y = calc.f(model.from + x * dx) * dy
                g.drawLine(x-1, ceiling - lastY, x, ceiling-y)
                lastY = y
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

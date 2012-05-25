package org.waman.functionplotter

import java.awt.BorderLayout as BL
import javax.swing.WindowConstants as WC
import javax.swing.BorderFactory as BF

actions{
    action(id:'paint', name:'Paint', mnemonic:'P', accelerator:'ctrl P',
        closure:controller.paintGraph)

    action(id:'about', name:'About', mnemonic:'A', accelerator:'F1',
        closure:controller.showAbout)
}

application(title: 'FunctionPlotter',
  location:[100, 100], size: [400, 400],
  defaultCloseOperation:WC.EXIT_ON_CLOSE,
  iconImage: imageIcon('/griffon-icon-48x48.png').image,
  iconImages: [imageIcon('/griffon-icon-48x48.png').image,
               imageIcon('/griffon-icon-32x32.png').image,
               imageIcon('/griffon-icon-16x16.png').image]) {
    menuBar(){
        menu(mnemonic:'A', 'Action'){
            menuItem(action:paint)
        }
        glue()
        menu(mnemonic:'H', 'H'){
            menuItem(action:about)
        }
    }

    panel(border:BF.createEmptyBorder(6, 6, 6, 6)){
        borderLayout()
        vbox(constraints:BL.NORTH){
            hbox{
                hstrut width:10
                label 'f(x) = '
                textField action:paint, columns:20, text:bind(target:model, 'function'), 'sin(x)'
                button(action:paint)
            }
        }
        vbox(constraints:BL.WEST){
            labeledSpinner('max', 1d)
            20.times{ vglue() }
            labeledSpinner('min', -1d)
        }
        vbox(constraints:BL.CENTER, border:BF.createTitledBorder('Function Plot')){
            panel id:'canvas'
        }
        hbox(constraints:BL.SOUTH){
            hstrut width:10
            labeledSpinner('from', 0d)
            10.times{ hglue() }
            labeledSpinner('to', 2d*Math.PI)
        }
    }
}

def labeledSpinner(label, value){
    this.label(label)
    spinner value:bind(target:model, label), stateChanged:controller.paintGraph,
            model:spinnerNumberModel(value:value)
}
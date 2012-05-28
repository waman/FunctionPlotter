package org.waman.functionplotter

import java.awt.BorderLayout as BL
import javax.swing.WindowConstants as WC
import javax.swing.BorderFactory as BF

JFrame.defaultLookAndFeelDecorated = true
JDialog.defaultLookAndFeelDecorated = true

actions{
    action(id:'paint', name:'Paint', mnemonic:'P', accelerator:'ctrl P',
        closure:controller.paintGraph)

//    action(id:'showLaf', name:'show Look & Feel', mnemonic:'L', accelerator:'ctrl L',
//        closure:controller.showLaf)

    action(id:'about', name:'About', mnemonic:'A', accelerator:'F1',
        closure:controller.showAbout)
}

application(title: 'FunctionPlotter',
//  location:[100, 100], size: [400, 400],
  size:[800, 600], locationByPlatform:true,
  defaultCloseOperation:WC.EXIT_ON_CLOSE,
  iconImage: imageIcon('/griffon-icon-48x48.png').image,
  iconImages: [imageIcon('/griffon-icon-48x48.png').image,
               imageIcon('/griffon-icon-32x32.png').image,
               imageIcon('/griffon-icon-16x16.png').image]) {
    menuBar{
        menu(mnemonic:'A', 'Action'){
            menuItem(action:paint)
        }
//        menu(mnemonic:'L', 'Look & Feel'){
//            menuItem(action:showLaf)
//        }
        glue()
        menu(mnemonic:'H', 'Help'){
            menuItem(action:about)
        }
    }

    panel(border:emptyBorder(6)){
        migLayout()

        panel(constraints:'north'){
            migLayout columnConstraints:'[30][300]25[50][50]25[50]'

            label 'f(x) = '
            textField 'sin(x)', action: paint, columns: 30, text: bind(target: model, 'function')
            label 'samples : '
            textField '1000', action: paint, columns: 5, text: bind(target: model, 'samples', converter:{ it as int })
            button action:paint
        }

        panel(border:titledBorder(title:'Function Plot')){
            chart id:'coordinate', CoordinateChart.class
        }

        panel(constraints:'south'){
            migLayout columnConstraints:'60[20][40]540[20][40]'

            domainSpinner('from', 0d)
            domainSpinner('to', 2d*Math.PI)
        }

        panel(constraints:BL.WEST){
            migLayout layoutConstraints:'wrap 1', columnConstraints:'[40]', rowConstraints:'[10][20]350[10][20]'
            rangeSpinner('max', 1d)
            rangeSpinner('min', -1d)
        }
    }
}

def domainSpinner(label, value){
    this.label(label)
    spinner(value:bind(target:model, label), 
               stateChanged:controller.paintGraph, 
               model:spinnerNumberModel(value:value))
}

def rangeSpinner(label, value){
    this.label(label)
    spinner(value:bind(target:model, label),
               stateChanged:controller.adjustRange,
               model:spinnerNumberModel(value:value))
}
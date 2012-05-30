package org.waman.functionplotter

JFrame.defaultLookAndFeelDecorated = true
JDialog.defaultLookAndFeelDecorated = true

actions{
    action(id:'paintAll', name:'Paint All', mnemonic:'P', accelerator:'ctrl P',
        closure:controller.paintAll)

    action(id:'addFunction', name:'Add Function', mnemonic:'F', accelerator:'ctrl F',
        closure:controller.addFunction)

    action(id:'showLaf', name:'show Look & Feel', mnemonic:'L', accelerator:'ctrl L',
        closure:controller.showLaf)

    action(id:'showInfo', name:'Show Info', mnemonic:'I', accelerator:'F1',
        closure:controller.showInfo)
}

application(title: 'FunctionPlotter',
//  location:[100, 100], size: [400, 400],
  size:[1000, 600], locationByPlatform:true,
  defaultCloseOperation:WindowConstants.EXIT_ON_CLOSE,
  iconImage: imageIcon('/griffon-icon-48x48.png').image,
  iconImages: [imageIcon('/griffon-icon-48x48.png').image,
               imageIcon('/griffon-icon-32x32.png').image,
               imageIcon('/griffon-icon-16x16.png').image]) {
    menuBar{
        menu(mnemonic:'A', 'Action'){
            menuItem(action:paintAll)
            menuItem(action:addFunction)
        }
        menu(mnemonic:'L', 'Look & Feel'){
            menuItem(action:showLaf)
        }
        glue()
        menu(mnemonic:'H', 'Help'){
            menuItem(action:showInfo)
        }
    }

    panel(border:emptyBorder(6)){
        migLayout()

        panel(id:'controlSection', constraints:'west', border:emptyBorder(3)){
            migLayout layoutConstraints:'wrap 1'

            scrollPane{
                panel(id:'functionSection'){
                    migLayout layoutConstraints:'wrap 1'
                }
            }

            panel{
                migLayout()
                button action:addFunction
                button action:paintAll
            }
        }

        panel(id:'chartSection', border:titledBorder(title:'Function Plot')){
            migLayout()

            panel{
                chart id:'coordinate', CoordinateChart.class
            }

            panel(constraints:'south'){
                migLayout columnConstraints:'60[20][40]540[20][40]'

                domainSpinner('from', 0d)
                domainSpinner('to', Math.PI*2d)
            }

            panel(constraints:'west'){
                migLayout layoutConstraints:'wrap 1', columnConstraints:'[40]', rowConstraints:'[10][20]350[10][20]'

                rangeSpinner('max', 1d)
                rangeSpinner('min', -1d)
            }
        }
    }
}

def domainSpinner(label, value){
    this.label(label)
    spinner(value:bind(target:model, label), 
               stateChanged:controller.paintAll, 
               model:spinnerNumberModel(value:value))
}

def rangeSpinner(label, value){
    this.label(label)
    spinner(value:bind(target:model, label),
               stateChanged:controller.adjustRange,
               model:spinnerNumberModel(value:value))
}
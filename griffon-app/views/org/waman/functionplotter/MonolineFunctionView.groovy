package org.waman.functionplotter

panel(id:'content', border:titledBorder(title:'Groovy')){
    migLayout()

    label text:bind{ model.name }
    label '='
    textField 'sin(x)', columns:12, text:bind(target:model, 'function')
}

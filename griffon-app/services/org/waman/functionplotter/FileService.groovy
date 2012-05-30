package org.waman.functionplotter

import javax.swing.JFileChooser
import javax.swing.JOptionPane

class FileService {

    def saveFile(JFileChooser fc, Closure c){
    	if (fc.showSaveDialog() == JFileChooser.APPROVE_OPTION){
            def file = fc.selectedFile

            if(canSaveFile(file)) c(file)
        }
    }

    def canSaveFile(File file){
        switch(file){
            case null:
                return false

            case { !it.exists() }:
                return true

            default:
                def result = JOptionPane.showConfirmDialog(
                                 app.windowManager.windows[0],
                                 """File "${file.name}" already exists.\nDo you override it?""",
                                 'Confirm File Override', 
                                 JOptionPane.YES_NO_OPTION)
                return result == JOptionPane.YES_OPTION
        }
    }
}
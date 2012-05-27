application {
    title = 'FunctionPlotter'
    startupGroups = ['functionPlotter']

    // Should Griffon exit when no Griffon created frames are showing?
    autoShutdown = true

    // If you want some non-standard application class, apply it here
    //frameClass = 'javax.swing.JFrame'
}
mvcGroups {
    // MVC Group for "functionPlotter"
    'functionPlotter' {
        model      = 'org.waman.functionplotter.FunctionPlotterModel'
        view       = 'org.waman.functionplotter.FunctionPlotterView'
        controller = 'org.waman.functionplotter.FunctionPlotterController'
    }
}

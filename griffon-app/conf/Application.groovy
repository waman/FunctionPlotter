application {
    title = 'FunctionPlotter'
    startupGroups = ['functionPlotter']

    // Should Griffon exit when no Griffon created frames are showing?
    autoShutdown = true

    // If you want some non-standard application class, apply it here
    //frameClass = 'javax.swing.JFrame'
}
mvcGroups {
    // MVC Group for "monolineFunction"
    'monolineFunction' {
        model      = 'org.waman.functionplotter.MonolineFunctionModel'
        view       = 'org.waman.functionplotter.MonolineFunctionView'
        controller = 'org.waman.functionplotter.MonolineFunctionController'
    }

    // MVC Group for "functionPlotter"
    'functionPlotter' {
        model      = 'org.waman.functionplotter.FunctionPlotterModel'
        view       = 'org.waman.functionplotter.FunctionPlotterView'
        controller = 'org.waman.functionplotter.FunctionPlotterController'
    }
}

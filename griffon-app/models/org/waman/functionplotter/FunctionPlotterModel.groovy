package org.waman.functionplotter

class FunctionPlotterModel {

    @Bindable double from
    @Bindable double to
    @Bindable double min
    @Bindable double max

    List<Double> getDomain(){
        return [from, to]
    }

    List<Double> getRange(){
        return [min, max]
    }
}
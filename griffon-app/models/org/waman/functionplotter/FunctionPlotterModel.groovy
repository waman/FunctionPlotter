package org.waman.functionplotter

class FunctionPlotterModel {

    private final List<FunctionDataFactory> factories = []

    int functionIndex = 0
    def functionId = new FunctionId()

    @Bindable double from, to, min, max

    def leftShift(factory){
        return this.factories << factory
    }

    List<FunctionData> createFunctionDataList(){
        return this.factories.collect{ it.createFunctionData() }
    }

    List<Double> getDomain(){
        return [from, to]
    }

    List<Double> getRange(){
        return [min, max]
    }
}
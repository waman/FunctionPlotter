package org.waman.functionplotter

import groovy.beans.Bindable

class MonolineFunctionModel implements FunctionDataFactory{

    int index
    @Bindable String name
    @Bindable String function
    @Bindable int samples = 1000

    FunctionData createFunctionData(){
        return new FunctionData(name, function, samples)
    }
}
package org.waman.functionplotter

class FunctionPlotterModel {

    @Bindable String function
    @Bindable int samples

    @Bindable double from
    @Bindable double to
    @Bindable double min
    @Bindable double max

    def Map copyProperties(){
        def map = [:]
        ['function', 'samples', 'from', 'to', 'min', 'max'].each{ prop ->
            map[prop] = this[prop]
        }
        return map
    }
}
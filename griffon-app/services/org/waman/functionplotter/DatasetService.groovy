package org.waman.functionplotter

import org.jfree.data.function.Function2D
import org.jfree.data.general.DatasetUtilities
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYDataset

class DatasetService {

    XYSeries createXYSeries(FunctionData fdata, List<Double> domain){
        def f = new ScriptFunction2D(fdata.function)
        return DatasetUtilities.sampleFunction2DToSeries(f, domain[0], domain[1], fdata.samples, fdata.name)
    }

    XYDataset createXYDataset(FunctionData fdata, List<Double> domain){
        def f = new ScriptFunction2D(fdata.function)
        return DatasetUtilities.sampleFunction2D(f, domain[0], domain[1], fdata.samples, fdata.name)
    }
}